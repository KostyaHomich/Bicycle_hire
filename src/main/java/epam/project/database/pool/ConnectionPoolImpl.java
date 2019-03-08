package epam.project.database.pool;

import epam.project.database.dao.exception.ConnectionPoolException;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPoolImpl implements ConnectionPool {

    private static Logger LOGGER = Logger.getLogger(ConnectionPoolImpl.class.getName());

    private static final String DRIVER_CLASS = "driver";
    private static final String DB_HOST = "host";
    private static final String DB_PORT = "port";
    private static final String DB_NAME = "name";
    private static final String POOL_CAPACITY = "poolCapacity";
    private static final String DB_PROPERTIES_NAME = "db.properties";

    private Properties properties;
    private String url;
    private String host;
    private int poolCapacity;
    private String port;
    private String dbName;
    private Semaphore semaphore;
    private static Lock lock = new ReentrantLock();

    private BlockingDeque<Connection> connections = new LinkedBlockingDeque<>();

    private static ConnectionPool instance;

    private ConnectionPoolImpl(){
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPoolImpl();
                ((ConnectionPoolImpl) instance).init();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public void init() throws ConnectionPoolException {

        properties = new Properties();
        try (InputStream inputStream  = ConnectionPoolImpl.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_NAME)) {
            properties.load(inputStream);

            host = properties.getProperty(DB_HOST);
            port = properties.getProperty(DB_PORT);
            dbName = properties.getProperty(DB_NAME);

            this.url = "jdbc:mysql://" + host + ":" + port + "/" + dbName +
                    "?verifyServerCertificate=false" +
                    "&useSSL=false" +
                    "&requireSSL=false" +
                    "&useLegacyDatetimeCode=false" +
                    "&amp" +
                    "&serverTimezone=UTC" +
                    "&allowPublicKeyRetrieval=true";


            poolCapacity = Integer.parseInt(properties.getProperty(POOL_CAPACITY));
            Class.forName(properties.getProperty(DRIVER_CLASS));
            semaphore = new Semaphore(poolCapacity);
            connections = new LinkedBlockingDeque<>();
            for (int i = 0; i < poolCapacity; i++) {
               createConnection();
            }

        } catch (IOException |SQLException| ClassNotFoundException e ) {
            throw new ConnectionPoolException("Initialize error.", e);
        }
    }

    @Override
    public Connection getConnection() throws ConnectionPoolException {
        Connection connection ;
        try {
            semaphore.acquire();
            if (connections.isEmpty()) {
                createConnection();
            }
            connection = connections.take();

        } catch (InterruptedException | SQLException e) {
            throw new ConnectionPoolException("Error taking the connection.", e);
        }
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        connections.push(connection);
        semaphore.release();
    }

    @Override
    public void destroyPool() throws ConnectionPoolException {
        try {
            for (Connection connection : connections) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

    private void initDriver(String driverClass) {
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Driver cannot be found", e);
        }
    }

    private Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(url, properties);
        connections.add(connection);
        InvocationHandler connectionHandler = (Object proxy, Method method, Object[] args) -> {
            if (method.getName().equals("close")) {
                releaseConnection((Connection) proxy);
                return null;
            }
            return method.invoke(connection, args);
        };

        return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(), connectionHandler);
    }
}

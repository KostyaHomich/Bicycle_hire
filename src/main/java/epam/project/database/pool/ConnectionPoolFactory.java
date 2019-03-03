package epam.project.database.pool;

import epam.project.database.dao.exception.ConnectionPoolException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolFactory {

    private static ConnectionPoolFactory instance;
    private static Lock lock = new ReentrantLock();

    private ConnectionPoolFactory() {}

    public static ConnectionPoolFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new ConnectionPoolFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    public ConnectionPool getConnectionPool() throws ConnectionPoolException {
        return ConnectionPoolImpl.getInstance();
    }
}

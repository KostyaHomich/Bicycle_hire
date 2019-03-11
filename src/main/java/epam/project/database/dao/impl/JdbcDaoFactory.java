package epam.project.database.dao.impl;

import epam.project.database.dao.*;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.pool.ConnectionPool;
import epam.project.database.pool.ConnectionPoolFactory;
import epam.project.entity.Bicycle;
import epam.project.entity.Order;
import epam.project.entity.PointHire;
import epam.project.entity.User;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class JdbcDaoFactory implements DaoFactory, TransactionalDaoFactory {
    private static JdbcDaoFactory instance;
    private static Lock lock = new ReentrantLock();
    private Map<Class, Supplier<EntityDao>> creators = new HashMap<>();

    private class DaoInvocationHandler implements InvocationHandler {
        private final EntityDao dao;

        DaoInvocationHandler(EntityDao dao) {
            this.dao = dao;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;

            if (Arrays.stream(dao.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(AutoConnection.class))
                    .map(Method::getName)
                    .anyMatch(m -> m.equals(method.getName()))) {

                ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
                Connection connection = connectionPool.getConnection();

                TransactionManager.setConnectionWithReflection(dao, connection);

                result = method.invoke(dao, args);

                connectionPool.releaseConnection(connection);
                TransactionManager.setConnectionWithReflection(dao, null);

            } else {
                result = method.invoke(dao, args);
            }

            return result;
        }

    }

    private JdbcDaoFactory() {
        creators.put(Bicycle.class, BicycleDaoImpl::new);
        creators.put(PointHire.class, PointHireDaoImpl::new);
        creators.put(Order.class, OrderDao::new);
        creators.put(User.class, UserDaoImpl::new);
    }

    public static JdbcDaoFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new JdbcDaoFactory();
            }
        } finally {
            lock.unlock();
        }

        return instance;
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> EntityDao<T, PK> getDao(Class<T> entityClass) throws DaoException {
        Supplier<EntityDao> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }
        EntityDao dao = daoCreator.get();

        return (EntityDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                new DaoInvocationHandler(dao));
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> EntityDao<T, PK> getTransactionalDao(Class<T> entityClass) throws DaoException {
        Supplier<EntityDao> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity class cannot be find");
        }

        return daoCreator.get();
    }
}

package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.pool.ConnectionPoolFactory;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.ConnectionPoolException;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.pool.ConnectionPool;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TransactionManager {
    private Connection proxyConnection;
    private List<EntityDao> daoList;

    public void begin(EntityDao dao, EntityDao... daos) throws DaoException {
        try {
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            proxyConnection = connectionPool.getConnection();
            setConnectionWithReflection(dao, proxyConnection);
            for (EntityDao d : daos) {
                setConnectionWithReflection(d, proxyConnection);
            }

            daoList = new ArrayList<>(daos.length + 1);
            daoList.addAll(Arrays.asList(daos));
            daoList.add(dao);

        } catch (ConnectionPoolException e) {
            throw new DaoException("Failed to get a connection from CP.", e);
        }
    }

    public void end() throws DaoException {
        try {
            for (EntityDao d : daoList) {
                setConnectionWithReflection(d, null);
            }
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            connectionPool.releaseConnection(proxyConnection);
            proxyConnection = null;
            daoList = null;
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public void commit() throws SQLException {
        proxyConnection.commit();
    }

    public void rollback() throws SQLException {
        proxyConnection.rollback();
    }

    static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DaoException("DAO implementation does not extends AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DaoException("Failed to set connection for transactional DAO. ", e);
        }
    }
}

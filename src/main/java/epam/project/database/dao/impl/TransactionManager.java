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

public final class TransactionManager {
    private Connection proxyConnection;

    public void begin(EntityDao dao, EntityDao... daos) throws DaoException, ConnectionPoolException {

        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

        try {
            proxyConnection = connectionPool.getConnection();
            proxyConnection.setAutoCommit(false);
            setConnectionWithReflection(daos, proxyConnection);
            for (EntityDao d : daos) {
                setConnectionWithReflection(d, proxyConnection);
            }

        } catch (ConnectionPoolException e) {
            throw new DaoException("Failed to get a connection from CP.", e);
        } catch (SQLException e) {
            throw new DaoException("Failed to set auto commit", e);
        }
    }

    public void end() throws SQLException {
        proxyConnection.setAutoCommit(true);
        proxyConnection.close();

    }

    public void commit() throws SQLException {
        proxyConnection.commit();
    }

    public void rollback() throws SQLException {
        proxyConnection.rollback();
    }


    static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DaoException("DAO implementation does not extend AbstractJdbcDao.");
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

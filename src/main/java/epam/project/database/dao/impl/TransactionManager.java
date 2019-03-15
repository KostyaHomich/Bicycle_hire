package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.pool.ConnectionPoolFactory;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.ConnectionPoolException;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.pool.ConnectionPool;
import epam.project.database.pool.ConnectionPoolImpl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class TransactionManager {
    private Connection connection;


    public void begin(AbstractJdbcDao dao, AbstractJdbcDao ... daos) throws DaoException {
        try {
            ConnectionPool pool = ConnectionPoolImpl.getInstance();
            this.connection = pool.getConnection();
            connection.setAutoCommit(false);
            setConnectionWithReflection(dao,connection);
            for (EntityDao specificDao : daos){
                setConnectionWithReflection(specificDao,connection);
            }

        } catch (SQLException| ConnectionPoolException e) {
            throw new DaoException("problem with  connection.setAutoCommit(false)" , e);
        }

    }

    public void end() throws DaoException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("failed to return connection" , e);
        }
    }

    public void commit() throws DaoException{
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException("failed to commit transaction" , e);
        }
    }

    public void rollback() throws DaoException {

        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new DaoException("problem with  rollback transaction" , e);
        }
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

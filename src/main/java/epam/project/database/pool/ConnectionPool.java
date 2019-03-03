package epam.project.database.pool;

import epam.project.database.dao.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection() throws ConnectionPoolException;
    void releaseConnection(Connection connection);
    void destroyPool() throws ConnectionPoolException;
}

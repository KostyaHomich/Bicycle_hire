package epam.project.database.dao.impl;

import epam.project.database.dao.exception.ConnectionPoolException;
import epam.project.database.pool.ConnectionPool;
import epam.project.database.pool.ConnectionPoolImpl;



import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.stream.Collectors;


public class InitBdTest {
    private static volatile boolean created = false;

    public static void bdInit() throws ConnectionPoolException {
        if (!created) {
            created = true;
            ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
            try (Connection connection = connectionPool.getConnection()) {
                String create = Files
                        .readAllLines(Paths.get("src/test/resources/create_bd.sql"), StandardCharsets.UTF_8)
                        .stream().collect(Collectors.joining());
                Statement statement = connection.createStatement();
                statement.execute(create);
                statement.close();
            } catch (IOException | SQLException | ConnectionPoolException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

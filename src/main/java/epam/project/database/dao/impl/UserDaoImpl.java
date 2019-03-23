package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.AutoConnection;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.UserDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.entity.User;
import epam.project.entity.UserRole;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements UserDao, EntityDao<User, Integer> {

    private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());


    private static final String CREATE_QUERY =
            "insert into user values (NULL ,?,?,?, ?,?,?, ?,?,?);";
    private static final String UPDATE_QUERY =
            "update user set login=?,password=?,first_name=?,last_name=?,status=?,registration_Date=?,balance=?,email=?,id_Role=? where " +
                    "id=?;";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE id=?;";
    private static final String SELECT_QUERY = "SELECT * FROM user";

    private static final String CHECK_IF_CONTAINS_BY_LOGIN = "SELECT * FROM user WHERE login=?;";
    private static final String CHECK_IF_CONTAINS_BY_EMAIL = "SELECT * FROM user WHERE email=?;";
    private static final String GET_BY_LOGIN = "SELECT * FROM user WHERE login=?;";

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws DaoException {
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                setDefaultData(rs, user);


                result.add(user);
            }
        } catch (Exception e) {
            throw new DaoException("Failed to create entity.", e);
        }
        return result;
    }

    private void setDefaultData(ResultSet rs, User user) throws SQLException {
        user.setId(rs.getInt("id"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setFirstName(rs.getString("first_Name"));
        user.setLastName(rs.getString("last_Name"));
        user.setStatus(rs.getString("status"));
        user.setRegistrationDate(rs.getString("registration_Date"));
        user.setBalance(rs.getBigDecimal("balance"));
        user.setEmail(rs.getString("email"));
        int role = rs.getInt("id_Role");
        user.setRole(UserRole.values()[--role].toString().toLowerCase());
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {

        setDefaultUserData(statement, object, 0);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {

        int counter = 0;
        counter = setDefaultUserData(statement, object, counter);
        statement.setInt(++counter, object.getId());
    }

    private int setDefaultUserData(PreparedStatement statement, User object, int counter) throws SQLException {

        statement.setString(++counter, object.getLogin());
        statement.setString(++counter, object.getPassword());
        statement.setString(++counter, object.getFirstName());
        statement.setString(++counter, object.getLastName());
        statement.setString(++counter, object.getStatus());
        statement.setString(++counter, object.getRegistrationDate());
        statement.setBigDecimal(++counter, object.getBalance());
        statement.setString(++counter, object.getEmail());
        int role = UserRole.valueOf(object.getRole().toUpperCase()).ordinal();
        role++;
        statement.setInt(++counter, role);
        return counter;

    }


    @Override
    public String getSelectQuery() {
        return SELECT_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return CREATE_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }


    @AutoConnection
    @Override
    public User getByLogin(String login) throws DaoException {
        User user;
        ResultSet rs;

        try (PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN)) {

            statement.setString(1, login);
            rs = statement.executeQuery();

            if (rs.next()) {
                user = new User();
                setDefaultData(rs, user);
            } else {
                throw new DaoException("This user doesn't exist");
            }
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }

        return user;
    }

    @AutoConnection
    @Override
    public List<User> getUsers(int count) throws DaoException {
        List<User> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            int counter = 0;
            ResultSet rs = statement.executeQuery();
            while (rs.next() && counter < count) {
                User user = new User();
                counter++;
                setDefaultData(rs, user);
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity's", e);
        }
    }


    @AutoConnection
    @Override
    public boolean checkLoginExistance(String login) throws DaoException {

        return checkExistance(login, CHECK_IF_CONTAINS_BY_LOGIN);
    }

    @AutoConnection
    @Override
    public boolean checkEmailExistance(String email) throws DaoException {

        return checkExistance(email, CHECK_IF_CONTAINS_BY_EMAIL);

    }

    private boolean checkExistance(String value, String checkIfContains) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(checkIfContains)) {
            statement.setString(1, value);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }
    }
}

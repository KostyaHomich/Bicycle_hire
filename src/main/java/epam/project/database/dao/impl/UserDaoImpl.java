package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.AutoConnection;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.UserDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.entity.Role;
import epam.project.entity.User;
import epam.project.service.impl.UserService;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements UserDao,EntityDao<User, Integer> {

    private static Logger LOGGER = Logger.getLogger(UserDaoImpl.class.getName());

    private static final String CREATE_QUERY =
            "insert into user values (NULL ,?,?,?, ?,?,?, ?,?,?);";
    private static final String UPDATE_QUERY =
            "update user set login=?,password=?,first_name=?,last_name=?,status=?,registration_Date=?,balance=?,email=?,id_Role=? where " +
                    "id=?";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE id=?";
    private static final String SELECT_QUERY = "SELECT * FROM user";

    private static final String CHECK_IF_CONTAINS = "SELECT email FROM user WHERE login=?";
    private static final String GET_BY_LOGIN = "SELECT * FROM user WHERE login=?";

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        List<User> result = new ArrayList<>();
        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_Name"));
                user.setLastName(rs.getString("last_Name"));
                user.setStatus(rs.getString("status"));
                user.setRegistrationDate(rs.getString("registration_Date"));
                user.setBalance(rs.getBigDecimal("balance"));
                user.setEmail(rs.getString("email"));
                int role=rs.getInt("id_Role");
                user.setRole(Role.values()[--role].toString().toLowerCase());


                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException("Failed to create entity.",e);
        }
        return result;
    }
    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {

            int counter=1;
            Date date = new java.sql.Date(System.currentTimeMillis());
            Timestamp timestamp = new Timestamp(date.getTime());

            statement.setString(counter,object.getLogin());
            statement.setString(++counter,object.getPassword());
            statement.setString(++counter,object.getFirstName());
            statement.setString(++counter,object.getLastName());
            statement.setString(++counter,object.getStatus());
            statement.setTimestamp(++counter,timestamp);
            statement.setBigDecimal(++counter,object.getBalance());
            statement.setString(++counter,object.getEmail());

            int role=Role.valueOf(object.getRole().toUpperCase()).ordinal();
            role++;

            statement.setInt(++counter,role);

    }
    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {

        int counter=1;
        Date date = new java.sql.Date(System.currentTimeMillis());
        Timestamp timestamp = new Timestamp(date.getTime());

        statement.setString(counter,object.getLogin());
        statement.setString(++counter,object.getPassword());
        statement.setString(++counter,object.getFirstName());
        statement.setString(++counter,object.getLastName());
        statement.setString(++counter,object.getStatus());
        statement.setTimestamp(++counter,timestamp);
        statement.setBigDecimal(++counter,object.getBalance());
        statement.setString(++counter,object.getEmail());

        int role=Role.valueOf(object.getRole().toUpperCase()).ordinal();
        role++;

        statement.setInt(++counter,role);
        statement.setInt(++counter,object.getId());


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

    @Override
    @AutoConnection
    public int signIn() {
        return 0;
    }

    @Override
    @AutoConnection
    public User getByLogin(String login) throws DaoException, PersistException {
        User user;
        ResultSet rs;

        try(PreparedStatement statement = connection.prepareStatement(GET_BY_LOGIN)) {

            statement.setString(1, login);
            rs = statement.executeQuery();

            if(rs.next()) {
                user=new User();
                user.setId(rs.getInt("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("first_Name"));
                user.setLastName(rs.getString("last_Name"));
                user.setStatus(rs.getString("status"));
                user.setRegistrationDate(rs.getString("registration_Date"));
                user.setBalance(rs.getBigDecimal("balance"));
                user.setEmail(rs.getString("email"));
                int role=rs.getInt("id_Role");
                user.setRole(Role.values()[--role].toString().toLowerCase());


            }
            else{
                throw new DaoException("This user doesn't exist");
            }
        }
        catch (SQLException e) {
            throw new PersistException("Failed to get entity.",e);
        }

        return user;
    }

    @Override
    @AutoConnection
    public boolean contains(User user) throws PersistException {

        try(  PreparedStatement statement= connection.prepareStatement(CHECK_IF_CONTAINS)) {

            statement.setString(1, user.getLogin());

            ResultSet rs  = statement.executeQuery();
            if (!rs.next()) {
                return false;
            }
            else{
               return true;
            }
        }
        catch (SQLException e) {
            throw new PersistException("Failed to get entity.",e);
        }

    }
}

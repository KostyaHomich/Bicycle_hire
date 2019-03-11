package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.AutoConnection;
import epam.project.database.dao.BicycleDao;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.dto.PointHireBicycle;
import epam.project.entity.Bicycle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BicycleDaoImpl extends AbstractJdbcDao<Bicycle, Integer> implements BicycleDao, EntityDao<Bicycle, Integer> {

    private static final String CREATE_QUERY =
            "insert into bicycle values (NULL ,?,?,?,?);";

    private static final String CREATE_QUERY_POINT_HIRE_BICYCLE =
            "insert into point_hire_bicycle values (NULL ,?,?);";

    private static final String UPDATE_QUERY =
            "update bicycle set daily_rental_price=?,name=?,status=?,description=? where id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM bicycle WHERE id=?";

    private static final String SELECT_QUERY =
            "SELECT * FROM bicycle";

    private static final String CHECK_IF_CONTAINS =
            "SELECT * FROM bicycle WHERE id=?";

    private static final String TAKE_BY_BICYCLE_ID_POINT_HIRE_BICYCLE =
            "SELECT * FROM point_hire_bicycle WHERE id_bicycle=?";

    private static final String GET_ALL_AVAILABLE_BICYCLE =
            "SELECT * FROM bicycle_hire.bicycle where status='available'";

    private static final String GET_ALL_RENTED_BICYCLE =
            "SELECT * FROM bicycle_hire.bicycle where status='rented'";

    private static final String GET_ALL_IN_REPAIRING_BICYCLE =
            "SELECT * FROM bicycle_hire.bicycle where status='in repairing'";

    private static final String GET_ALL_BICYCLE_BY_POINTHIRE =
            "SELECT bicycle.id,daily_rental_price,name,status,description " +
                    "FROM bicycle,point_hire_bicycle where point_hire_bicycle.id_point_hire=?" +
                    " and bicycle.id=point_hire_bicycle.id_bicycle;";


    @Override
    protected List<Bicycle> parseResultSet(ResultSet rs) throws PersistException {
        List<Bicycle> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Bicycle bicycle = new Bicycle();
                bicycle.setId(rs.getInt("id"));
                bicycle.setDaily_rental_price(rs.getBigDecimal("daily_rental_price"));
                bicycle.setName(rs.getString("name"));
                bicycle.setStatus(rs.getString("status"));
                bicycle.setDescription(rs.getString("description"));
                result.add(bicycle);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }


    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Bicycle object) throws SQLException {

        statement.setBigDecimal(1, object.getDaily_rental_price());
        statement.setString(2, object.getName());
        statement.setString(3, object.getStatus());
        statement.setString(4, object.getDescription());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Bicycle object) throws SQLException {

        statement.setBigDecimal(1, object.getDaily_rental_price());
        statement.setString(2, object.getName());
        statement.setString(3, object.getStatus());
        statement.setString(4, object.getDescription());
        statement.setInt(5, object.getId());

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
    public void addPointHireBicycle(Bicycle bicycle) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_QUERY_POINT_HIRE_BICYCLE)) {
            int counter = 0;
            statement.setInt(++counter, bicycle.getPoint_hire_id());
            statement.setInt(++counter, bicycle.getId());
            ResultSet rs = statement.executeQuery();
            rs.next();
        } catch (SQLException e) {
            throw new DaoException("Failed to insert entity", e);
        }
    }

    @AutoConnection
    @Override
    public PointHireBicycle getByBicyclePkPointHireBicycle(int id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(TAKE_BY_BICYCLE_ID_POINT_HIRE_BICYCLE)) {
            PointHireBicycle pointHireBicycle = new PointHireBicycle();
            int counter = 0;
            statement.setInt(++counter, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                pointHireBicycle.setId(rs.getInt("id"));
                pointHireBicycle.setId_bicycle(rs.getInt("id_bicycle"));
                pointHireBicycle.setId_point_hire(rs.getInt("id_point_hire"));
            } else {
                throw new DaoException("This point hire bicycle doesn't exist");
            }
            return pointHireBicycle;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity", e);
        }
    }

    @AutoConnection
    @Override
    public boolean containsBicycle(int id) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(CHECK_IF_CONTAINS)) {

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }

    }

    @AutoConnection
    @Override
    public List<Bicycle> getAllBicycleByPointHirePk(int id) throws DaoException {
        List<Bicycle> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_BICYCLE_BY_POINTHIRE)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Bicycle bicycle = new Bicycle();
                bicycle.setId(rs.getInt("id"));
                bicycle.setDaily_rental_price(rs.getBigDecimal("daily_rental_price"));
                bicycle.setName(rs.getString("name"));
                bicycle.setStatus(rs.getString("status"));
                bicycle.setDescription(rs.getString("description"));
                result.add(bicycle);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }

    }
}

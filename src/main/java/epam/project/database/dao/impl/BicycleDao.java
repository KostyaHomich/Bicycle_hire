package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.PersistException;
import epam.project.entity.Bicycle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BicycleDao extends AbstractJdbcDao<Bicycle, Integer> implements EntityDao<Bicycle, Integer> {

    private static final String CREATE_QUERY =
            "insert into bicycle values (NULL ,?,?,?,?)";

    private static final String UPDATE_QUERY =
            "update bicycle set daily_rental_price=?,name=?,status=?,description=? where id=?";

    private static final String DELETE_QUERY =
            "DELETE FROM bicycle WHERE id=?";

    private static final String SELECT_QUERY =
            "SELECT * FROM bicycle";

    private static final String GET_ALL_BICYCLE =
            "SELECT * FROM bicycle";

    private static final String CHECK_IF_CONTAINS =
            "SELECT idbicycle FROM bicycle_hire.bicycle WHERE idbicycle=?";

    private static final String TAKE_BY_ID =
            "SELECT * FROM bicycle_hire.bicycle WHERE idbicycle=?";

    private static final String GET_ALL_AVAILABLE_BICYCLE =
            "SELECT * FROM bicycle_hire.bicycle where status='available'";

    private static final String GET_ALL_RENTED_BICYCLE =
            "SELECT * FROM bicycle_hire.bicycle where status='rented'";

    private static final String GET_ALL_IN_REPAIRING_BICYCLE =
            "SELECT * FROM bicycle_hire.bicycle where status='in repairing'";

    private static final String GET_ALL_BICYCLE_BY_POINTHIRE =
            "SELECT idbicycle,daily_rental_price,name,status,description FROM bicycle_hire.bicycle,point_hire_bicycle " +
                    "where point_hire_bicycle.id_point_hire=? and bicycle.idbicycle=point_hire_bicycle.id_bicycle;";


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



}

package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.entity.Bicycle;
import epam.project.entity.Order;
import epam.project.entity.PointHire;
import epam.project.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractJdbcDao<Order, Integer> implements EntityDao<Order, Integer> {
    private static final String CREATE_QUERY =
            "insert into bicycle_order values (NULL ,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY =
            "update bicycle_order set id_user=?,id_point_hire_bicycle=?,time_order=?,time_rental=?,status=?,cost=?" +
                    " where id=?";
    private static final String DELETE_QUERY =
            "DELETE FROM bicycle_order WHERE id=?";
    private static final String SELECT_QUERY =
           "SELECT * FROM bicycle_order";

    private static final String CHECK_IF_CONTAINS =
            "SELECT idbicycle FROM bicycle_order WHERE idbicycle_order=?";
    private static final String TAKE_BY_ID =
            "SELECT * FROM bicycle_hire.bicycle_order WHERE idbicycle_order=?";


    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
        List<Order> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setIdUser(rs.getInt("id_user"));
                order.setIdPointHireBicycle(rs.getInt("id_point_hire_bicycle"));
                order.setTimeOrder(rs.getString("time_order"));
                order.setRentalTime(rs.getInt("time_rental"));
                order.setStatus(rs.getString("status"));
                order.setCost(rs.getBigDecimal("cost"));


                result.add(order);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws SQLException {

            statement.setInt(1,object.getIdUser());
            statement.setInt(2,object.getIdPointHireBicycle());
            statement.setString(3,object.getTimeOrder());
            statement.setInt(4,object.getRentalTime());
            statement.setString(5,object.getStatus());
            statement.setBigDecimal(6,object.getCost());

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws SQLException {

            statement.setInt(1,object.getIdUser());
            statement.setInt(2,object.getIdPointHireBicycle());
            statement.setString(3,object.getTimeOrder());
            statement.setInt(4,object.getRentalTime());
            statement.setString(5,object.getStatus());
            statement.setBigDecimal(6,object.getCost());
            statement.setInt(7,object.getId());

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

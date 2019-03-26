package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.AutoConnection;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.OrderDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.dto.PointHireBicycle;
import epam.project.entity.Order;
import epam.project.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends AbstractJdbcDao<Order, Integer> implements OrderDao, EntityDao<Order, Integer> {
    private static final String CREATE_QUERY =
            "insert into bicycle_order values (NULL ,?,?,?,?,?,?)";
    private static final String UPDATE_QUERY =
            "update bicycle_order set id_user=?,id_point_hire_bicycle=?,time_order=?,time_rental=?,status=?,cost=?" +
                    " where id=?";
    private static final String DELETE_QUERY =
            "DELETE FROM bicycle_order WHERE id=?";

    private static final String SELECT_QUERY =
            "SELECT * FROM bicycle_order";
    private static final String GET_ALL_ORDERS_BY_USER_ID =
            "SELECT bicycle_order.* FROM bicycle_order,user where bicycle_order.id_user=user.id and user.id=?;";


    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws DaoException {
        List<Order> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Order order = new Order();
                setOrderData(rs, order);
                result.add(order);
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws SQLException {

        setStatement(statement, object);

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws SQLException {

        setStatement(statement, object);
        statement.setInt(7, object.getId());

    }

    private void setStatement(PreparedStatement statement, Order object) throws SQLException {
        statement.setInt(1, object.getUser().getId());
        statement.setInt(2, object.getPointHireBicycle().getId());
        statement.setString(3, object.getTimeOrder());
        statement.setInt(4, object.getRentalTime());
        statement.setString(5, object.getStatus());
        statement.setBigDecimal(6, object.getCost());
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
    public List<Order> getAllOrdersByUserPk(int pk,int count) throws DaoException {
        List<Order> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS_BY_USER_ID)) {

            statement.setInt(1, pk);
            ResultSet rs = statement.executeQuery();
            int counter = 0;
            while (rs.next() && counter < count) {
                counter++;
                Order order = new Order();
                order.setId(rs.getInt("bicycle_order.id"));
                setOrderData(rs, order);
                result.add(order);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }
    }

    @AutoConnection
    @Override
    public List<Order> getOrders(int count) throws DaoException {
        List<Order> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_QUERY)) {
            ResultSet rs = statement.executeQuery();
            int counter = 0;
            while (rs.next() && counter < count) {
                counter++;
                Order order = new Order();
                setOrderData(rs, order);
                result.add(order);
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException("Failed to get entity.", e);
        }
    }

    private void setOrderData(ResultSet rs, Order order) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id_user"));
        order.setUser(user);
        PointHireBicycle pointHireBicycle = new PointHireBicycle();
        pointHireBicycle.setId(rs.getInt("id_point_hire_bicycle"));
        order.setId(rs.getInt("id"));
        order.setPointHireBicycle(pointHireBicycle);
        order.setTimeOrder(rs.getString("time_order"));
        order.setRentalTime(rs.getInt("time_rental"));
        order.setStatus(rs.getString("status"));
        order.setCost(rs.getBigDecimal("cost"));

    }

}

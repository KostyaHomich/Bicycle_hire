package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;
import epam.project.entity.Order;

import java.util.List;

public interface OrderDao extends EntityDao<Order, Integer> {
    List<Order> getAllOrdersByUserPk(int pk) throws DaoException;
    List<Order> getOrders(int count) throws DaoException;

}

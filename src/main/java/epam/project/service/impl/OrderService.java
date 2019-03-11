package epam.project.service.impl;

import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.entity.Order;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderService implements Service {
    private static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    public OrderService(){

    }

    public List<Order> takeAll() throws ServiceException {

        List<Order> orders;
        try {
            EntityDao<Order,Integer> orderDao = JdbcDaoFactory.getInstance().getDao(Order.class);
            orders = orderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all orders", e);
        }
        return orders;
    }

    public boolean add(Order order) throws ServiceException {

        try {
            EntityDao<Order,Integer> orderDao = JdbcDaoFactory.getInstance().getDao(Order.class);
            orderDao.persist(order);

        } catch (DaoException e) {
            throw new ServiceException("Failed to add order", e);
        }
        return true;

    }

    public boolean delete(Order order) throws ServiceException {

        try {
            EntityDao<Order,Integer> orderDao = JdbcDaoFactory.getInstance().getDao(Order.class);
            orderDao.delete(order);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete order", e);
        }
        return true;
    }

    public Order getById(int id) throws ServiceException {

        try {
            EntityDao<Order,Integer> orderDao = JdbcDaoFactory.getInstance().getDao(Order.class);
            return orderDao.getByPK(id);
        } catch (DaoException  | PersistException e) {
            throw new ServiceException("Failed to get order", e);
        }
    }

    public boolean update(Order order) throws ServiceException {
        try {
            EntityDao<Order,Integer> orderDao = JdbcDaoFactory.getInstance().getDao(Order.class);
            orderDao.update(order);
            return true;
        } catch (DaoException e) {
            throw new ServiceException("Failed to update order", e);
        }

    }

}

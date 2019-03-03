package epam.project.service.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.database.dao.impl.OrderDao;
import epam.project.entity.Order;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderService implements Service {
    private static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    private OrderDao orderDao;

    public OrderService() throws ServiceException {
        init();
    }

    private void init() throws ServiceException {
        try {
            JdbcDaoFactory factory = (JdbcDaoFactory) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
            orderDao = (OrderDao) factory.getDao(Order.class);
        } catch (DaoException e) {
            throw new ServiceException("Initialize error.", e);
        }
    }

    public List<Order> takeAll() throws ServiceException {

        List<Order> orders;
        try {
            orders = orderDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all orders", e);
        }
        return orders;
    }

    public boolean add(Order order) throws ServiceException {

        try {
            orderDao.persist(order);
        } catch (DaoException e) {
            throw new ServiceException("Failed to add order", e);
        }
        return true;

    }

    public boolean delete(Order order) throws ServiceException {

        try {
            orderDao.delete(order);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete order", e);
        }
        return true;
    }

    public boolean update(Order order) throws ServiceException {
        try {
            orderDao.update(order);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete order", e);
        }
        return true;
    }

    public Order takeOrder(int id) throws ServiceException {
        try {
            return orderDao.getByPK(id);
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to take order", e);
        }

    }

}

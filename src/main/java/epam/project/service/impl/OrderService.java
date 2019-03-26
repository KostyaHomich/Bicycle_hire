package epam.project.service.impl;

import epam.project.database.dao.*;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.database.dao.impl.TransactionManager;
import epam.project.dto.PointHireBicycle;
import epam.project.entity.Bicycle;
import epam.project.entity.Order;
import epam.project.entity.PointHire;
import epam.project.entity.User;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
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

    public List<Order> takeAllOrderByUserPk(int pk,int count) throws ServiceException {

        List<Order> orders;
        try {
            OrderDao orderDao = (OrderDao) JdbcDaoFactory.getInstance().getDao(Order.class);
            orders = orderDao.getAllOrdersByUserPk(pk,count);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all orders by pk", e);
        }
        return orders;
    }

    public boolean add(Order order) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            EntityDao<Order, Integer> orderDao = JdbcDaoFactory.getInstance().getTransactionalDao(Order.class);
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getTransactionalDao(Bicycle.class);
            UserDao userDao=(UserDao) JdbcDaoFactory.getInstance().getTransactionalDao(User.class);

            transactionManager.begin((AbstractJdbcDao) orderDao, (AbstractJdbcDao) bicycleDao,(AbstractJdbcDao) userDao);

            PointHireBicycle pointHireBicycle = bicycleDao.getByBicyclePkPointHireBicycle(order.getBicycle().getId());
            order.setPointHireBicycle(pointHireBicycle);

            Bicycle bicycle = bicycleDao.getByPK(pointHireBicycle.getId_bicycle());
            User user=userDao.getByPK(order.getUser().getId());

            BigDecimal cost = new BigDecimal(order.getRentalTime() * bicycle.getDaily_rental_price().intValue());
            BigDecimal userBalance=new BigDecimal(user.getBalance().intValue()-cost.intValue());

            user.setBalance(userBalance);
            order.setCost(cost);

            bicycle.setStatus("rented");

            bicycleDao.update(bicycle);
            userDao.update(user);
            orderDao.persist(order);
            transactionManager.commit();
            transactionManager.end();
            return true;
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException d) {
                LOGGER.error("Failed to rollback", e);
            }
            LOGGER.error("Failed to add order", e);
            throw new ServiceException("Failed to add order", e);

        }

    }

    public boolean cancelOrder(int id) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            EntityDao<Order, Integer> orderDao = JdbcDaoFactory.getInstance().getTransactionalDao(Order.class);
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getTransactionalDao(Bicycle.class);
            transactionManager.begin((AbstractJdbcDao) orderDao, (AbstractJdbcDao) bicycleDao);
            Order order = orderDao.getByPK(id);
            PointHireBicycle pointHireBicycle=bicycleDao.getByPkPointHireBicycle(order.getPointHireBicycle().getId());
            Bicycle bicycle = bicycleDao.getByPK(pointHireBicycle.getId_bicycle());
            bicycle.setStatus("available");
            bicycleDao.update(bicycle);
            order.setStatus("finished");
            orderDao.update(order);

            transactionManager.commit();
            transactionManager.end();
            return true;
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException d) {
                LOGGER.error("Failed to rollback", e);
            }
            e.printStackTrace();
            LOGGER.error("Failed to cancel order", e);
            throw new ServiceException("Failed to cancel order", e);

        }

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
        TransactionManager transactionManager = new TransactionManager();
        try {
            EntityDao<Order, Integer> orderDao = JdbcDaoFactory.getInstance().getTransactionalDao(Order.class);
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getTransactionalDao(Bicycle.class);
            EntityDao<PointHire, Integer> pointHireDao = JdbcDaoFactory.getInstance().getTransactionalDao(PointHire.class);

            transactionManager.begin((AbstractJdbcDao) orderDao, (AbstractJdbcDao) bicycleDao,(AbstractJdbcDao) pointHireDao);

            Order order = orderDao.getByPK(id);
            System.out.println("1 "+order.getId());

            PointHireBicycle pointHireBicycle=bicycleDao.getByPkPointHireBicycle(order.getPointHireBicycle().getId());
            System.out.println("2 "+pointHireBicycle.getId());
            System.out.println("2,5 "+pointHireBicycle.getId_bicycle());
            Bicycle bicycle = bicycleDao.getByPK(pointHireBicycle.getId_bicycle());
            System.out.println("3 "+bicycle.getId());
            PointHire pointHire=pointHireDao.getByPK(pointHireBicycle.getId_point_hire());
            System.out.println("4 "+pointHire.getId());

            order.setBicycle(bicycle);
            order.setPointHire(pointHire);

            transactionManager.commit();
            transactionManager.end();
            return order;
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException d) {
                LOGGER.error("Failed to get by pk order", e);
            }
            e.printStackTrace();
            LOGGER.error("Failed to get by pk order", e);
            throw new ServiceException("Failed to get by pk order", e);
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

    public List<Order> getOrders(int count) throws ServiceException {
        try {
            OrderDao orderDao = (OrderDao) JdbcDaoFactory.getInstance().getDao(Order.class);
            return  orderDao.getOrders(count);
        } catch ( DaoException e) {
            throw new ServiceException("Failed to get bicycles", e);
        }
    }

}

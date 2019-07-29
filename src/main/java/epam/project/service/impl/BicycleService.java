package epam.project.service.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.BicycleDao;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.database.dao.impl.TransactionManager;
import epam.project.dto.PointHireBicycle;
import epam.project.entity.Bicycle;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class BicycleService {
    private static Logger LOGGER = Logger.getLogger(BicycleService.class.getName());

    public BicycleService() {
    }


    public List<Bicycle> takeAll() throws ServiceException {

        try {
            EntityDao<Bicycle, Integer> bicycleDao = JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            return bicycleDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all bicycles", e);
        }
    }

    public List<Bicycle> takeAllBicycleByPointHirePk(int id) throws ServiceException {
        try {
           BicycleDao bicycleDao = (BicycleDao)JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            return bicycleDao.getAllBicycleByPointHirePk(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all bicycles by pk", e);
        }
    }


    public boolean add(Bicycle bicycle) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            EntityDao<Bicycle,Integer>entityDao= JdbcDaoFactory.getInstance().getTransactionalDao(Bicycle.class);
            transactionManager.begin((AbstractJdbcDao) entityDao);
            Bicycle bicycleInserted=entityDao.persist(bicycle);
            bicycleInserted.setPoint_hire_id(bicycle.getPoint_hire_id());
            ((BicycleDao)entityDao).addPointHireBicycle(bicycleInserted);
            transactionManager.commit();
            transactionManager.end();
            return true;
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException d) {
                LOGGER.error("Failed to rollback", e);
            }
            LOGGER.error("Failed to add bicycle", e);
            throw new ServiceException("Failed to add bicycle", e);

        }

    }

    public boolean addBestBicycle(Bicycle bicycle, User user) throws ServiceException {

        try {
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            PointHireBicycle pointHireBicycle=bicycleDao.getByBicyclePkPointHireBicycle(bicycle.getId());
            bicycleDao.addBestBicycle(pointHireBicycle,user);

        } catch (DaoException e) {
            throw new ServiceException("Failed to add best bicycle", e);
        }
        return true;

    }
    public List<Bicycle> showBestBicycles(User user) throws ServiceException {

        try {
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            return bicycleDao.getAllBestBicyclesByUserId(user.getId());

        } catch (DaoException e) {
            throw new ServiceException("Failed to register user", e);
        }

    }

    public boolean deleteBestBicycle(int id) throws ServiceException {

        try {
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            int idBestBicycle=bicycleDao.getBestBicycleIdByBicycleId(id);
            bicycleDao.deleteBestBicycle(idBestBicycle);

        } catch (DaoException e) {
            throw new ServiceException("Failed to delete best bicycle", e);
        }
        return true;

    }

    public boolean delete(Bicycle bicycle) throws ServiceException {
        try {
            EntityDao<Bicycle, Integer> bicycleDao = JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            bicycleDao.delete(bicycle);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete bicycle", e);
        }
        return true;
    }

    public boolean update(Bicycle bicycle) throws ServiceException {
        try {
            BicycleDao entityDao = (BicycleDao) JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            entityDao.update(bicycle);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete bicycle", e);
        }
        return true;
    }

    public boolean contains(int id) throws ServiceException {

        try {
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            return bicycleDao.containsBicycle(id);

        } catch (DaoException e) {
            throw new ServiceException("Failed to check contains bicycle", e);
        }

    }

    public Bicycle getById(int id) throws ServiceException {

        try {
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            PointHireBicycle pointHireBicycle = bicycleDao.getByBicyclePkPointHireBicycle(id);
            Bicycle bicycle = bicycleDao.getByPK(id);
            bicycle.setPoint_hire_id(pointHireBicycle.getId_point_hire());
            return bicycle;
        } catch ( DaoException e) {
            throw new ServiceException("Failed to get bicycle", e);
        }

    }

    public List<Bicycle> getBicycles(int count) throws ServiceException {
        try {
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            return  bicycleDao.getBicycles(count);
        } catch ( DaoException e) {
            throw new ServiceException("Failed to get bicycles", e);
        }
    }
}

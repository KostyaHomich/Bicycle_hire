package epam.project.service.impl;

import epam.project.database.dao.BicycleDao;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.database.dao.impl.TransactionManager;
import epam.project.dto.PointHireBicycle;
import epam.project.entity.Bicycle;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class BicycleService implements Service {
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
        try {
            BicycleDao bicycleDao = (BicycleDao) JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            TransactionManager transactionManager = new TransactionManager();

            transactionManager.begin(bicycleDao);
            bicycleDao.persist(bicycle);
            bicycleDao.addPointHireBicycle(bicycle);
            transactionManager.end();
            transactionManager.commit();

            return true;
        } catch (DaoException | SQLException e) {
            throw new ServiceException("Failed to login bicycle", e);
        }
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
            EntityDao<Bicycle, Integer> bicycleDao = JdbcDaoFactory.getInstance().getDao(Bicycle.class);
            bicycleDao.update(bicycle);
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
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to login bicycle", e);
        }

    }

}

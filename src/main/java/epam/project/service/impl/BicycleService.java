package epam.project.service.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.UserDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.BicycleDao;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.entity.Bicycle;
import epam.project.entity.User;
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
            EntityDao<Bicycle,Integer> bicycleDao =  FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class);
            return  bicycleDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all bicycles", e);
        }
    }

    public boolean add(Bicycle bicycle) throws ServiceException {
        try {
            BicycleDao bicycleDao = (BicycleDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class).getAll();
            bicycleDao.persist(bicycle);
        } catch (DaoException e) {
            throw new ServiceException("Failed to register user", e);
        }
        return true;//user was registered

    }

    public boolean delete(Bicycle bicycle) throws ServiceException {

        try {
            BicycleDao bicycleDao = (BicycleDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class).getAll();
            bicycleDao.delete(bicycle);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete user", e);
        }
        return true;
    }

    public boolean update(Bicycle bicycle) throws ServiceException {
        try {
            BicycleDao bicycleDao = (BicycleDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class).getAll();
            bicycleDao.update(bicycle);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete user", e);
        }
        return true;
    }

    public Bicycle getById(int id) throws ServiceException {
        try {
            BicycleDao bicycleDao = (BicycleDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class).getAll();
            return bicycleDao.getByPK(id);
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to login user", e);
        }

    }

}

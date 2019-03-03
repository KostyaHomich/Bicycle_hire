package epam.project.service.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.BicycleDao;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.entity.Bicycle;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class BicycleService implements Service {
    private static Logger LOGGER = Logger.getLogger(BicycleService.class.getName());

    private BicycleDao bicycleDao;

    public BicycleService() throws ServiceException {
        init();
    }

    private void init() throws ServiceException {
        try {
            JdbcDaoFactory factory = (JdbcDaoFactory) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
            bicycleDao = (BicycleDao) factory.getDao(Bicycle.class);
        } catch (DaoException e) {
            throw new ServiceException("Initialize error.", e);
        }
    }

    public List<Bicycle> takeAll() throws ServiceException {

        List<Bicycle> bicycles;
        try {
            bicycles = bicycleDao.getAll();
        } catch (DaoException  e) {
            throw new ServiceException("Failed to get all users", e);
        }
        return bicycles;
    }

    public boolean add(Bicycle bicycle) throws ServiceException {

        try {
            bicycleDao.persist(bicycle);
        } catch ( DaoException e) {
            throw new ServiceException("Failed to register user", e);
        }
        return true;//user was registered

    }

    public boolean delete(Bicycle bicycle ) throws ServiceException {

        try {
            bicycleDao.delete(bicycle);
        } catch ( DaoException e) {
            throw new ServiceException("Failed to delete user",e);
        }
        return true;
    }

    public boolean update(Bicycle bicycle) throws ServiceException {
        try {
            bicycleDao.update(bicycle);
        } catch ( DaoException e) {
            throw new ServiceException("Failed to delete user",e);
        }
        return true;
    }

    public Bicycle takeBicycle(int id) throws ServiceException {
        try {
            return bicycleDao.getByPK(id);
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to login user", e);
        }

    }

}

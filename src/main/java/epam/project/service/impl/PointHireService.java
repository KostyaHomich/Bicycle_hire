package epam.project.service.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.UserDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.database.dao.impl.PointHireDao;
import epam.project.entity.Bicycle;
import epam.project.entity.PointHire;
import epam.project.entity.User;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class PointHireService implements Service {
    private static Logger LOGGER = Logger.getLogger(PointHireService.class.getName());


    public PointHireService(){
    }


    public List<PointHire> takeAll() throws ServiceException {

        try {
            EntityDao<PointHire,Integer> pointHireDao =  FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);
            return  pointHireDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all point hires", e);
        }
    }

    public boolean add(PointHire pointHire) throws ServiceException {

        try {
            EntityDao<PointHire,Integer> pointHireDao =  FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);
            pointHireDao.persist(pointHire);
        } catch (DaoException e) {
            throw new ServiceException("Failed to add point hire", e);
        }
        return true;

    }

    public boolean delete(PointHire pointHire) throws ServiceException {

        try {
            EntityDao<PointHire,Integer> pointHireDao =  FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);
            pointHireDao.delete(pointHire);
        } catch (  DaoException e) {
            throw new ServiceException("Failed to delete point hire",e);
        }
        return true;
    }

    public boolean update(PointHire pointHire) throws ServiceException {
        try {
            EntityDao<PointHire,Integer> pointHireDao =  FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);
            pointHireDao.update(pointHire);
        } catch ( DaoException e) {
            throw new ServiceException("Failed to delete point hire",e);
        }
        return true;
    }

    public PointHire getById(int id) throws ServiceException {

        try {
            EntityDao<PointHire,Integer> pointHireDao =  FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);
            return pointHireDao.getByPK(id);
        } catch ( DaoException | SQLException | PersistException e) {
            throw new ServiceException("Failed to delete point hire", e);
        }
    }
}

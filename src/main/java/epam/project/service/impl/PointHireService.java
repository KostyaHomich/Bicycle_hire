package epam.project.service.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.database.dao.impl.PointHireDao;
import epam.project.entity.PointHire;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class PointHireService implements Service {
    private static Logger LOGGER = Logger.getLogger(PointHireService.class.getName());
    private PointHireDao pointHireDao;

    public PointHireService() throws ServiceException {
        init();
    }

    private void init() throws ServiceException {
        try {
            JdbcDaoFactory factory = (JdbcDaoFactory) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
            pointHireDao = (PointHireDao) factory.getDao(PointHire.class);
        } catch (DaoException e) {
            throw new ServiceException("Initialize error.", e);
        }
    }

    public List<PointHire> takeAll() throws ServiceException {

        List<PointHire> pointHires;
        try {
            pointHires = pointHireDao.getAll();
        } catch (DaoException  e) {
            throw new ServiceException("Failed to get all point hires", e);
        }
        return pointHires;
    }

    public boolean add(PointHire pointHire) throws ServiceException {

        try {
            pointHireDao.persist(pointHire);
        } catch (DaoException e) {
            throw new ServiceException("Failed to add point hire", e);
        }
        return true;

    }

    public boolean delete(PointHire pointHire) throws ServiceException {

        try {
            pointHireDao.delete(pointHire);
        } catch (  DaoException e) {
            throw new ServiceException("Failed to delete point hire",e);
        }
        return true;
    }

    public boolean update(PointHire pointHire) throws ServiceException {
        try {
            pointHireDao.update(pointHire);
        } catch ( DaoException e) {
            throw new ServiceException("Failed to delete point hire",e);
        }
        return true;
    }

    public PointHire takePointHire(int id) throws ServiceException {
        try {
            return pointHireDao.getByPK(id);
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to take order", e);
        }

    }
}

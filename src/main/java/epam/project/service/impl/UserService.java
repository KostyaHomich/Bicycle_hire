package epam.project.service.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.UserDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.database.dao.impl.TransactionManager;
import epam.project.entity.User;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService implements Service {

    private static Logger LOGGER = Logger.getLogger(UserService.class.getName());

    public UserService() {
    }

    public List<User> takeAll() throws ServiceException {

        List<User> users;
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            users = userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException("Failed to get all users", e);
        }
        return users;
    }

    public boolean register(User user) throws ServiceException {

        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);

            userDao.persist(user);

        } catch (DaoException e) {
            throw new ServiceException("Failed to register user", e);
        }
        return true;

    }

    public User signIn(String login, String password) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            User user = userDao.getByLogin(login);
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new ServiceException("Wrong password");
            }
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to sign in user.", e);
        }
    }

    public boolean delete(User user) throws ServiceException {

        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            userDao.delete(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete user", e);
        }
        return true;
    }

    public User getById(int id) throws ServiceException {

        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.getByPK(id);
        } catch (DaoException  | PersistException e) {
            throw new ServiceException("Failed to delete user", e);
        }
    }

    public boolean update(User user) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        try {
            UserDao entityDao = (UserDao) JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            transactionManager.begin((AbstractJdbcDao) entityDao);

            user.setPassword((entityDao).getByLogin(user.getLogin()).getPassword());
            user.setRegistrationDate((entityDao).getByLogin(user.getLogin()).getRegistrationDate());

            entityDao.update(user);
            transactionManager.commit();
            transactionManager.end();
            return true;
        } catch (DaoException | PersistException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException d) {
                LOGGER.error("Failed to rollback",e);
            }
            LOGGER.error("Failed to update user",e);
            throw new ServiceException("Failed to update user", e);

        }


    }

    public User takeUser(String login) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.getByLogin(login);
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to take user.", e);
        }

    }



    public boolean checkLoginExistence(String login) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.checkLoginExistance(login);

        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to check contains user", e);
        }

    }

    public boolean checkEmailExistence(String email) throws ServiceException {

        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.checkEmailExistance(email);

        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to check contains user", e);
        }

    }

}

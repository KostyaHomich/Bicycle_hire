package epam.project.service.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.UserDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.dao.impl.JdbcDaoFactory;
import epam.project.entity.User;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;

import org.apache.log4j.Logger;

import java.sql.SQLException;
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
        } catch (DaoException | SQLException | PersistException e) {
            throw new ServiceException("Failed to delete user", e);
        }
    }

    public boolean update(User user) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to update user", e);
        }
        return true;
    }

    public User takeUser(String login) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.getByLogin(login);
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to take user.", e);
        }

    }

    public boolean contains(User user) throws ServiceException {

        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.contains(user);

        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to check contains user", e);
        }

    }

    public boolean checkLoginExistance(String login) throws ServiceException {
        try {

            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.checkLoginExistance(login);

        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to check contains user", e);
        }

    }

    public boolean checkEmailExistance(String email) throws ServiceException {

        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.checkEmailExistance(email);

        } catch (DaoException | PersistException e) {
            throw new ServiceException("Failed to check contains user", e);
        }

    }

}

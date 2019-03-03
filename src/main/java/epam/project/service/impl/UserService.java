package epam.project.service.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.UserDao;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.entity.User;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.UserValidator;
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
            users = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class).getAll();
        } catch (DaoException  e) {
            throw new ServiceException("Failed to get all users", e);
        }
        return users;
    }

    public boolean register(User user) throws ServiceException {

        try {
            UserValidator userValidator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);
            if (userValidator.doValidate(user)) {
                EntityDao<User, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
                dao.persist(user);
            } else {
                return false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Failed to register user", e);
        }
        return true;

    }

    public User signIn(String login,String password) throws ServiceException {
        try {

            UserDao userDao = (UserDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
            User user = userDao.getByLogin(login);
            LOGGER.info(" "+user.getPassword() +"   " +password);
            if (user.getPassword().equals(password)) {
                return user;
            }
            else {
                throw new ServiceException("No such user: " + login);
            }
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to sign in user.", e);
        }
    }

    public boolean delete(User user) throws ServiceException {

        try {
            UserDao userDao = (UserDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
            userDao.delete(user);
        } catch ( DaoException e) {
            throw new ServiceException("Failed to delete user", e);
        }
        return true;
    }

    public boolean update(User user) throws ServiceException {
        try {
            UserDao userDao = (UserDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to delete user", e);
        }
        return true;
    }

    public User takeUser(String login) throws ServiceException {
        try {
            UserDao userDao = (UserDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
            return userDao.getByLogin(login);
        } catch (PersistException | DaoException e) {
            throw new ServiceException("Failed to take user.", e);
        }

    }

    public boolean contains(User user) throws ServiceException {

        try {
            UserDao userDao = (UserDao) FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
            return  userDao.contains(user);

        } catch (DaoException |PersistException e) {
            throw new ServiceException("Failed to check contains user", e);
        }

    }
}

package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.entity.User;

public interface UserDao extends EntityDao<User, Integer> {
    User getByLogin(String login) throws DaoException, PersistException;
    boolean contains(User user) throws DaoException, PersistException;
    boolean checkLoginExistance(String login) throws DaoException, PersistException;
    boolean checkEmailExistance(String email) throws DaoException, PersistException;

}

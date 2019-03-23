package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;
import epam.project.entity.Order;
import epam.project.entity.User;

import java.util.List;

public interface UserDao extends EntityDao<User, Integer> {
    User getByLogin(String login) throws DaoException;
    List<User> getUsers(int count) throws DaoException;
    boolean checkLoginExistance(String login) throws DaoException;
    boolean checkEmailExistance(String email) throws DaoException;

}

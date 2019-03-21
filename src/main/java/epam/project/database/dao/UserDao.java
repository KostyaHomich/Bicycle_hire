package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;
import epam.project.entity.User;

public interface UserDao extends EntityDao<User, Integer> {
    User getByLogin(String login) throws DaoException;

    boolean checkLoginExistance(String login) throws DaoException;
    boolean checkEmailExistance(String email) throws DaoException;

}

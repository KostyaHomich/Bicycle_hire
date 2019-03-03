package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface EntityDao<T extends Identified<PK>, PK extends Serializable> {

     @AutoConnection
     T persist(T object) throws  DaoException;
     @AutoConnection
     T getByPK(PK key) throws DaoException, SQLException, PersistException;
     @AutoConnection
     void update(T object) throws  DaoException;
     @AutoConnection
     void delete(T object) throws  DaoException;
     @AutoConnection
     List<T> getAll() throws  DaoException;
}

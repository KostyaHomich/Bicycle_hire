package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;

import java.io.Serializable;

public interface DaoFactory {
    <T extends Identified<PK>, PK extends Serializable> EntityDao getDao(Class<T> entityClass) throws DaoException;
}

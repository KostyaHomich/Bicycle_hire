package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;

import java.io.Serializable;

public interface TransactionalDaoFactory {
    <T extends Identified<PK>, PK extends Serializable> EntityDao getTransactionalDao(Class<T> entityClass) throws DaoException;
}

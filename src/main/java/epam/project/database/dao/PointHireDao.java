package epam.project.database.dao;

import epam.project.database.dao.exception.DaoException;
import epam.project.entity.PointHire;

public interface PointHireDao extends EntityDao<PointHire, Integer>{
    boolean containsPointHire(int id) throws DaoException;
}

package epam.project.database.dao;


import epam.project.database.dao.exception.DaoException;

import epam.project.dto.PointHireBicycle;
import epam.project.entity.Bicycle;


public interface BicycleDao extends EntityDao<Bicycle, Integer> {
    boolean addPointHireBicycle(Bicycle bicycle) throws DaoException;
    PointHireBicycle getByBicyclePkPointHireBicycle(int id) throws DaoException;

}

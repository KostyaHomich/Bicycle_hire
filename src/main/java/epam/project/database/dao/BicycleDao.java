package epam.project.database.dao;


import epam.project.database.dao.exception.DaoException;
import epam.project.dto.PointHireBicycle;
import epam.project.entity.Bicycle;

import java.util.List;


public interface BicycleDao extends EntityDao<Bicycle, Integer> {
    void addPointHireBicycle(Bicycle bicycle) throws DaoException;
    PointHireBicycle getByPkPointHireBicycle(int id) throws DaoException;

    PointHireBicycle getByBicyclePkPointHireBicycle(int id) throws DaoException;
    boolean containsBicycle(int id) throws DaoException;
    List<Bicycle> getAllBicycleByPointHirePk(int id) throws DaoException;

    List<Bicycle> getAllAvailableBicycleByPointHirePk(int id) throws DaoException;

    List<Bicycle> getAllAvailableBicycle() throws DaoException;
}

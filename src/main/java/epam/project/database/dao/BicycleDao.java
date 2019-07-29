package epam.project.database.dao;


import epam.project.database.dao.exception.DaoException;
import epam.project.dto.PointHireBicycle;
import epam.project.entity.Bicycle;
import epam.project.entity.User;

import java.util.List;


public interface BicycleDao extends EntityDao<Bicycle, Integer> {

    void addPointHireBicycle(Bicycle bicycle) throws DaoException;

    PointHireBicycle getByBicyclePkPointHireBicycle(int id) throws DaoException;

    boolean containsBicycle(int id) throws DaoException;

    List<Bicycle> getAllBicycleByPointHirePk(int id) throws DaoException;

    List<Bicycle> getBicycles(int count) throws DaoException;

    void addBestBicycle(PointHireBicycle pointHireBicycle, User user) throws DaoException;

    void deleteBestBicycle(int id) throws DaoException;

    List<Bicycle> getAllBestBicyclesByUserId(int id) throws DaoException;

    int getBestBicycleIdByBicycleId(int id) throws DaoException;

}

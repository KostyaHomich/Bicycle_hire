package epam.project.database.dao.impl;


import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.exception.DaoException;
import epam.project.database.dao.exception.PersistException;
import epam.project.database.pool.ConnectionPoolImpl;
import epam.project.entity.Bicycle;

import epam.project.entity.PointHire;
import epam.project.entity.User;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BicycleDaoTest {

    private EntityDao bicycleDao;
    private Bicycle bicycle;
    private Connection connection;

    @Before
    public void init() throws Throwable {
        InitBdTest.bdInit();

        bicycleDao= FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class);
        bicycle=new Bicycle();
        bicycle.setId(1);
        bicycle.setDaily_rental_price(new BigDecimal(4));
        bicycle.setStatus("available");
        bicycle.setName("Focus");
        bicycle.setDescription("Color:Red,Condition:bad,Number of speeds:6,Type:Electric bicycle");
        connection = ConnectionPoolImpl.getInstance().getConnection();
    }

    @Test
    public void persistTest() throws Exception {

        Bicycle updated= (Bicycle) bicycleDao.persist(bicycle);
        Assert.assertEquals(bicycle.getDescription(),updated.getDescription());
    }

    @Test
    public void updateTest() throws Exception {

        Bicycle updated= (Bicycle) bicycleDao.persist(bicycle);
        updated.setDescription("abs");
        bicycleDao.update(updated);
        Bicycle bicycle=(Bicycle) bicycleDao.getByPK(updated.getId());
        Assert.assertEquals(updated.getDescription(),bicycle.getDescription());
    }

    @Test
    public void deleteTest() throws Exception {

        Bicycle deleted = (Bicycle)bicycleDao.persist(bicycle);
        bicycleDao.delete(deleted);
        Assert.assertFalse(bicycleDao.getAll().stream().findAny().isPresent());


    }

    @Test
    public void getByPKTest() throws Exception {

        Bicycle bicycleWithPk =(Bicycle) bicycleDao.persist(bicycle);
        Bicycle bicycle=(Bicycle)bicycleDao.getByPK(bicycleWithPk.getId());
        Assert.assertEquals(bicycleWithPk.getDescription(),bicycle.getDescription());
    }

    @Test
    public void getAllTest() throws Exception {

        bicycleDao.persist(bicycle);
        Assert.assertEquals(bicycleDao.getAll().size()>=1,true);
    }


    @After
    public void destroy() throws Exception {
        connection.close();
    }



}
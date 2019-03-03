package epam.project.database.dao.impl;

import epam.project.database.dao.AbstractJdbcDao;
import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.pool.ConnectionPoolImpl;
import epam.project.entity.PointHire;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.PreparedStatement;


@RunWith(JUnit4.class)
public class PointHireDaoTest {

    private EntityDao pointHireDao;
    private PointHire pointHire;
    private Connection connection;

    @Before
    public void init() throws Throwable {
        InitBdTest.bdInit();
        pointHireDao= FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);

        pointHire=new PointHire();
        pointHire.setId(1);
        pointHire.setLocation("1070 Doe Crossing Center");
        pointHire.setTelephone("+32 529 585 0067");
        pointHire.setDescription("Working time: 8:00 - 179:00");
        connection = ConnectionPoolImpl.getInstance().getConnection();

    }

    @Test
    public void persistTest() throws Exception {

        PointHire updated= (PointHire) pointHireDao.persist(pointHire);
        Assert.assertEquals(pointHire.getLocation(),updated.getLocation());
    }

    @Test
    public void updateTest() throws Exception {

        PointHire updated= (PointHire) pointHireDao.persist(pointHire);
        updated.setLocation("abs");
        updated.setDescription("abs");
        pointHireDao.update(updated);
        PointHire pointHire=(PointHire) pointHireDao.getByPK(updated.getId());
        Assert.assertEquals(updated,pointHire);
    }

    @Test
    public void deleteTest() throws Exception {

        PointHire deleted = (PointHire)pointHireDao.persist(pointHire);
        pointHireDao.delete(deleted);
        Assert.assertFalse(pointHireDao.getAll().stream().findAny().isPresent());


    }

    @Test
    public void getByPKTest() throws Exception {

        PointHire pointHireWithPK =(PointHire) pointHireDao.persist(pointHire);
        PointHire pointHire=(PointHire)pointHireDao.getByPK(pointHireWithPK.getId());
        Assert.assertEquals(pointHireWithPK.getLocation(),pointHire.getLocation());
    }

    @Test
    public void getAllTest() throws Exception {

        pointHireDao.persist(pointHire);
        Assert.assertEquals(pointHireDao.getAll().size()>=1,true);
    }


    @After
    public void destroy() throws Exception {
        connection.close();
    }

}
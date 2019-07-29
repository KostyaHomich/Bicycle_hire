package epam.project.database.dao.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.exception.DaoException;
import epam.project.entity.PointHire;
import epam.project.entity.PointHire;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.junit.runner.RunWith;

import java.awt.*;
import java.math.BigDecimal;

@RunWith(JUnit4.class)
public class PointHireDaoImplTest {
    private PointHire testPointHire;

    @Before
    public void initializePointHire() {
        testPointHire = new PointHire();

        testPointHire.setDescription("A");
        testPointHire.setTelephone("+375447031096");
        testPointHire.setLocation("A");
    }

    @Test
    public void persistPointHire() throws DaoException {
        EntityDao<PointHire, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);
        PointHire actual = dao.persist(testPointHire);

        testPointHire.setId(0);
        Assert.assertEquals(testPointHire, actual);
    }

    @Test
    public void getPointHireByPK() throws DaoException {
        EntityDao<PointHire, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);
        PointHire expected = dao.persist(testPointHire);
        Integer id = expected.getId();

        PointHire actual = dao.getByPK(id);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void updatePointHire() throws DaoException {
        EntityDao<PointHire, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);
        PointHire expected = dao.persist(testPointHire);

        Integer id = expected.getId();
        expected.setDescription("nonMafiosi");
        dao.update(expected);

        PointHire actual = dao.getByPK(id);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deletePointHire() throws DaoException {
        EntityDao<PointHire, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);

        PointHire expected = dao.persist(testPointHire);

        Integer id = expected.getId();
        dao.delete(expected);
        PointHire PointHire = dao.getByPK(id);


        Assert.assertEquals(new PointHire(), PointHire);
    }

}

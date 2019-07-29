package epam.project.database.dao.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.exception.DaoException;
import epam.project.entity.Bicycle;
import epam.project.entity.PointHire;
import epam.project.entity.Bicycle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BicycleDaoImplTest {
    private Bicycle testBicycle;

    @Before
    public void initializeBicycle() {
        testBicycle = new Bicycle();


        testBicycle.setDescription("A");

        testBicycle.setPoint_hire_id(1);
    }

    @Test
    public void persistBicycle() throws DaoException {
        EntityDao<Bicycle, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class);
        Bicycle actual = dao.persist(testBicycle);

        testBicycle.setId(0);
        Assert.assertEquals(testBicycle, actual);
    }

    @Test
    public void getBicycleByPK() throws DaoException {
        EntityDao<Bicycle, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class);

        Bicycle expected = dao.persist(testBicycle);
        Integer id = expected.getId();

        Bicycle actual = dao.getByPK(id);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void updateBicycle() throws DaoException {
        EntityDao<Bicycle, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class);
        Bicycle expected = dao.persist(testBicycle);

        Integer id = expected.getId();
        expected.setDescription("nonMafiosi");
        dao.update(expected);

        Bicycle actual = dao.getByPK(id);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteBicycle() throws DaoException {
        EntityDao<Bicycle, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class);
        Bicycle expected = dao.persist(testBicycle);

        Integer id = expected.getId();
        dao.delete(expected);
        Bicycle bicycle = dao.getByPK(id);


        Assert.assertEquals(new Bicycle(), bicycle);
    }

}

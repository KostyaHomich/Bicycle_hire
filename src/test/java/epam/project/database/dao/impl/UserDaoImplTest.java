package epam.project.database.dao.impl;

import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.dao.exception.DaoException;
import epam.project.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.JUnit4;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UserDaoImplTest {
    private User testUser;

    @Before
    public void initializeUser() {
        testUser = new User();

        testUser.setLogin("mafiosi");
        testUser.setPassword("JDUF7RUTI6JHUGYT7586UTJGHRY5746RYFHGURJT");
        testUser.setEmail("hyperemail2019@gmail.com");
        testUser.setFirstName("John");
        testUser.setLastName("Philips");
        testUser.setStatus("active");
        testUser.setRegistrationDate("2019-01-08 01:52:00");
    }

    @Test
    public void persistUser() throws DaoException {
        EntityDao<User, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
        User actual = dao.persist(testUser);

        testUser.setId(0);
        Assert.assertEquals(testUser, actual);
    }

    @Test
    public void getUserByPK() throws DaoException {
        EntityDao<User, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
        testUser.setLogin("AAAA");
        testUser.setEmail("aaaa.@gmail.com");
        testUser.setRole("USER");
        User expected = dao.persist(testUser);
        Integer id = expected.getId();

        User actual = dao.getByPK(id);

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void updateUser() throws DaoException {
        EntityDao<User, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
        User expected = dao.persist(testUser);

        testUser.setLogin("BBBB");
        testUser.setEmail("bbbb.@gmail.com");
        testUser.setRole("USER");

        Integer id = expected.getId();
        expected.setLogin("nonMafiosi");
        dao.update(expected);

        User actual = dao.getByPK(id);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteUser() throws DaoException {
        EntityDao<User, Integer> dao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);

        testUser.setLogin("CCCC");
        testUser.setEmail("cccc.@gmail.com");
        testUser.setRole("USER");

        User expected = dao.persist(testUser);

        Integer id = expected.getId();
        dao.delete(expected);
        User user = dao.getByPK(id);


        Assert.assertEquals(new User(), user);
    }

}

package epam.project.database.dao.impl;

import epam.project.database.dao.*;
import epam.project.database.pool.ConnectionPoolImpl;
import epam.project.entity.PointHire;
import epam.project.entity.User;
import junit.framework.Assert;
import org.junit.Test;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UserDaoImplTest {
    private User user;
    private EntityDao<User,Integer> userDao;
    private AbstractJdbcDao daoWithAbstractMethods;
    private Connection connection;

    @Before
    public void init() throws Throwable {

        daoWithAbstractMethods= (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
        InitBdTest.bdInit();
        userDao= FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);

        user = new User();
        user.setId(1);
        user.setLogin("kokotik234");
        user.setPassword("9cbb3b8fefae300c09f9eedb16c8b39bea9a416412ef76ed4b0a4d8898cd9924");
        user.setFirstName("Vinnie");
        user.setLastName("Meir");
        user.setStatus("not confirmed");
        user.setRegistrationDate("2018-09-14 16:16:48");
        user.setBalance(new BigDecimal(3996));
        user.setEmail("absd@gmail.com");
        user.setRole("user");
        connection = ConnectionPoolImpl.getInstance().getConnection();
        //deleteAll = connection.prepareStatement("DELETE FROM user WHERE id<200");
    }

    @Test
    public void getCreateQuery() {
        Assert.assertEquals("insert into user values (NULL ,?,?,?, ?,?,?, ?,?,?);", daoWithAbstractMethods.getCreateQuery());
    }

    @Test
    public void getUpdateQuery() {
        Assert.assertEquals("update user set login=?,password=?," +
                "first_name=?,last_name=?,status=?,balance=?,email=?,id_Role=? where" +
                " id=?", daoWithAbstractMethods.getUpdateQuery());
    }

    @Test
    public void getDeleteQuery() {
        Assert.assertEquals("DELETE FROM user WHERE id=?", daoWithAbstractMethods.getDeleteQuery());
    }

    @Test
    public void persistTest() throws Exception {
        //deleteAll.execute();
        User updated= userDao.persist(user);
        Assert.assertEquals(user.getLastName(),updated.getLastName());
    }

    @Test
    public void updateTest() throws Exception {
        //deleteAll.execute();
        User updated= userDao.persist(user);
        updated.setLastName("LastName");
        userDao.update(updated);
        Assert.assertEquals(updated.getLastName(),((User) userDao.getByPK(updated.getId())).getLastName());
    }

    @Test
    public void deleteTest() throws Exception {
       // deleteAll.execute();
        User deleted = userDao.persist(user);
        userDao.delete(deleted);
        Assert.assertFalse(userDao.getAll().stream().findAny().isPresent());


    }

    @Test
    public void getByPKTest() throws Exception {
       // deleteAll.execute();
        User userWithPK =userDao.persist(user);
        User user=userDao.getByPK(userWithPK.getId());
        Assert.assertEquals(userWithPK.getLogin(),user.getLogin());
    }

    @Test
    public void getAllTest() throws Exception {
       // deleteAll.execute();
       userDao.persist(user);
        Assert.assertEquals(userDao.getAll().size()>=1,true);
    }

    @After
    public void destroy() throws Exception {
        connection.close();
    }


}
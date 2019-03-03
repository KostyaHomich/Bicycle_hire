package epam.project.database.dao.impl;


import epam.project.database.dao.DaoFactoryType;
import epam.project.database.dao.EntityDao;
import epam.project.database.dao.FactoryProducer;
import epam.project.database.pool.ConnectionPoolImpl;
import epam.project.entity.Bicycle;
import epam.project.entity.Order;
import epam.project.entity.PointHire;
import epam.project.entity.User;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.*;

public class OrderDaoTest {
    private EntityDao orderDao;
    private Order order;
    private Connection connection;
    private EntityDao userDao;
    private User user;
    private PointHire pointHire;
    private EntityDao pointHireDao;
    private PreparedStatement addPointHireBicycle;
    private Bicycle bicycle;
    private EntityDao bicycleDao;


    @Before
    public void init() throws Throwable {
        InitBdTest.bdInit();


        orderDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Order.class);
        order = new Order();
        order.setId(1);
        order.setIdPointHireBicycle(1);
        order.setCost(new BigDecimal(30));
        order.setStatus("completed");
        order.setTimeOrder("2018-12-14 06:51:50");
        order.setRentalTime(12);
        order.setIdUser(1);

        userDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
        pointHireDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(PointHire.class);

        user = new User();
        user.setId(1);
        user.setLogin("vmeir1");
        user.setPassword("9cbb3b8fefae300c09f9eedb16c8b39bea9a416412ef76ed4b0a4d8898cd9924");
        user.setFirstName("Vinnie");
        user.setLastName("Meir");
        user.setStatus("not confirmed");
        user.setRegistrationDate("2018-09-14 16:16:48");
        user.setBalance(new BigDecimal(3996));
        user.setEmail("VinnieMeir@gmail.com");
        user.setRole("user");

        pointHire = new PointHire();
        pointHire.setId(1);
        pointHire.setLocation("1070 Doe Crossing Center");
        pointHire.setTelephone("+32 529 585 0067");
        pointHire.setDescription("Working time: 8:00 - 179:00");
        connection = ConnectionPoolImpl.getInstance().getConnection();


        bicycleDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(Bicycle.class);
        bicycle = new Bicycle();
        bicycle.setId(1);
        bicycle.setDaily_rental_price(new BigDecimal(4));
        bicycle.setStatus("available");
        bicycle.setName("Focus");
        bicycle.setDescription("Color:Red,Condition:bad,Number of speeds:6,Type:Electric bicycle");

        bicycle = (Bicycle) bicycleDao.persist(bicycle);
        connection = ConnectionPoolImpl.getInstance().getConnection();

        addPointHireBicycle = connection.prepareStatement("INSERT INTO " +
                " point_hire_bicycle values (NULL,?,?)", Statement.RETURN_GENERATED_KEYS);


    }


    @Test
    public void persistTest() throws Exception {


        user = (User) userDao.persist(user);
        pointHire = (PointHire) pointHireDao.persist(pointHire);
        bicycle = (Bicycle) bicycleDao.persist(bicycle);


        addPointHireBicycle.setInt(1, pointHire.getId());
        addPointHireBicycle.setInt(2, bicycle.getId());
        addPointHireBicycle.execute();
        ResultSet generatedKeys = addPointHireBicycle.getGeneratedKeys();
        if (generatedKeys.next()) {
            order.setIdUser(user.getId());
            order.setIdPointHireBicycle(generatedKeys.getInt(1));

            Order updated = (Order) orderDao.persist(order);
            Assert.assertEquals(order.getStatus(), updated.getStatus());
        }

    }

    @Test
    public void updateTest() throws Exception {

        user = (User) userDao.persist(user);
        pointHire = (PointHire) pointHireDao.persist(pointHire);
        bicycle = (Bicycle) bicycleDao.persist(bicycle);


        addPointHireBicycle.setInt(1, pointHire.getId());
        addPointHireBicycle.setInt(2, bicycle.getId());
        addPointHireBicycle.execute();
        ResultSet generatedKeys = addPointHireBicycle.getGeneratedKeys();

        if (generatedKeys.next()) {
            order.setIdUser(user.getId());
            order.setIdPointHireBicycle(generatedKeys.getInt(1));
            orderDao.persist(order);
            order.setStatus("finished ");
            orderDao.update(order);

            Assert.assertEquals(order.getStatus(), ((Order) orderDao.getByPK(order.getId())).getStatus());
        }

    }

    @Test
    public void deleteTest() throws Exception {

        user = (User) userDao.persist(user);
        pointHire = (PointHire) pointHireDao.persist(pointHire);
        bicycle = (Bicycle) bicycleDao.persist(bicycle);


        addPointHireBicycle.setInt(1, pointHire.getId());
        addPointHireBicycle.setInt(2, bicycle.getId());
        addPointHireBicycle.execute();
        ResultSet generatedKeys = addPointHireBicycle.getGeneratedKeys();

        if (generatedKeys.next()) {
            order.setIdUser(user.getId());
            order.setIdPointHireBicycle(generatedKeys.getInt(1));
            Order deleted = (Order) orderDao.persist(order);
            orderDao.delete(deleted);
            Assert.assertFalse(orderDao.getAll().stream().findAny().isPresent());
        }


    }

    @Test
    public void getByPKTest() throws Exception {

        user = (User) userDao.persist(user);
        pointHire = (PointHire) pointHireDao.persist(pointHire);
        bicycle = (Bicycle) bicycleDao.persist(bicycle);


        addPointHireBicycle.setInt(1, pointHire.getId());
        addPointHireBicycle.setInt(2, bicycle.getId());
        addPointHireBicycle.execute();
        ResultSet generatedKeys = addPointHireBicycle.getGeneratedKeys();

        if (generatedKeys.next()) {
            order.setIdUser(user.getId());
            order.setIdPointHireBicycle(generatedKeys.getInt(1));
            Order deleted = (Order) orderDao.persist(order);
            Order order = (Order) orderDao.getByPK(deleted.getId());
            Assert.assertEquals(deleted, order);
        }

    }

    @Test
    public void getAllTest() throws Exception {

        user = (User) userDao.persist(user);
        pointHire = (PointHire) pointHireDao.persist(pointHire);
        bicycle = (Bicycle) bicycleDao.persist(bicycle);


        addPointHireBicycle.setInt(1, pointHire.getId());
        addPointHireBicycle.setInt(2, bicycle.getId());
        addPointHireBicycle.execute();
        ResultSet generatedKeys = addPointHireBicycle.getGeneratedKeys();

        if (generatedKeys.next()) {
            order.setIdUser(user.getId());
            order.setIdPointHireBicycle(generatedKeys.getInt(1));
            orderDao.persist(order);

        }
        Assert.assertEquals(orderDao.getAll().size() >= 1, true);
    }

    @After
    public void destroy() throws Exception {
        connection.close();
    }


}
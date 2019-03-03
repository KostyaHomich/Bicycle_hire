package epam.project.service.impl;

import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import junit.framework.Assert;
import org.junit.Test;

import org.junit.Before;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService;
    private User user;
    @Before
    public void init() {
        user = new User();
        user.setId(1);
        user.setLogin("vmeir1");
        user.setPassword("9cbb3b8fefae300c09f9eedb16c8b39bea9a416412ef76ed4b0a4d8898cd9924");
        user.setFirstName("Vinnie");
        user.setLastName("Meir");
        user.setStatus("not confirmed");
        user.setRegistrationDate("2018-09-14 16:16:48");
        user.setBalance(new BigDecimal(3996));
        user.setEmail("absd@main.ru");
        user.setRole("user");
    }

    @Test
    public void takeAll() throws ServiceException {
        userService=new UserService();
        List<User> list= userService.takeAll();
        Assert.assertEquals(list.size()>=1,true);
    }

    @Test
    public void register() throws ServiceException {

        userService=new UserService();
        userService.register(user);
    }


}
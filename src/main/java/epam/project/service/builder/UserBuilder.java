package epam.project.service.builder;

import epam.project.controller.command.CommandRegisterUser;
import epam.project.entity.User;
import epam.project.service.HashGenerator;
import epam.project.service.ServiceFactory;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class UserBuilder implements Builder<User> {
    private static final String PASSWORD="password";
    private static final String LOGIN="login";
    private static final String EMAIL="email";
    private static final String FIST_NAME="first_name";
    private static final String LAST_NAME="last_name";
    private static final Logger LOGGER = LogManager.getLogger(CommandRegisterUser.class);
    @Override
    public User build(HttpServletRequest request) throws ServiceException, UnsupportedEncodingException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        HashGenerator hashGenerator = ServiceFactory.getInstance().getHashGenerator();
        User user=new User();

        Map params=request.getParameterMap();
        for (Object key: params.keySet())
        {
            String keyStr = (String)key;
            String[] value = (String[])params.get(keyStr);
            switch (keyStr) {
                case LOGIN:user.setLogin(value[0]);break;
                case PASSWORD:user.setPassword(hashGenerator.encode(value[0]));break;
                case FIST_NAME:user.setFirstName(value[0]);break;
                case LAST_NAME:user.setLastName(value[0]);break;
                case EMAIL:user.setEmail(value[0]);break;
                default:break;
            }

        }

        return user;
    }
}

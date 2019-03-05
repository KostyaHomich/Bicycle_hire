package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.builder.UserBuilder;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


public class CommandLogIn implements Command {
    private static final String ID = "id";
    private static final String ROLE = "role";
    private static final String LOGIN = "login";
    private static final Logger LOGGER = LogManager.getLogger(CommandLogIn.class);


    @Override
    public ResponseContent execute(HttpServletRequest request) {

        try {

            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            UserBuilder userBuilder = new UserBuilder();
            User user  = userBuilder.build(request);
            User signInUser = userService.signIn(user.getLogin(), user.getPassword());






            ResponseContent responseContent = new ResponseContent();

            Router router = new Router(CommandType.SHOW_MAIN_PAGE, Router.Type.FORWARD);
            request.getSession().setAttribute(ID, signInUser.getId());
            request.getSession().setAttribute(ROLE, signInUser.getRole());
            request.getSession().setAttribute(LOGIN, signInUser.getLogin());
            responseContent.setRouter(router);

            return responseContent;
        } catch (ServiceException | UnsupportedEncodingException e) {
            request.setAttribute("message", "Can't sigh in user.");
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(CommandType.SHOW_LOGIN_PAGE, Router.Type.FORWARD));
            return responseContent;
        }

    }
}

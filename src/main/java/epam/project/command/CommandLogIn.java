package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.builder.UserBuilder;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.validation.ValidationResult;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.UserValidator;
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
            ResponseContent responseContent = new ResponseContent();
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            UserBuilder userBuilder = new UserBuilder();
            UserValidator userValidator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);

            ValidationResult validationResult = userValidator.doValidate(request.getParameterMap());
            if (validationResult.getErrors().size()>0) {
                User user = userBuilder.build(request.getParameterMap());
                if (userService.contains(user)) {
                    User signInUser = userService.signIn(user.getLogin(), user.getPassword() + user.getLogin());

                    Router router = new Router(CommandType.SHOW_MAIN_PAGE, Router.Type.REDIRECT);

                    request.getSession().setAttribute(ID, signInUser.getId());
                    request.getSession().setAttribute(ROLE, signInUser.getRole());
                    request.getSession().setAttribute(LOGIN, signInUser.getLogin());

                    responseContent.setRouter(router);
                    return responseContent;

                } else {
                    throw new ServiceException("User with this login doesn't exist.");
                }

            } else {
                Router router = new Router("/WEB-INF/jsp/login.jsp", Router.Type.FORWARD);
                request.setAttribute("errorsList", validationResult);
                responseContent.setRouter(router);
                return responseContent;
            }
        } catch (ServiceException e) {

            request.setAttribute("error", e.getMessage());
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/WEB-INF/jsp/login.jsp", Router.Type.FORWARD));
            return responseContent;
        }

    }
}

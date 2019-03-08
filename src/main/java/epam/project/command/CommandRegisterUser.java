package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.HashGenerator;
import epam.project.service.RequestParameterParser;
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

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CommandRegisterUser implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandRegisterUser.class);

    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "repeat_password";


    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            ResponseContent responseContent = new ResponseContent();
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            UserValidator userValidator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);

            UserBuilder userBuilder = new UserBuilder();

            Map<String, String> parameters= RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = userValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                User user = userBuilder.build(parameters);
                if (userService.checkLoginExistance(user.getLogin())) {

                    String password = request.getParameter(PASSWORD);
                    String repeat_password = request.getParameter(REPEAT_PASSWORD);
                    user.setRegistrationDate(LocalDate.now().toString());

                    if (password.equals(repeat_password)) {
                        userService.register(user);
                        Router router = new Router("/WEB-INF/jsp/main.jsp", Router.Type.FORWARD);
                        responseContent.setRouter(router);
                        return responseContent;

                    } else {
                        throw new ServiceException("Passwords are not equals");
                    }
                }
                else {
                    throw new ServiceException("User with this login already exist");
                }
            } else {
                Router router = new Router("/WEB-INF/jsp/registration.jsp", Router.Type.FORWARD);
                request.setAttribute("errorsList", validationResult);
                responseContent.setRouter(router);
                return responseContent;
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/WEB-INF/jsp/registration.jsp", Router.Type.FORWARD));
            return responseContent;
        }
    }
}

package epam.project.controller.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.HashGenerator;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class CommandRegisterUser implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandRegisterUser.class);

    private static final String PASSWORD="password";
    private static final String REPEAT_PASSWORD="repeat_password";
    private static final String LOGIN="login";
    private static final String EMAIL="email";
    private static final String FIST_NAME="first_name";
    private static final String LAST_NAME="last_name";

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();

        try {
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            UserValidator validator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);
            HashGenerator hashGenerator=ServiceFactory.getInstance().getHashGenerator();

            String password = request.getParameter(PASSWORD);
            String repeat_password = request.getParameter(REPEAT_PASSWORD);
            String login=request.getParameter(LOGIN);

            User user = new User();
            user.setLogin(login);


            user.setPassword(hashGenerator.encode(password+login));
            user.setEmail(request.getParameter(EMAIL));
            user.setFirstName(request.getParameter(FIST_NAME));
            user.setLastName(request.getParameter(LAST_NAME));
            user.setRegistrationDate(LocalDate.now().toString());

            if (validator.doValidate(user) && password.equals(repeat_password) && !userService.contains(user)) {
                LOGGER.info("User was register.");
                userService.register(user);
                responseContent.setRouter(new Router(CommandType.SHOW_MAIN_PAGE.name(), Router.Type.REDIRECT));
            }

            return responseContent;
        } catch (ServiceException e) {
            LOGGER.info("Fail");
            request.setAttribute("error_message","All fields except e-mail must " +
                    "contain only letters, digits and underscore, e-mail must be valid and unique.");
            responseContent.setRouter(new Router(CommandType.SHOW_REGISTRATION_PAGE.name(), Router.Type.FORWARD));
            return responseContent;
        }
    }
}

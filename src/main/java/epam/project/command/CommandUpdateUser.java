package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CommandUpdateUser implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandUpdateUser.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {

            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            UserValidator userValidator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            UserBuilder userBuilder = new UserBuilder();

            ValidationResult validationResult = userValidator.doValidate(parameters);
            ResponseContent responseContent = new ResponseContent();
            if (validationResult.getErrors().size() == 0) {

                User user = userBuilder.build(parameters);
                if (userService.checkLoginExistance(user.getLogin())) {
                    userService.update(user);
                    Router router = new Router("/WEB-INF/jsp/user_list.jsp", Router.Type.FORWARD);
                    responseContent.setRouter(router);
                    return responseContent;

                } else {
                    throw new ServiceException("User with this login already not exist");
                }
            } else {
                Router router = new Router("/WEB-INF/jsp/user_details.jsp", Router.Type.FORWARD);
                request.setAttribute("errorsList", validationResult);
                responseContent.setRouter(router);
                return responseContent;
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/WEB-INF/jsp/user_details.jsp", Router.Type.FORWARD));
            return responseContent;
        }

    }
}

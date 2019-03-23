package epam.project.command;

import epam.project.builder.UserBuilder;
import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.entity.UserRole;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.util.RequestParameterParser;
import epam.project.validation.ValidationResult;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import epam.project.validation.impl.ContainsValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


public class CommandLogIn implements Command {
    private static final String USER = "signInUser";
    private static final Logger LOGGER = LogManager.getLogger(CommandLogIn.class);


    @Override
    public ResponseContent execute(HttpServletRequest request) {

        try {
            ResponseContent responseContent = new ResponseContent();
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            UserBuilder userBuilder = new UserBuilder();
            ContainsValidator userValidator = (ContainsValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.LOGIN);

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = userValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {
                User user = userBuilder.build(parameters);
                User signInUser = userService.signIn(user.getLogin(), user.getPassword());
                if (!signInUser.getStatus().equalsIgnoreCase("banned")) {

                    request.getSession().setAttribute(USER, signInUser);

                    if (signInUser.getRole().equalsIgnoreCase(UserRole.ADMIN.name())) {
                        Router router = new Router(PageConst.USER_PAGE_PATH, Router.Type.FORWARD);
                        responseContent.setRouter(router);
                        return responseContent;
                    } else {
                        Router router = new Router(PageConst.USER_PAGE_PATH, Router.Type.FORWARD);
                        responseContent.setRouter(router);
                        return responseContent;
                    }
                } else {
                    request.setAttribute("error","user.error.banned");
                    responseContent = new ResponseContent();
                    responseContent.setRouter(new Router(PageConst.LOGIN_PAGE_PATH, Router.Type.FORWARD));
                    return responseContent;
                }
            } else {
                Router router = new Router(PageConst.LOGIN_PAGE_PATH, Router.Type.FORWARD);
                request.setAttribute("errorsList", validationResult);
                responseContent.setRouter(router);
                return responseContent;
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.LOGIN_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }

    }
}

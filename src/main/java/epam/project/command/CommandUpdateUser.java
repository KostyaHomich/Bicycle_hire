package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
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
            request.setAttribute("entity", EntityType.USER);

            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            UserValidator userValidator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            UserBuilder userBuilder = new UserBuilder();

            ValidationResult validationResult = userValidator.doValidate(parameters);
            ResponseContent responseContent = new ResponseContent();
            if (validationResult.getErrors().size() == 0) {
                userService.update(userBuilder.build(parameters));
                Router router = new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD);
                responseContent.setRouter(router);
                return responseContent;


            } else {
                Router router = new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD);
                request.setAttribute("errorsList", validationResult);
                responseContent.setRouter(router);
                return responseContent;
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "Error: failed to update user");
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }

    }
}

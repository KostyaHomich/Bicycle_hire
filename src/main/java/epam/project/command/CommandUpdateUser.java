package epam.project.command;

import epam.project.builder.UserBuilder;
import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.util.RequestParameterParser;
import epam.project.util.ResponseContentBuilder;
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
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            UserValidator userValidator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);

            UserBuilder userBuilder = new UserBuilder();

            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            ValidationResult validationResult = userValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                User user = userBuilder.build(parameters);
                System.out.println("HOBA");
                userService.update(user);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_USER_LIST,request);

            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_USER_DETAILS,request);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to update user", e);
            request.setAttribute("error", "user.error.update_user");
            throw new CommandException("Failed to update user");
        }
    }
}

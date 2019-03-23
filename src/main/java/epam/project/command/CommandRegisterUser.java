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
import java.time.LocalDate;
import java.util.Map;

public class CommandRegisterUser implements Command {

    private static final String PASSWORD = "password";
    private static final String REPEAT_PASSWORD = "repeat_password";


    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            Map<String, String> parameters = RequestParameterParser.parseParameters(request);
            UserValidator userValidator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);

            UserBuilder userBuilder = new UserBuilder();


            ValidationResult validationResult = userValidator.doValidate(parameters);

            if (validationResult.getErrors().size() == 0) {

                User user = userBuilder.build(parameters);
                if (!userService.checkLoginExistence(user.getLogin())) {

                    String password = request.getParameter(PASSWORD);
                    String repeat_password = request.getParameter(REPEAT_PASSWORD);
                    user.setRegistrationDate(LocalDate.now().toString());

                    if (password.equals(repeat_password)) {

                        userService.register(user);
                        return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_MAIN_PAGE, request);

                    } else {
                        request.setAttribute("error", "user.error.password_are_not_equals");
                        return ResponseContentBuilder.buildForwardResponseContent(PageConst.REGISTRATION_PAGE_PATH);
                    }
                } else {
                    request.setAttribute("errorsList", "user.error.login_already_taken");
                    return ResponseContentBuilder.buildForwardResponseContent(PageConst.REGISTRATION_PAGE_PATH);
                }
            } else {
                request.setAttribute("errorsList", validationResult);
                return ResponseContentBuilder.buildForwardResponseContent(PageConst.REGISTRATION_PAGE_PATH);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "user.error.register_user");
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.REGISTRATION_PAGE_PATH);
        }
    }
}

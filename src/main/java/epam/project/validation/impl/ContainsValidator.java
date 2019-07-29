package epam.project.validation.impl;

import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.validation.ValidationResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

public class ContainsValidator {



    private static final String LOGIN = "login";
    private static final String EMAIL = "email";


    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    public ValidationResult doValidate(Map<String, String> params) throws ServiceException {

        ValidationResult validationResult;
        UserValidator validator = new UserValidator();

        validationResult = validator.doValidate(params);
        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case LOGIN:
                    checkLogin(validationResult, value);
                    break;
                case EMAIL:
                    checkEmail(validationResult, value);
                    break;
                default:
                    break;
            }

        }
        return validationResult;
    }

    private ValidationResult checkLogin(ValidationResult validationResult, String value) throws ServiceException {
        UserService userService = new UserService();
        ArrayList<String> errors = new ArrayList<>();
        if (!userService.checkLoginExistence(value)) {
            errors.add("user.error.login_not_exist");
          validationResult.add(LOGIN,errors);
        }
        return validationResult;
    }
    private ValidationResult checkEmail(ValidationResult validationResult, String value) throws ServiceException {
        UserService userService = new UserService();
        ArrayList<String> errors = new ArrayList<>();
        if (!userService.checkEmailExistence(value)) {
            errors.add("user.error.email_not_exist");
            validationResult.add(EMAIL,errors);
        }
        return validationResult;
    }
}

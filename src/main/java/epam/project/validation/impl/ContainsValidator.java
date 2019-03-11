package epam.project.validation.impl;

import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.validation.ValidationResult;
import epam.project.validation.Validator;
import epam.project.validation.ValidatorFactory;
import epam.project.validation.ValidatorType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

public class ContainsValidator implements Validator {



    private static final String LOGIN = "login";
    private static final String EMAIL = "email";


    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    public ValidationResult doValidate(Map<String, String> params) throws ServiceException {

        ValidationResult validationResult;
        UserValidator validator = (UserValidator) ValidatorFactory.getInstance().getValidator(ValidatorType.USER);

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
        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
        ArrayList<String> errors = new ArrayList<>();
        if (!userService.checkLoginExistance(value)) {
            errors.add("User with this login doesn't exist");
          validationResult.add("login",errors);
        }
        return validationResult;
    }
    private ValidationResult checkEmail(ValidationResult validationResult, String value) throws ServiceException {
        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
        ArrayList<String> errors = new ArrayList<>();
        if (!userService.checkEmailExistance(value)) {
            errors.add("User with this email doesn't exist");
            validationResult.add("email",errors);
        }
        return validationResult;
    }
}

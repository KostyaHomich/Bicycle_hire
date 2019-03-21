package epam.project.validation.impl;

import epam.project.validation.ValidationResult;
import epam.project.validation.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class UserValidator implements Validator {

    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String BALANCE = "balance";
    private static final String EMAIL = "email";
    private static final String FIST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    private static final String CHECK_EMAIL = "([\\w-\\.]+)@\\D((?:[\\w]+\\.)+)([a-zA-Z]{2,4}) ";
    private static final String CHECK_NAME = "[a-zA-Z]";
    private static final String SPACE = " ";
    private static final int MAX_EMAIL_LENGTH = 35;
    private static final int MIN_EMAIL_LENGTH = 4;
    private static final int MAX_FIRST_NAME_LENGTH = 15;
    private static final int MIN_FIRST_NAME_LENGTH = 3;
    private static final int MAX_LAST_NAME_LENGTH = 15;
    private static final int MIN_LAST_NAME_LENGTH = 3;
    private static final int MIN_LOGIN_LENGTH = 5;
    private static final int MAX_LOGIN_LENGTH = 15;
    private static final int MIN_PASSWORD_LENGTH = 7;
    private static final int MAX_PASSWORD_LENGTH = 25;


    public ValidationResult doValidate(Map<String,String> params)  {

        ValidationResult validationResult = new ValidationResult();

        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case LOGIN:
                    validateLogin(validationResult, value);
                    break;
                case PASSWORD:
                    validatePassword(validationResult, value);
                    break;
                case FIST_NAME:
                    validateFirstName(validationResult, value);
                    break;
                case LAST_NAME:
                    validateLastName(validationResult, value);
                    break;
                case EMAIL:
                    validateEmail(validationResult, value);
                    break;
                case BALANCE:
                    validateBalance(validationResult,value);
                    break;
                default:
                    break;
            }

        }
        return validationResult;
    }

    private void validateLogin(ValidationResult validationResult, String login) {
        ArrayList<String> errors = new ArrayList<>();

        if (login.length() <= MIN_LOGIN_LENGTH) {
            errors.add("user.error.min_login_length");
        }
        if (login.length() > MAX_LOGIN_LENGTH) {
            errors.add("user.error.max_login_length");
        }
        if (login.contains(SPACE)) {
            errors.add("user.error.login_empty_space");
        }
        if (errors.size() > 0) {
            validationResult.add("login", errors);
        }
    }

    private void validateEmail(ValidationResult validationResult, String email)  {

        ArrayList<String> errors = new ArrayList<>();

        if (email.length() <= MIN_EMAIL_LENGTH) {
            errors.add("user.error.min_email_length");
        }
        if (email.length() > MAX_EMAIL_LENGTH) {
            errors.add("user.error.max_email_length");
        }
        if (email.matches(CHECK_EMAIL)) {
            errors.add("user.error.invalid_email");
        }
        if (email.contains(SPACE)) {
            errors.add("user.error.email_empty_space");
        }
        if (errors.size() > 0) {
            validationResult.add(EMAIL, errors);
        }
    }

    private void validateFirstName(ValidationResult validationResult, String name) {
        ArrayList<String> errors = new ArrayList<>();
        if (name.length() <= MIN_FIRST_NAME_LENGTH) {
            errors.add("user.error.min_first_name_length");
        }
        if (name.length() > MAX_FIRST_NAME_LENGTH) {
            errors.add("user.error.max_first_name_length");
        }
        if (name.matches(CHECK_NAME)) {
            errors.add("user.error.invalid_first_name");
        }
        if (name.contains(SPACE)) {
            errors.add("user.error.first_name_empty_space");
        }
        if (errors.size() > 0) {
            validationResult.add(FIST_NAME, errors);
        }
    }

    private void validateLastName(ValidationResult validationResult, String name) {
        ArrayList<String> errors = new ArrayList<>();
        if (name.length() <= MIN_LAST_NAME_LENGTH) {
            errors.add("user.error.min_last_name_length");
        }
        if (name.length() > MAX_LAST_NAME_LENGTH) {
            errors.add("user.error.max_last_name_length");
        }
        if (name.matches(CHECK_NAME)) {
            errors.add("user.error.invalid_last_name");
        }
        if (name.contains(SPACE)) {
            errors.add("user.error.last_name_empty_space");
        }
        if (errors.size() > 0) {
            validationResult.add(LAST_NAME, errors);
        }
    }

    private void validateBalance(ValidationResult validationResult, String value) {
        ArrayList<String> errors = new ArrayList<>();
        try {
            BigDecimal balance= BigDecimal.valueOf(Integer.valueOf(value));
            if(balance.intValue()<0) {
                errors.add("user.error.balance_more_then_0");
            }
        }
        catch (NumberFormatException e){
        errors.add("user.error.invalid_balance");
        }
        if (errors.size() > 0) {
            validationResult.add(BALANCE, errors);
        }
    }

    private void validatePassword(ValidationResult validationResult, String password) {
        ArrayList<String> errors = new ArrayList<>();

        if (password.length() <= MIN_PASSWORD_LENGTH) {
            errors.add("user.error.min_password_length");
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            errors.add("user.error.max_password_length");
        }
        if (password.contains(SPACE)) {
            errors.add("user.error.password_empty_space");
        }
        if (errors.size() > 0) {
            validationResult.add(PASSWORD, errors);
        }
    }

}

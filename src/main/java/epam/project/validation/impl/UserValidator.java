package epam.project.validation.impl;

import epam.project.validation.ValidationResult;
import epam.project.validation.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

public class UserValidator implements Validator {

    private static final String PASSWORD = "password";
    private static final String LOGIN = "login";
    private static final String BALANCE = "balance";
    private static final String EMAIL = "email";
    private static final String FIST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String STATUS = "status";

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
    private static final int MIN_STATUS_LENGTH = 3;
    private static final int MAX_STATUS_LENGTH = 15;

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
                case STATUS:
                    validateStatus(validationResult,value);
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
            errors.add("Login length must be more then 5 symbols");
        }
        if (login.length() > MAX_LOGIN_LENGTH) {
            errors.add("Login length must be less then 16 symbols");
        }
        if (login.contains(SPACE)) {
            errors.add("Login must not have empty space");
        }
        if (errors.size() > 0) {
            validationResult.add("login", errors);
        }
    }

    private void validateEmail(ValidationResult validationResult, String email)  {

        ArrayList<String> errors = new ArrayList<>();

        if (email.length() <= MIN_EMAIL_LENGTH) {
            errors.add("Email length must be more then 5 symbols");
        }
        if (email.length() > MAX_EMAIL_LENGTH) {
            errors.add("Email length must be less then 16 symbols");
        }
        if (email.matches(CHECK_EMAIL)) {
            errors.add("Email are not valid");
        }
        if (email.contains(SPACE)) {
            errors.add("Email must not have empty space");
        }
        if (errors.size() > 0) {
            validationResult.add("email", errors);
        }
    }

    private void validateFirstName(ValidationResult validationResult, String name) {
        ArrayList<String> errors = new ArrayList<>();
        if (name.length() <= MIN_FIRST_NAME_LENGTH) {
            errors.add("First name length must be more then 5 symbols");
        }
        if (name.length() > MAX_FIRST_NAME_LENGTH) {
            errors.add("First name must be less then 16 symbols");
        }
        if (name.matches(CHECK_NAME)) {
            errors.add("First name are not valid only letters");
        }
        if (name.contains(SPACE)) {
            errors.add("First name must not have empty space");
        }
        if (errors.size() > 0) {
            validationResult.add("first_name", errors);
        }
    }

    private void validateLastName(ValidationResult validationResult, String name) {
        ArrayList<String> errors = new ArrayList<>();
        if (name.length() <= MIN_LAST_NAME_LENGTH) {
            errors.add("Last name length must be more then 5 symbols");
        }
        if (name.length() > MAX_LAST_NAME_LENGTH) {
            errors.add("Last name length must be less then 16 symbols");
        }
        if (name.matches(CHECK_NAME)) {
            errors.add("Last name are not valid only letters");
        }
        if (name.contains(SPACE)) {
            errors.add("Last name must not have empty space");
        }
        if (errors.size() > 0) {
            validationResult.add("last_name", errors);
        }
    }

    private void validateBalance(ValidationResult validationResult, String balance) {
        ArrayList<String> errors = new ArrayList<>();
        try {
            Integer.valueOf(balance);
        }
        catch (NumberFormatException e){
        errors.add("Balance are not valid ");
        }
        if (errors.size() > 0) {
            validationResult.add("last_name", errors);
        }
    }

    private void validatePassword(ValidationResult validationResult, String password) {
        ArrayList<String> errors = new ArrayList<>();

        if (password.length() <= MIN_PASSWORD_LENGTH) {
            errors.add("Password length must be more then 5 symbols");
        }
        if (password.length() > MAX_PASSWORD_LENGTH) {
            errors.add("Password length must be less then 16 symbols");
        }
        if (password.contains(SPACE)) {
            errors.add("Password must not have empty space");
        }
        if (errors.size() > 0) {
            validationResult.add("password", errors);
        }
    }

    private void validateStatus(ValidationResult validationResult, String status) {
        ArrayList<String> errors = new ArrayList<>();

        if (status.length() <= MIN_STATUS_LENGTH) {
            errors.add("Status length must be more then 5 symbols");
        }
        if (status.length() > MAX_STATUS_LENGTH) {
            errors.add("Status length must be less then 16 symbols");
        }
        if (errors.size() > 0) {
            validationResult.add("password", errors);
        }
    }
}

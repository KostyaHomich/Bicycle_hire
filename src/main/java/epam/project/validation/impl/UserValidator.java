package epam.project.validation.impl;

import epam.project.entity.User;
import epam.project.validation.ValidationResult;
import epam.project.validation.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserValidator implements Validator {

    private static final String PASSWORD="password";
    private static final String LOGIN="login";
    private static final String EMAIL="email";
    private static final String FIST_NAME="first_name";
    private static final String LAST_NAME="last_name";

    private static final  Logger LOGGER = LogManager.getRootLogger();
    private static final  String CHECK_LOGIN_SPECIAL_SYMBOLS = "[a-zA-Z0-9]";
    private static final  String CHECK_LOGIN_CONTAINS_LETTERS = "[a-zA-Z]{1,}";
    private static final  String CHECK_LOGIN_CONTAINS_NUMBERS = "[0-9]";
    private static final  String CHECK_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final  String CHECK_NAME = "[a-zA-Z]";
    private static final  String SPACE = " ";
    private static final  String EMPTY = "";
    private static final  int MAX_EMAIL_LENGTH = 40;
    private static final  int MIN_EMAIL_LENGTH = 3;
    private static final  int MAX_FIRST_NAME_LENGTH = 15;
    private static final  int MIN_FIRST_NAME_LENGTH = 2;
    private static final  int MAX_LAST_NAME_LENGTH = 15;
    private static final  int MIN_LAST_NAME_LENGTH = 2;
    private static final  int MIN_LOGIN_LENGTH = 5;
    private static final  int MAX_LOGIN_LENGTH = 15;


    public ValidationResult doValidate(Map params) {

        ValidationResult validationResult=new ValidationResult();

        for (Object key: params.keySet())
        {
            String keyStr = (String)key;
            String[] value = (String[])params.get(keyStr);
            switch (keyStr) {
                case LOGIN:validateLogin(validationResult,value[0]);break;
                case PASSWORD:break;
                case FIST_NAME:break;
                case LAST_NAME:break;
                case EMAIL:break;
                default:break;
            }

        }
return validationResult;
//        return email.matches(CHECK_EMAIL)
//                && !email.contains(SPACE)
//                && !email.equals(EMPTY)
//                && !firstName.contains(SPACE)
//                && !lastName.contains(SPACE)
//                && !firstName.equals(EMPTY)
//                && !lastName.equals(EMPTY)
//                && email.length() < MAX_EMAIL_LENGTH
//                && email.length() >= MIN_EMAIL_LENGTH
//                && firstName.length() < MAX_FIRST_NAME_LENGTH
//                && firstName.length() > MIN_FIRST_NAME_LENGTH
//                && lastName.length() < MAX_LAST_NAME_LENGTH
//                && lastName.length() > MIN_LAST_NAME_LENGTH
//                && firstName.matches(CHECK_NAME)
//                && lastName.matches(CHECK_NAME);
}

    private void validateLogin(ValidationResult validationResult,String login) {
        ArrayList<String> errors=new ArrayList<>();
        if(login.length()<=MIN_LOGIN_LENGTH) {
            errors.add("Login length must be more then 5 symbols.");
        }
        if(login.length()>MAX_LOGIN_LENGTH) {
            errors.add("Login length must be less then 16 symbols.");
        }
        if(login.matches(CHECK_LOGIN_SPECIAL_SYMBOLS)) {
            errors.add("Login must not contain special characters.");
        }
        validationResult.add("login",errors);
    }


}

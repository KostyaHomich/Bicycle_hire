package epam.project.validation.impl;

import epam.project.entity.User;
import epam.project.validation.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserValidator implements Validator {

    private static final  Logger LOGGER = LogManager.getRootLogger();
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

    public boolean doValidate(User user) {

        String email = user.getEmail();

        return email.matches(CHECK_EMAIL)
                && !email.contains(SPACE)
                && !email.equals(EMPTY)
                && !user.getFirstName().contains(SPACE)
                && !user.getLastName().contains(SPACE)
                && !user.getFirstName().equals(EMPTY)
                && !user.getLastName().equals(EMPTY)
                && email.length() < MAX_EMAIL_LENGTH
                && email.length() >= MIN_EMAIL_LENGTH
                && user.getFirstName().length() < MAX_FIRST_NAME_LENGTH
                && user.getFirstName().length() > MIN_FIRST_NAME_LENGTH
                && user.getLastName().length() < MAX_LAST_NAME_LENGTH
                && user.getLastName().length() > MIN_LAST_NAME_LENGTH
                && user.getFirstName().matches(CHECK_NAME)
                && user.getLastName().matches(CHECK_NAME);
    }
}

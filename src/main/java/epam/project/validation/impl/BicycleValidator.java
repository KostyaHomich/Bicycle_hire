package epam.project.validation.impl;


import epam.project.entity.Bicycle;
import epam.project.validation.ValidationResult;
import epam.project.validation.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class BicycleValidator implements Validator {

    private static final String DAILY_RENTAL_PRICE = "daily_rental_price";
    private static final String NAME = "name";
    private static final String STATUS = "status";
    private static final String DESCRIPTION = "description";

    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    private static final String CHECK_NAME = "[a-zA-Z]";
    private static final String SPACE = " ";
    private static final int MAX_NAME_LENGTH = 25;
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MIN_STATUS_LENGTH = 4;
    private static final int MAX_STATUS_LENGTH = 25;
    private static final int MIN_DESCRIPTION_LENGTH = 4;

    public ValidationResult doValidate(Map<String, String> params) {

        ValidationResult validationResult = new ValidationResult();

        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case DAILY_RENTAL_PRICE:
                    validateDailyRentalPrice(validationResult, value);
                    break;
                case NAME:
                    validateName(validationResult, value);
                    break;
                case DESCRIPTION:
                    validateDescription(validationResult, value);
                    break;
                case STATUS:
                    validateStatus(validationResult, value);
                    break;
                default:
                    break;

            }

        }
        return validationResult;
    }

    private void validateDailyRentalPrice(ValidationResult validationResult, String value) {
        ArrayList<String> errors = new ArrayList<>();
        BigDecimal dailyRentalPrice;
        try {
            dailyRentalPrice = BigDecimal.valueOf(Integer.valueOf(value));
            if (dailyRentalPrice.intValue() < 0) {
                errors.add("Daily rental price can't be less then 0");
            }
        } catch (NumberFormatException e) {
            errors.add("Daily rental price are not valid");
        }
        if (errors.size() > 0) {
            validationResult.add("login", errors);
        }
    }

    private void validateName(ValidationResult validationResult, String name) {
        ArrayList<String> errors = new ArrayList<>();

        if (name.length() <= MIN_NAME_LENGTH) {
            errors.add("Name must be more then " + MIN_NAME_LENGTH + " symbols");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            errors.add("Name must be less then " + MAX_NAME_LENGTH + " symbols");
        }
        if (name.contains(SPACE)) {
            errors.add("Name must not have empty spaces");
        }
        if (errors.size() > 0) {
            validationResult.add("login", errors);
        }
    }

    private void validateStatus(ValidationResult validationResult, String status) {
        ArrayList<String> errors = new ArrayList<>();

        if (status.length() <= MIN_STATUS_LENGTH) {
            errors.add("Status length must be more then " + MIN_STATUS_LENGTH + " symbols");
        }
        if (status.length() > MAX_STATUS_LENGTH) {
            errors.add("Status length must be less then " + MAX_STATUS_LENGTH + " symbols");
        }
        if (status.matches(CHECK_NAME)) {
            errors.add("Status can contains only letters");
        }
        if (errors.size() > 0) {
            validationResult.add("password", errors);
        }
    }

    private void validateDescription(ValidationResult validationResult, String description) {
        ArrayList<String> errors = new ArrayList<>();

        if (description.length() <= MIN_DESCRIPTION_LENGTH) {
            errors.add("Description length must be more then " + MIN_DESCRIPTION_LENGTH + " symbols");
        }

        if (errors.size() > 0) {
            validationResult.add("password", errors);
        }
    }

}

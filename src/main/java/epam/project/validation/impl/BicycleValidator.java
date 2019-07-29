package epam.project.validation.impl;


import epam.project.validation.ValidationResult;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

public class BicycleValidator {

    private static final String DAILY_RENTAL_PRICE = "daily_rental_price";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";


    private static final Logger LOGGER = LogManager.getLogger(UserValidator.class);

    private static final String CHECK_NAME = "[a-zA-Z]";
    private static final String SPACE = " ";
    private static final int MAX_NAME_LENGTH = 25;
    private static final int MIN_NAME_LENGTH = 4;
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
                errors.add("bicycle.error.daily_rental_price_more_then_0");
            }
        } catch (NumberFormatException e) {
            errors.add("bicycle.error.invalid_daily_rental_price");
        }
        if (errors.size() > 0) {
            validationResult.add(DAILY_RENTAL_PRICE, errors);
        }
    }

    private void validateName(ValidationResult validationResult, String name) {
        ArrayList<String> errors = new ArrayList<>();

        if (name.length() <= MIN_NAME_LENGTH) {
            errors.add("bicycle.error.min_name_length");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            errors.add("bicycle.error.max_name_length");
        }
        if (name.contains(SPACE)) {
            errors.add("bicycle.error.name_empty_space");
        }
        if (name.matches(CHECK_NAME)) {
            errors.add("bicycle.error.invalid_name");
        }
        if (errors.size() > 0) {
            validationResult.add(NAME, errors);
        }
    }

    private void validateDescription(ValidationResult validationResult, String description) {
        ArrayList<String> errors = new ArrayList<>();

        if (description.length() <= MIN_DESCRIPTION_LENGTH) {
            errors.add("bicycle.error.min_description_length");
        }

        if (errors.size() > 0) {
            validationResult.add(DESCRIPTION, errors);
        }
    }


}

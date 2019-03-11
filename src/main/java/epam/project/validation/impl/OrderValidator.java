package epam.project.validation.impl;


import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.service.impl.PointHireService;
import epam.project.validation.ValidationResult;
import epam.project.validation.Validator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

public class OrderValidator implements Validator {

    private static final String TIME_RENTAL = "time_rental";
    private static final String STATUS = "status";
    private static final String POINT_HIRE_ID = "point_hire_id";
    private static final String BICYCLE_ID = "bicycle_id";

    private static final Logger LOGGER = LogManager.getLogger(OrderValidator.class);

    private static final int MAX_STATUS_LENGTH = 35;
    private static final int MIN_STATUS_LENGTH = 4;

    public ValidationResult doValidate(Map<String,String> params) throws ServiceException {

        ValidationResult validationResult = new ValidationResult();

        for (Object key : params.keySet()) {
            String keyStr = (String) key;
            String value = params.get(keyStr);
            switch (keyStr) {
                case POINT_HIRE_ID:
                    validatePointHireId(validationResult, value);
                    break;
                case BICYCLE_ID:
                    validateBicycleId(validationResult, value);
                    break;
                case TIME_RENTAL:
                    validateTimeRental(validationResult, value);
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


    private ValidationResult validateBicycleId(ValidationResult validationResult, String value) throws ServiceException {

        BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
        ArrayList<String> errors = new ArrayList<>();
        if (!bicycleService.contains(Integer.valueOf(value))) {
            errors.add("Bicycle with this id doesn't exist");
            validationResult.add("bicycle",errors);
        }
        return validationResult;
    }

    private ValidationResult validatePointHireId(ValidationResult validationResult, String value) throws ServiceException {

        PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
        ArrayList<String> errors = new ArrayList<>();
        if (!pointHireService.contains(Integer.valueOf(value))) {
            errors.add("Point hire with this id doesn't exist");
            validationResult.add("bicycle",errors);
        }
        return validationResult;
    }


    private void validateTimeRental(ValidationResult validationResult, String value) {
        ArrayList<String> errors = new ArrayList<>();
        int dailyRentalPrice;
        try {
            dailyRentalPrice = Integer.valueOf(value);
            if (dailyRentalPrice <= 0) {
                errors.add("Time rental can't be less then 0");
            }
        } catch (NumberFormatException e) {
            errors.add("Time rental are not valid");
        }
        if (errors.size() > 0) {
            validationResult.add("login", errors);
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

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class OrderValidator implements Validator {

    private static final String TIME_RENTAL = "time_rental";
    private static final String TIME_ORDER = "time_order";
    private static final String POINT_HIRE_ID = "point_hire_id";
    private static final String BICYCLE_ID = "bicycle_id";

    private static final Logger LOGGER = LogManager.getLogger(OrderValidator.class);


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
                case TIME_ORDER:
                    validateTimeOrder(validationResult, value);
                    break;
                default:
                    break;
            }

        }
        return validationResult;
    }


    private void validateBicycleId(ValidationResult validationResult, String value) throws ServiceException {

        BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
        ArrayList<String> errors = new ArrayList<>();
        if (!bicycleService.contains(Integer.valueOf(value))) {
            errors.add("Bicycle with this id doesn't exist");
            validationResult.add(BICYCLE_ID,errors);
        }

    }

    private void validatePointHireId(ValidationResult validationResult, String value) throws ServiceException {

        PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
        ArrayList<String> errors = new ArrayList<>();
        if (!pointHireService.contains(Integer.valueOf(value))) {
            errors.add("Point hire with this id doesn't exist");
            validationResult.add(POINT_HIRE_ID,errors);
        }
    }


    private void validateTimeRental(ValidationResult validationResult, String value) {
        ArrayList<String> errors = new ArrayList<>();
        try {
            int dailyRentalPrice = Integer.valueOf(value);
            if (dailyRentalPrice <= 0) {
                errors.add("order.error.time_rental_more_then_0");
            }
        } catch (NumberFormatException e) {
            errors.add("order.error.invalid_time_rental");
        }
        if (errors.size() > 0) {
            validationResult.add(TIME_RENTAL, errors);
        }
    }

    private void validateTimeOrder(ValidationResult validationResult, String value) {
        ArrayList<String> errors = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {

            Date date = format.parse(value);
            if(date.getTime()<new Date().getTime()) {
                errors.add("order.error.time_order_less_than_now");
            }
        } catch (ParseException e) {
         errors.add("order.error.invalid_time_order");
        }
        if (errors.size() > 0) {
            validationResult.add(TIME_RENTAL, errors);
        }
    }

}

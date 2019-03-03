package epam.project.validation.impl;


import epam.project.entity.Order;
import epam.project.validation.Validator;

public class OrderValidator implements Validator {

    private static final String REGEX_DATE = "[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])";

    public boolean doValidate(Order order) {
        if(order.getCost().intValue()>0
                && order.getRentalTime()>0
                && order.getTimeOrder().matches(REGEX_DATE)){
            return true;
        }
        else{
            return false;
        }
    }
}

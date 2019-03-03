package epam.project.validation.impl;


import epam.project.entity.Bicycle;
import epam.project.validation.Validator;

public class BicycleValidator implements Validator {
    private final static String SPACE = " ";
    private final static String EMPTY = "";

    public boolean doValidate(Bicycle bicycle) {
        if(bicycle.getDaily_rental_price().intValue()>0
                && !bicycle.getName().equals(SPACE)
                && !bicycle.getName().equals(EMPTY)){
            return true;
        }
        else{
            return false;
        }
    }
}

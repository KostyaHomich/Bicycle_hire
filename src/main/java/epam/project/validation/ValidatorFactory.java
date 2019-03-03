package epam.project.validation;


import epam.project.validation.impl.BicycleValidator;
import epam.project.validation.impl.OrderValidator;
import epam.project.validation.impl.PointHireValidator;
import epam.project.validation.impl.UserValidator;

public class ValidatorFactory {

    private ValidatorFactory() {
    }

    private static ValidatorFactory instance;

    public static ValidatorFactory getInstance() {

        if (instance == null) {
            instance = new ValidatorFactory();
        }
        return instance;
    }

    public Validator getValidator(ValidatorType type) {
        switch (type) {
            case BICYCLE:return new BicycleValidator();
            case ORDER:return new OrderValidator();
            case USER:return new UserValidator();
            case POINTHIRE:return new PointHireValidator();
        }
        return null;
    }

}

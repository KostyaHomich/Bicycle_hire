package epam.project.validation;


import epam.project.validation.impl.*;

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
            case POINT_HIRE:return new PointHireValidator();
            case LOGIN:return new ContainsValidator();

        }
        return null;
    }

}

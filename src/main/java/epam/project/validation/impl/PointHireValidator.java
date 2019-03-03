package epam.project.validation.impl;

import epam.project.entity.PointHire;
import epam.project.validation.Validator;


public class PointHireValidator implements Validator {

    private static final String CHECK_TELEPHONE="/^(\\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\\s*)?$/";

    public static boolean doValidate(PointHire pointHire) {
        if(pointHire.getTelephone().matches(CHECK_TELEPHONE)) {
            return true;
        }
        return false;
    }
}

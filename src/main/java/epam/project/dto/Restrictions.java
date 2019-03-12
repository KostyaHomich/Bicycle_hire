package epam.project.dto;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class Restrictions {
    private List<String> allowedRoles;
    private List<String> allowedMethods;

    public boolean isAllowedRole(String role) {
        return (allowedRoles == null) || allowedRoles.contains(role);
    }

    public boolean isAllowedMethod(String method) {
        return (allowedMethods == null) || allowedMethods.contains(method);
    }

    public Restrictions setRoles(String... allowedRoles) {
        this.allowedRoles = Arrays.asList(allowedRoles);
        return this;
    }

    public Restrictions setMethods(String... allowedMethods) {
        this.allowedMethods = Arrays.asList(allowedMethods);
        return this;
    }
}

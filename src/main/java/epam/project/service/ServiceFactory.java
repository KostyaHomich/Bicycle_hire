package epam.project.service;

import epam.project.service.impl.BicycleService;
import epam.project.service.impl.PointHireService;
import epam.project.service.impl.UserService;


public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Service getService(ServiceType type)  {
        switch (type) {
            case USER:return  new UserService();
            case BICYCLE: return new BicycleService();
            case POINT_HIRE: return new PointHireService();
        }
       return null;
    }
    public HashGenerator getHashGenerator() {
        return new HashGenerator();
    }

}

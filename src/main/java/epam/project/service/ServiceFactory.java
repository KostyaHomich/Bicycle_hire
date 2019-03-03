package epam.project.service;

import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.service.impl.OrderService;
import epam.project.service.impl.PointHireService;
import epam.project.service.impl.UserService;
import epam.project.service.recovery.RecoveryPasswordService;

public class ServiceFactory {

    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Service getService(ServiceType type) throws ServiceException {
        switch (type) {
            case USER:return  new UserService();
            case ORDER: return new OrderService();
            case BICYCLE: return new BicycleService();
            case POINT_HIRE: return new PointHireService();
        }
       return null;
    }
    public HashGenerator getHashGenerator() {
        return new HashGenerator();
    }
    public RecoveryPasswordService getRecoveryPasswordService() {
        return new RecoveryPasswordService();
    }
}

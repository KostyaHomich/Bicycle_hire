package epam.project.controller.command;

import epam.project.controller.command.CommandType;
import epam.project.dto.ResponseContent;
import epam.project.controller.command.Command;
import epam.project.controller.command.Router;
import epam.project.entity.Bicycle;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteBicycle implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);

            Integer id = Integer.valueOf(request.getParameter("id"));
            Bicycle bicycle = bicycleService.takeBicycle(id);
            bicycleService.delete(bicycle);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command="+CommandType.DELETE_BICYCLE, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException("Can't delete bicycle.", e);
        }
    }
}

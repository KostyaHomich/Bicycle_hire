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
import java.math.BigDecimal;

public class CommandAddBicycle implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
            Bicycle bicycle = new Bicycle();
            bicycle.setName(request.getParameter("name"));
            bicycle.setDaily_rental_price(new BigDecimal(Integer.valueOf(request.getParameter("daily_rental_price"))));
            bicycle.setDescription(request.getParameter("description"));

            bicycleService.add(bicycle);

            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(CommandType.ADD_BICYCLE, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException("Can't add bicycle.", e);
        }
    }
}

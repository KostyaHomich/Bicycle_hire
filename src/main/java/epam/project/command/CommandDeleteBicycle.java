package epam.project.command;

import epam.project.dto.ResponseContent;
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
            Integer id = Integer.valueOf(request.getParameter("bicycleId"));

            Bicycle bicycle = bicycleService.getById(id);
            bicycleService.delete(bicycle);

            Command command = new CommandShowBicyclePageAndTakeAllBicycles();
            return  command.execute(request);
        } catch (ServiceException e) {
            request.setAttribute("error","Can't delete bicycle.");
            Command command = new CommandShowBicyclePageAndTakeAllBicycles();
            return  command.execute(request);

        }
    }
}

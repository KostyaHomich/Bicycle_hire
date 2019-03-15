package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.EntityType;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteBicycle implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("entity", EntityType.BICYCLE);
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
            Integer id = Integer.valueOf(request.getParameter("bicycleId"));

            Bicycle bicycle = bicycleService.getById(id);
            bicycleService.delete(bicycle);

            return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_LIST, request);
        } catch (ServiceException e) {
            request.setAttribute("error","Can't delete bicycle.");
            return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_LIST, request);
        }
    }
}

package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.EntityType;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandDeleteBicycle implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandDeleteBicycle.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
            if (request.getParameter("bicycleId") != null) {

                Integer id = Integer.valueOf(request.getParameter("bicycleId"));

                Bicycle bicycle = bicycleService.getById(id);
                bicycleService.delete(bicycle);
            } else {
                request.setAttribute("error", "bicycle.error.delete_bicycle");
            }
            return sendTolLastPage(request);
        } catch (ServiceException e) {
            LOGGER.error("Failed to delete bicycle", e);
            request.setAttribute("error", "bicycle.error.delete_bicycle");
            throw new CommandException("Failed to delete bicycle");
        }
    }

    private ResponseContent sendTolLastPage(HttpServletRequest request) throws CommandException {
        String lastPage = request.getParameter("lastPage");
        switch (lastPage) {
            case "point_hire_list":
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_LIST, request);
            case "bicycle_list":
                return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_LIST, request);
            default:
                throw new CommandException("Last page are not present");
        }
    }
}

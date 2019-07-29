package epam.project.command.bicycle;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.CommandType;
import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.EntityType;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandAddBestBicycle implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandAddBicycle.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute("entity", EntityType.BICYCLE);
            BicycleService bicycleService = new BicycleService();

            User user = (User) request.getSession().getAttribute("signInUser");
            int id = Integer.valueOf(request.getParameter("bicycleId"));
            Bicycle bicycle = bicycleService.getById(id);

            bicycleService.addBestBicycle(bicycle, user);
            return ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_BICYCLE_LIST, request);

        } catch (ServiceException e) {
            LOGGER.error("Failed to add bicycle.", e);
            request.setAttribute("error", "bicycle.error.add_bicycle");
            throw new CommandException("Failed to add bicycle.");
        }

    }

}

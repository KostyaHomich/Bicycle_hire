package epam.project.command.bicycle;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.PageConst;
import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowBestBicycles  implements Command {
    private static Logger LOGGER = Logger.getLogger(CommandShowBicycleList.class.getName());

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {

        try {
            request.setAttribute("viewName", "best_bicycle_list");
            BicycleService bicycleService = new BicycleService();

            User user = (User) request.getSession().getAttribute("signInUser");
            List<Bicycle> bicycleList = bicycleService.showBestBicycles(user);
            request.setAttribute("bicycles", bicycleList);
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        } catch (ServiceException e) {
            LOGGER.error("Failed to show best bicycle list", e);
            request.setAttribute("error", "page.error.show_bicycle_list");
            throw new CommandException("Failed to show best bicycle list");
        }


    }
}
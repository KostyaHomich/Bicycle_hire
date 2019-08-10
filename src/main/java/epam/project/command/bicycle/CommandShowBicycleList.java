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

public class CommandShowBicycleList implements Command {
    private static Logger LOGGER = Logger.getLogger(CommandShowBicycleList.class.getName());

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {

        try {
            int page = 1;
            int recordsPerPage = 5;
            if(request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            int currentAmountBicycles=(page-1)*recordsPerPage;

            request.setAttribute("viewName", "bicycle_list");

            BicycleService bicycleService = new BicycleService();


            User user = (User) request.getSession().getAttribute("signInUser");
            List<Bicycle> bicycleList = bicycleService.getBicycles(currentAmountBicycles,recordsPerPage, user);

            int amountBicycles=bicycleService.getAmountBicycles();
            int amountPages=amountBicycles / recordsPerPage;


            request.setAttribute("bicycles", bicycleList);
            request.setAttribute("amountPages", amountPages);
            request.setAttribute("page", page);

            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        } catch (ServiceException e) {
            LOGGER.error("Failed to show bicycle list", e);
            request.setAttribute("error", "page.error.show_bicycle_list");
            throw new CommandException("Failed to show bicycle list");
        }


    }
}

package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.User;
import epam.project.entity.UserRole;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowBicycleList implements Command {
    private static Logger LOGGER = Logger.getLogger(CommandShowBicycleList.class.getName());

    private static final String AMOUNT_BICYCLES="amountBicycles";

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {

        try {
            
            request.setAttribute("viewName", "bicycle_list");

            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);

            User user = (User) request.getSession().getAttribute("signInUser");
            List<Bicycle> bicycleList;

            String amountBicycles = request.getParameter(AMOUNT_BICYCLES);
            request.setAttribute(AMOUNT_BICYCLES, 5);

            if (user.getRole().equalsIgnoreCase(UserRole.ADMIN.name())) {
                if (amountBicycles != null && !amountBicycles.isEmpty()) {
                    int count = Integer.valueOf(amountBicycles);
                    bicycleList = bicycleService.getBicycles(count);
                    request.setAttribute(AMOUNT_BICYCLES, count);
                }
                else {
                    bicycleList = bicycleService.getBicycles(5);
                }
            } else {
                if (amountBicycles != null && !amountBicycles.isEmpty()) {
                    int count = Integer.valueOf(amountBicycles);
                    bicycleList = bicycleService.getAvailableBicycles(count);
                    request.setAttribute(AMOUNT_BICYCLES, count);
                }
                else {
                    bicycleList = bicycleService.getAvailableBicycles(5);
                }
            }
            request.setAttribute("bicycles", bicycleList);
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        } catch (ServiceException e) {
            LOGGER.error("Failed to show bicycle list", e);
            request.setAttribute("error", "page.error.show_bicycle_list");
            throw new CommandException("Failed to show bicycle list");
        }


    }
}

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
    @Override
    public ResponseContent execute(HttpServletRequest request) {

            try {
                request.setAttribute("viewName", "bicycle_list");

                BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);

                User user = (User) request.getSession().getAttribute("signInUser");
                List<Bicycle> bicycleList;

                if (user.getRole().equalsIgnoreCase(UserRole.USER.name())) {
                    bicycleList = bicycleService.takeAllAvailableBicycle();

                } else {
                    bicycleList = bicycleService.takeAll();
                }

                request.setAttribute("bicycles",bicycleList);
                return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
            } catch (ServiceException e) {
                request.setAttribute("error", "Error: failed get all bicycles.");
                return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
            }


    }
}

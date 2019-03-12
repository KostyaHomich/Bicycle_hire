package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.EntityType;
import epam.project.entity.User;
import epam.project.entity.UserRole;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowBicycleList implements Command {
    private static Logger LOGGER = Logger.getLogger(CommandShowBicycleList.class.getName());
    @Override
    public ResponseContent execute(HttpServletRequest request) {

        ResponseContent responseContent = new ResponseContent();
            try {
                request.setAttribute("entity", EntityType.BICYCLE);

                BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);

                User user = (User) request.getSession().getAttribute("signInUser");
                List<Bicycle> bicycleList;

                if (user.getRole().equalsIgnoreCase(UserRole.USER.name())) {
                    bicycleList = bicycleService.takeAllAvailableBicycle();

                } else {
                    bicycleList = bicycleService.takeAll();
                }

                request.setAttribute("bicycles",bicycleList);

                responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
                return responseContent;

            } catch (ServiceException e) {

                request.setAttribute("error", "Error: failed get all orders.");
                responseContent = new ResponseContent();
                responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
                return responseContent;
            }


    }
}

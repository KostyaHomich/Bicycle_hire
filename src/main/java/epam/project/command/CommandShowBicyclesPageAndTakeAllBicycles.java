package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowBicyclesPageAndTakeAllBicycles implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request) {
            ResponseContent responseContent = new ResponseContent();
            try {
                BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
                List<Bicycle> bicycleList = bicycleService.takeAll();
                request.setAttribute("bicycles",bicycleList);
                responseContent.setRouter(new Router("/WEB-INF/jsp/bicycle.jsp", Router.Type.FORWARD));
            } catch (ServiceException e) {

            }
            return responseContent;

    }
}

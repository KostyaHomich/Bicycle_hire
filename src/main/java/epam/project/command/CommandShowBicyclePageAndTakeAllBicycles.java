package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowBicyclePageAndTakeAllBicycles implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request) {
            ResponseContent responseContent = new ResponseContent();
            try {
                BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
                List<Bicycle> bicycleList = bicycleService.takeAll();
                request.setAttribute("entity","bicycle");
                request.setAttribute("bicycles",bicycleList);
                responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
            } catch (ServiceException e) {
                request.setAttribute("error", "Error: failed get all bicycles.");
                responseContent = new ResponseContent();
                responseContent.setRouter(new Router(PageConst.ADMIN_PAGE_PATH, Router.Type.FORWARD));
                return responseContent;
            }
            return responseContent;

    }
}

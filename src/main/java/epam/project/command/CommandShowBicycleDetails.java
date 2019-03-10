package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;

import javax.servlet.http.HttpServletRequest;

public class CommandShowBicycleDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request)  {
        try {
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
            int id=Integer.valueOf(request.getParameter("bicycleId"));
            if(id==0) {
                Bicycle bicycle=new Bicycle();
                return setAttribute(request, bicycle);
            }
            else {
                Bicycle bicycle = bicycleService.getById(id);
                return setAttribute(request, bicycle);
            }
       }
        catch (ServiceException e) {
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.BICYCLE_DETAILS_PAGE_PATH,Router.Type.FORWARD));
            return responseContent;
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, Bicycle bicycle) {
        request.setAttribute("bicycle", bicycle);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageConst.BICYCLE_DETAILS_PAGE_PATH, Router.Type.FORWARD));
        return responseContent;
    }
}

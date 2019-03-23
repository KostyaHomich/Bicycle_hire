package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.entity.EntityType;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;

public class CommandShowBicycleDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request)  {
        try {
            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);

            int id=Integer.valueOf(request.getParameter("bicycleId"));

            request.setAttribute("viewName","bicycle_details");
            if(id==0) {
                int point_hire_id=Integer.valueOf(request.getParameter("pointHireId"));
                Bicycle bicycle=new Bicycle();
                bicycle.setPoint_hire_id(point_hire_id);
                return setAttribute(request, bicycle);
            }
            else {
                Bicycle bicycle = bicycleService.getById(id);
                return setAttribute(request, bicycle);
            }
       }
        catch (ServiceException e) {
            return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, Bicycle bicycle) {
        request.setAttribute("bicycle", bicycle);

        String lastPage=request.getParameter("lastPage");
        switch (lastPage) {
            case "bicycle_list":request.setAttribute("lastCommand",CommandType.SHOW_BICYCLE_LIST);break;
            case "point_hire_list":request.setAttribute("lastCommand",CommandType.SHOW_POINT_HIRE_LIST);break;
            default:break;
        }
        return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
    }
}

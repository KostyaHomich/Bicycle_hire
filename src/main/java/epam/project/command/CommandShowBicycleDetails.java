package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.Bicycle;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.BicycleService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandShowBicycleDetails implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandShowBicycleDetails.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute("viewName", "bicycle_details");

            BicycleService bicycleService = (BicycleService) ServiceFactory.getInstance().getService(ServiceType.BICYCLE);
            if(request.getParameter("bicycleId")!=null) {
                int id = Integer.valueOf(request.getParameter("bicycleId"));
                if (id == 0) {
                    int point_hire_id = Integer.valueOf(request.getParameter("pointHireId"));
                    Bicycle bicycle = new Bicycle();
                    bicycle.setPoint_hire_id(point_hire_id);
                    return setAttribute(request, bicycle);
                } else {
                    Bicycle bicycle = bicycleService.getById(id);
                    return setAttribute(request, bicycle);
                }
            }
            else {
                setLastPageAttribute(request);
                request.setAttribute("error","page.error.show_bicycle_details");
                return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
            }

        }
        catch (ServiceException e) {
            LOGGER.error("Failed to show bicycle details", e);
            request.setAttribute("error","page.error.show_bicycle_details");
            throw new CommandException("Failed to show bicycle details");

        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, Bicycle bicycle) {
        request.setAttribute("bicycle", bicycle);

        setLastPageAttribute(request);

        return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
    }

    private void setLastPageAttribute(HttpServletRequest request) {
        String lastPage=request.getParameter("lastPage");

        switch (lastPage) {
            case "bicycle_list":request.setAttribute("lastCommand", CommandType.SHOW_BICYCLE_LIST);break;
            case "point_hire_list":request.setAttribute("lastCommand",CommandType.SHOW_POINT_HIRE_LIST);break;
            default:break;
        }
    }
}

package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;

import javax.servlet.http.HttpServletRequest;

public class CommandShowPointHireDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            int id=Integer.valueOf(request.getParameter("pointHireId"));
            if(id==0) {
                PointHire pointHire=new PointHire();
                return setAttribute(request, pointHire);
            }
            else {
                PointHire pointHire = pointHireService.getById(id);
                return setAttribute(request, pointHire);
            }
        }
        catch (ServiceException e) {
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.POINT_HIRE_DETAILS_PAGE_PATH,Router.Type.FORWARD));
            return responseContent;
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, PointHire pointHire) {
        request.setAttribute("pointHire", pointHire);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageConst.POINT_HIRE_LIST_PAGE_PATH, Router.Type.FORWARD));
        return responseContent;
    }
}

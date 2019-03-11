package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowPointHirePageAndTakeAllPointHires implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        try {
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            List<PointHire> pointHireList = pointHireService.takeAll();
            request.setAttribute("entity","point_hire");
            request.setAttribute("pointHireList",pointHireList);
            responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        } catch (ServiceException e) {
            request.setAttribute("error", "Error: failed get all point hires.");
            responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }

    }
}

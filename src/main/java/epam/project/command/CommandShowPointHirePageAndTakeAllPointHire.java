package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowPointHirePageAndTakeAllPointHire implements Command{
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        try {
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);

            List<PointHire> pointHireList = pointHireService.takeAll();
            request.setAttribute("pointHires",pointHireList);
            responseContent.setRouter(new Router("/WEB-INF/jsp/point_hire.jsp", Router.Type.FORWARD));
        } catch (ServiceException e) {

        }
        return responseContent;
    }
}

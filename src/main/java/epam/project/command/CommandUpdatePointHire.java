package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;

import javax.servlet.http.HttpServletRequest;

public class CommandUpdatePointHire implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);

            PointHire pointHire = new PointHire();
            pointHire.setLocation(request.getParameter("location"));
            pointHire.setTelephone(request.getParameter("telephone"));
            pointHire.setDescription(request.getParameter("description"));
            pointHire.setId(Integer.valueOf(request.getParameter("id")));

            pointHireService.update(pointHire);

            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command="+CommandType.UPDATE_POINT_HIRE, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException("Can't update point hire", e);
        }
    }
}

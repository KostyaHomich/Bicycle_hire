package epam.project.command;

import epam.project.command.CommandType;
import epam.project.dto.ResponseContent;
import epam.project.command.Command;
import epam.project.command.Router;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;

import javax.servlet.http.HttpServletRequest;

public class CommandDeletePointHire implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);

            Integer id = Integer.valueOf(request.getParameter("id"));
            PointHire pointHire = pointHireService.getById(id);
            pointHireService.delete(pointHire);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command="+CommandType.DELETE_POINT_HIRE, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException("Can't delete point hire.", e);
        }
    }
}

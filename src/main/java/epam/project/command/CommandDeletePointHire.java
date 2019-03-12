package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
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

            request.setAttribute("entity", EntityType.POINT_HIRE);
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            Integer id = Integer.valueOf(request.getParameter("point_hireId"));

            PointHire pointHire = pointHireService.getById(id);
            pointHireService.delete(pointHire);

            Command command = new CommandShowPointHireList();
            return  command.execute(request);
        } catch (ServiceException e) {
            request.setAttribute("error","Can't delete point hire.");
            Command command = new CommandShowPointHireList();
            return  command.execute(request);

        }
    }
}

package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;

public class CommandDeletePointHire implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {

            request.setAttribute("entity", EntityType.POINT_HIRE);
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            if (request.getParameter("pointHireId") != null) {
                Integer id = Integer.valueOf(request.getParameter("pointHireId"));

                PointHire pointHire = pointHireService.getById(id);
                pointHireService.delete(pointHire);
                return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_LIST,request);

            }
            else {
                request.setAttribute("error","point_hire.error.delete_point_hire");
                return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_LIST,request);
            }
        } catch (ServiceException e) {
            request.setAttribute("error","point_hire.error.delete_point_hire");
            return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_POINT_HIRE_LIST,request);

        }
    }
}

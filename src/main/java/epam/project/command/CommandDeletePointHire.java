package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandDeletePointHire implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandDeletePointHire.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
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
            LOGGER.error("Failed to delete point hire", e);
            request.setAttribute("error","point_hire.error.delete_point_hire");
            throw new CommandException("Failed to delete point hire");
        }
    }
}

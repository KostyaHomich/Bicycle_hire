package epam.project.command.point.hire;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.PageConst;
import epam.project.dto.ResponseContent;
import epam.project.entity.PointHire;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandShowPointHireDetails implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandShowPointHireDetails.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute("viewName","point_hire_details");

            PointHireService pointHireService = new PointHireService();

            if (request.getParameter("pointHireId") != null) {
                int id=Integer.valueOf(request.getParameter("pointHireId"));

                if(id==0) {
                    PointHire pointHire=new PointHire();
                    return setAttribute(request, pointHire);
                } else {
                    PointHire pointHire = pointHireService.getById(id);
                    return setAttribute(request, pointHire);
                }
            }else {
                request.setAttribute("error", "page.error.show_point_hire_details");
                return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
            }
        }
        catch (ServiceException e) {
            LOGGER.error("Failed to show point hire details", e);
            request.setAttribute("error", "page.error.show_point_hire_details");
            throw new CommandException("Failed to show point hire details");
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, PointHire pointHire) {

        request.setAttribute("pointHire", pointHire);
        return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
    }
}

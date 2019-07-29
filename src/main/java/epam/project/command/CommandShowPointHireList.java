package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.PointHire;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowPointHireList implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandShowPointHireList.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute("viewName","point_hire_list");
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            List<PointHire> pointHireList;

            pointHireList = pointHireService.takeAll();

            request.setAttribute("pointHireList",pointHireList);
            return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);

        } catch (ServiceException e) {
            LOGGER.error("Failed to show point hire list", e);
            request.setAttribute("error", "page.error.show_point_hire_list");
            throw new CommandException("Failed to show point hire list");
        }

    }
}

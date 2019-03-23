package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.PointHire;
import epam.project.entity.User;
import epam.project.entity.UserRole;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowPointHireList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("viewName","point_hire_list");
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            User user = (User) request.getSession().getAttribute("signInUser");
            List<PointHire> pointHireList;

            if (user.getRole().equalsIgnoreCase(UserRole.ADMIN.name())) {
                pointHireList = pointHireService.takeAll();

            } else {
                pointHireList = pointHireService.takeAllPointHireWithAvailableBicycle();
            }

            request.setAttribute("pointHireList",pointHireList);
            return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);

        } catch (ServiceException e) {
            request.setAttribute("error", "page.error.show_point_hire_list");
            return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        }

    }
}

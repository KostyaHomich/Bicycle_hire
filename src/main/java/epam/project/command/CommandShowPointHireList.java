package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.PointHire;
import epam.project.entity.User;
import epam.project.entity.UserRole;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.PointHireService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowPointHireList implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        try {
            request.setAttribute("entity", EntityType.POINT_HIRE);
            PointHireService pointHireService = (PointHireService) ServiceFactory.getInstance().getService(ServiceType.POINT_HIRE);
            User user = (User) request.getSession().getAttribute("signInUser");
            List<PointHire> pointHireList;

            if (user.getRole().equalsIgnoreCase(UserRole.USER.name())) {
                pointHireList = pointHireService.takeAllPointHireWithAvailablaBicycle();

            } else if (user.getRole().equalsIgnoreCase(UserRole.ADMIN.name())) {
                pointHireList = pointHireService.takeAll();
            } else {
                responseContent.setRouter(new Router(PageConst.LOGIN_PAGE_PATH, Router.Type.FORWARD));
                return responseContent;
            }

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

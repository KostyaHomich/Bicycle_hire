package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;

public class CommandShowUserDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("viewName","user_details");

            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            int id = Integer.valueOf(request.getParameter("userId"));
            if (request.getParameter("userId") != null) {

                if (id == 0) {
                    User user = new User();
                    return setAttribute(request, user);
                } else {
                    User user = userService.getById(id);
                    return setAttribute(request, user);
                }
            }
            else {
                request.setAttribute("error", "page.error.show_user_details");
                return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", "page.error.show_user_details");
            return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, User user) {
        request.setAttribute("user", user);
        return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
    }
}

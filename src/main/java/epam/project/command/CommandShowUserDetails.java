package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;

public class CommandShowUserDetails implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("entity", EntityType.USER);

            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            int id = Integer.valueOf(request.getParameter("userId"));

            if (id == 0) {
                User user = new User();
                return setAttribute(request, user);
            } else {
                User user = userService.getById(id);
                return setAttribute(request, user);
            }
        } catch (ServiceException e) {
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(PageConst.ENTITY_DETAILS_PAGE_PATH,Router.Type.FORWARD));
            return responseContent;
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, User user) {
        request.setAttribute("user", user);
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageConst.ENTITY_DETAILS_PAGE_PATH, Router.Type.FORWARD));
        return responseContent;
    }
}

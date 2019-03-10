package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowUserPageAndTakeAllUsers implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        try {
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            List<User> userList = userService.takeAll();
            request.setAttribute("entity","user");
            request.setAttribute("users",userList);
            responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        } catch (ServiceException e) {
            request.setAttribute("error","Error: failed get all users");
            responseContent.setRouter(new Router(PageConst.ENTITY_LIST_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }
    }
}

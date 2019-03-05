package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowAllUsers implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        try {
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            List<User> userList = userService.takeAll();
            request.setAttribute("users",userList);
            responseContent.setRouter(new Router("/static/jsp/show_users.jsp", Router.Type.FORWARD));
        } catch (ServiceException e) {

        }
        return responseContent;
    }
}

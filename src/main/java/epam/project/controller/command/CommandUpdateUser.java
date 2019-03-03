package epam.project.controller.command;

import epam.project.controller.command.CommandType;
import epam.project.dto.ResponseContent;
import epam.project.controller.command.Command;
import epam.project.controller.command.Router;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;

public class CommandUpdateUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);

            String login = String.valueOf(request.getParameter("login"));
            User user = userService.takeUser(login);
            userService.update(user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("?command="+CommandType.UPDATE_USER, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException("Can't update user.", e);
        }
    }
}

package epam.project.command;

import epam.project.command.CommandType;
import epam.project.dto.ResponseContent;
import epam.project.command.Command;
import epam.project.command.Router;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;
public class CommandDeleteUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);

            int id = Integer.valueOf(request.getParameter("id"));
            User user = userService.getById(id);

            userService.delete(user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router(CommandType.SHOW_MAIN_PAGE.name(), Router.Type.REDIRECT));

            return responseContent;
        } catch (ServiceException e) {
            throw new RuntimeException("Can't delete user.", e);
        }
    }


}

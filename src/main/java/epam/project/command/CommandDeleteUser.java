package epam.project.command;

import epam.project.dto.ResponseContent;
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
            Integer id = Integer.valueOf(request.getParameter("userId"));

            User user = userService.getById(id);
            userService.delete(user);

            Command command = new CommandShowUserPageAndTakeAllUsers();
            return  command.execute(request);
        } catch (ServiceException e) {
            request.setAttribute("error","Can't delete user.");
            Command command = new CommandShowUserPageAndTakeAllUsers();
            return  command.execute(request);

        }
    }


}

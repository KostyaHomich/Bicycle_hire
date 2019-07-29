package epam.project.command.user;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.CommandType;
import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
public class CommandDeleteUser implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandDeleteUser.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute("entity", EntityType.USER);
            UserService userService = new UserService();
            if(request.getParameter("userId")!=null){
                Integer id = Integer.valueOf(request.getParameter("userId"));

                User user = userService.getById(id);
                userService.delete(user);

                return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_USER_LIST,request);
            }
            else {
                request.setAttribute("error","user.error.delete_user");
                return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_USER_LIST,request);
            }
        } catch (ServiceException e) {
            LOGGER.error("Failed to delete user", e);
            request.setAttribute("error","user.error.delete_user");
            throw new CommandException("Failed to delete user");

        }
    }


}

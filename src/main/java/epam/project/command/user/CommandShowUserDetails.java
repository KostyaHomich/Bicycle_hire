package epam.project.command.user;

import epam.project.command.Command;
import epam.project.command.CommandException;
import epam.project.command.PageConst;
import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandShowUserDetails implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandShowUserDetails.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {
            request.setAttribute("viewName","user_details");

            UserService userService = new UserService();
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
            LOGGER.error("Failed to show user details", e);
            request.setAttribute("error", "page.error.show_user_details");
            throw new CommandException("Failed to show user details");
        }
    }

    private ResponseContent setAttribute(HttpServletRequest request, User user) {
        request.setAttribute("user", user);
        return  ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_DETAILS_PAGE_PATH);
    }
}

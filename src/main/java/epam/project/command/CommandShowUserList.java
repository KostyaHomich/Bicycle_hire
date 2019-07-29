package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.util.ResponseContentBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommandShowUserList implements Command {

    private static final Logger LOGGER = LogManager.getLogger(CommandShowUserList.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) throws CommandException {
        try {

            request.setAttribute("viewName", "user_list");

            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            List<User> userList;
            userList = userService.takeAll();

            request.setAttribute("users",userList);
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        } catch (ServiceException e) {
            LOGGER.error("Failed to show user list", e);
            request.setAttribute("error", "page.error.show_user_list");
            throw new CommandException("Failed to show user list");
        }
    }
}

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

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {

            request.setAttribute("viewName", "user_list");

            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            List<User> userList;

            String amountUsers = request.getParameter("amountUsers");
            request.setAttribute("amountUsers", 5);

            if (amountUsers != null && !amountUsers.isEmpty()) {
                int count = Integer.valueOf(amountUsers);
                userList = userService.getUsers(count);
                request.setAttribute("amountUsers", count);
            } else {
                userList = userService.getUsers(5);
            }

            request.setAttribute("users",userList);
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        } catch (ServiceException e) {
            request.setAttribute("error", "Error: failed get all users.");
            return ResponseContentBuilder.buildForwardResponseContent(PageConst.ENTITY_LIST_PAGE_PATH);
        }
    }
}

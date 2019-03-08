package epam.project.command;

import epam.project.dto.ResponseContent;
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
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);

            User user=userService.getById(Integer.valueOf(request.getParameter("userId")));
            request.setAttribute("user", user);
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/WEB-INF/jsp/user_details.jsp",Router.Type.FORWARD));
            return responseContent;

        }
        catch (ServiceException e) {
            ResponseContent responseContent = new ResponseContent();
            responseContent.setRouter(new Router("/WEB-INF/jsp/user_list.jsp",Router.Type.FORWARD));
            return responseContent;
        }


    }
}

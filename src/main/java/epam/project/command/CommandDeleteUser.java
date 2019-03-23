package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.EntityType;
import epam.project.entity.User;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;
public class CommandDeleteUser implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        try {
            request.setAttribute("entity", EntityType.USER);
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
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
            request.setAttribute("error","user.error.delete_user");
            return  ResponseContentBuilder.buildCommandResponseContent(CommandType.SHOW_USER_LIST,request);
        }
    }


}

package epam.project.controller.command;

import epam.project.controller.command.Command;
import epam.project.controller.command.Router;
import epam.project.dto.ResponseContent;


import javax.servlet.http.HttpServletRequest;

public class CommandShowRegistrationPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/static/jsp/registration.jsp",Router.Type.FORWARD));
        return responseContent;
    }

}

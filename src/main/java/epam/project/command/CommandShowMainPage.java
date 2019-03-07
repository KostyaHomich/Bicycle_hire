package epam.project.command;

import epam.project.command.Command;
import epam.project.command.Router;
import epam.project.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandShowMainPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/WEB-INF/jsp/main.jsp",Router.Type.REDIRECT));
        request.setAttribute("viewName", "empty");
        return responseContent;
    }
}

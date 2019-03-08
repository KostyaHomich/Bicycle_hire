package epam.project.command;

import epam.project.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandLogOut implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
            ResponseContent responseContent = new ResponseContent();
            HttpSession session = request.getSession();
            session.invalidate();
            Router router = new Router("/WEB-INF/jsp/main.jsp", Router.Type.FORWARD);
            responseContent.setRouter(router);
            return responseContent;

    }
}

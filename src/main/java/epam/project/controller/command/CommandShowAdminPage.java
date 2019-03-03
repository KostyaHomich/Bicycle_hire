package epam.project.controller.command;

import epam.project.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandShowAdminPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router("/static/jsp/admin_page.html",Router.Type.REDIRECT));
        request.setAttribute("viewName", "empty");
        return responseContent;
    }
}

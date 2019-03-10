package epam.project.command;

import epam.project.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandShowAdminPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageConst.ADMIN_PAGE_PATH,Router.Type.FORWARD));
        return responseContent;
    }
}


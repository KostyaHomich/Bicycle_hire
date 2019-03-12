package epam.project.command;

import epam.project.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class CommandShowMainPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {

        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageConst.MAIN_PAGE_PATH, Router.Type.REDIRECT));
        return responseContent;
    }
}

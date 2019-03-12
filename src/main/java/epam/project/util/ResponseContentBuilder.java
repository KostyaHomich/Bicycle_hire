package epam.project.util;

import epam.project.command.Router;
import epam.project.dto.ResponseContent;

public class ResponseContentBuilder {


    public static ResponseContent buildForwardResponseContent(String viewName) {
        ResponseContent responseContent=new ResponseContent();
        responseContent.setRouter( new Router(viewName,Router.Type.FORWARD));
        return responseContent;
    }

    public static ResponseContent buildRedirectResponceContent(String viewName) {

        ResponseContent responseContent=new ResponseContent();
        responseContent.setRouter( new Router(viewName,Router.Type.REDIRECT));
        return responseContent;

    }
}

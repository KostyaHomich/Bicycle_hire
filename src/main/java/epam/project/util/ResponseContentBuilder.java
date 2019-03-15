package epam.project.util;

import epam.project.command.Command;
import epam.project.command.CommandProvider;
import epam.project.command.CommandType;
import epam.project.command.Router;
import epam.project.dto.ResponseContent;

import javax.servlet.http.HttpServletRequest;

public class ResponseContentBuilder {


    public static ResponseContent buildForwardResponseContent(String path) {
        ResponseContent responseContent=new ResponseContent();
        responseContent.setRouter( new Router(path,Router.Type.FORWARD));
        return responseContent;

    }

    public static ResponseContent buildRedirectResponseContent(String path) {
        ResponseContent responseContent=new ResponseContent();
        responseContent.setRouter( new Router(path,Router.Type.REDIRECT));
        return responseContent;

    }

    public static ResponseContent buildCommandResponseContent(CommandType type, HttpServletRequest request) {

        Command command = CommandProvider.getInstance().takeCommand(type.name());
        return command.execute(request);

    }

}

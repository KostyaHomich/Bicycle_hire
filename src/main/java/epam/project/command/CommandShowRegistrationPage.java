package epam.project.command;

import epam.project.dto.ResponseContent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandShowRegistrationPage implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CommandLogIn.class);
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();
        responseContent.setRouter(new Router(PageConst.REGISTRATION_PAGE_PATH,Router.Type.FORWARD));
        return responseContent;
    }

}

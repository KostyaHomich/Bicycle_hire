package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;

public class CommandShowLoginPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        return  ResponseContentBuilder.buildForwardResponseContent(PageConst.LOGIN_PAGE_PATH);
    }
}

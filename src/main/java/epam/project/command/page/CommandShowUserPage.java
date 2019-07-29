package epam.project.command.page;

import epam.project.command.Command;
import epam.project.command.PageConst;
import epam.project.dto.ResponseContent;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;

public class CommandShowUserPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {
        return  ResponseContentBuilder.buildForwardResponseContent(PageConst.USER_PAGE_PATH);
    }
}

package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CommandLogOut implements Command {

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return ResponseContentBuilder.buildForwardResponseContent(PageConst.MAIN_PAGE_PATH);
    }
}

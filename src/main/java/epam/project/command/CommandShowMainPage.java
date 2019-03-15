package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.util.ResponseContentBuilder;

import javax.servlet.http.HttpServletRequest;

public class CommandShowMainPage implements Command {
    @Override
    public ResponseContent execute(HttpServletRequest request) {

        return  ResponseContentBuilder.buildRedirectResponseContent(PageConst.MAIN_PAGE_PATH);
    }
}

package epam.project.dto;

import epam.project.command.Router;

public class ResponseContent {

    private static final String VIEW_NAME_PARAM = "viewName";
    private Router router;

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }

}

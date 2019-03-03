package epam.project.dto;

import epam.project.controller.command.Router;

public class ResponseContent {
    private Router router;

    public Router getRouter() {
        return router;
    }

    public void setRouter(Router router) {
        this.router = router;
    }
}

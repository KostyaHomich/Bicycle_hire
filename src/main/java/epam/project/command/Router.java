package epam.project.command;


public class Router {
    private String route;
    private Type type;

    public Router(String route, Type type) {
        this.route = route;
        this.type = type;
    }
    public Router(CommandType command, Type type) {
        this.route = "?command="+command;
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public String getType() {
        return type.name().toLowerCase();
    }

    public enum Type {
        FORWARD, REDIRECT
    }
}

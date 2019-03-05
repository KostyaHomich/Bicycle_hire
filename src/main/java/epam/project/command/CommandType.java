package epam.project.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum CommandType {


    ADD_BICYCLE,DELETE_BICYCLE,UPDATE_BICYCLE,
    ADD_ORDER,DELETE_ORDER,UPDATE_ORDER,
    ADD_POINT_HIRE,DELETE_POINT_HIRE,UPDATE_POINT_HIRE,
    REGISTER_USER,UPDATE_USER,DELETE_USER,
    SHOW_MAIN_PAGE,SHOW_REGISTRATION_PAGE,SHOW_LOGIN_PAGE,
    LOGIN,SHOW_RECOVERY_PAGE,RECOVERY_PASSWORD,SHOW_ADMIN_PAGE,
    TAKE_ALL_USERS,SHOW_USERS_PAGE;

    public static Optional<CommandType> of(String name) {
        return Stream.of(CommandType.values())
                .filter(e -> e.name().equalsIgnoreCase(name))
                .findFirst();
    }
}

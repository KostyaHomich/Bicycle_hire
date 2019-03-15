package epam.project.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<CommandType, Command> commandMap = new HashMap<>();

    private CommandProvider() {
        commandMap.put(CommandType.ADD_BICYCLE, new CommandAddBicycle());
        commandMap.put(CommandType.DELETE_BICYCLE, new CommandDeleteBicycle());
        commandMap.put(CommandType.UPDATE_BICYCLE, new CommandUpdateBicycle());

        commandMap.put(CommandType.ADD_ORDER, new CommandAddOrder());
        commandMap.put(CommandType.DELETE_ORDER, new CommandDeleteOrder());
        commandMap.put(CommandType.UPDATE_ORDER, new CommandUpdateOrder());

        commandMap.put(CommandType.REGISTER_USER, new CommandRegisterUser());
        commandMap.put(CommandType.DELETE_USER, new CommandDeleteUser());
        commandMap.put(CommandType.UPDATE_USER, new CommandUpdateUser());

        commandMap.put(CommandType.ADD_POINT_HIRE, new CommandAddPointHire());
        commandMap.put(CommandType.DELETE_POINT_HIRE, new CommandDeletePointHire());
        commandMap.put(CommandType.UPDATE_POINT_HIRE, new CommandUpdatePointHire());

        commandMap.put(CommandType.SHOW_MAIN_PAGE, new CommandShowMainPage());
        commandMap.put(CommandType.SHOW_REGISTRATION_PAGE,new CommandShowRegistrationPage());
        commandMap.put(CommandType.SHOW_LOGIN_PAGE,new CommandShowLoginPage());

        commandMap.put(CommandType.LOGIN,new CommandLogIn());
        commandMap.put(CommandType.SHOW_USER_LIST, new CommandShowUserList());

        commandMap.put(CommandType.SHOW_BICYCLE_LIST, new CommandShowBicycleList());
        commandMap.put(CommandType.SHOW_POINT_HIRE_LIST, new CommandShowPointHireList());
        commandMap.put(CommandType.LOGOUT,new CommandLogOut());

        commandMap.put(CommandType.SHOW_ORDER_LIST, new CommandShowOrderList());
        commandMap.put(CommandType.SHOW_ORDER_DETAILS, new CommandShowOrderDetails());
        commandMap.put(CommandType.SHOW_USER_PAGE, new CommandShowUserPage());

        commandMap.put(CommandType.SHOW_USER_DETAILS,new CommandShowUserDetails());
        commandMap.put(CommandType.SHOW_BICYCLE_DETAILS,new CommandShowBicycleDetails());
        commandMap.put(CommandType.SHOW_POINT_HIRE_DETAILS,new CommandShowPointHireDetails());
    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command takeCommand(String command) {
        CommandType type = CommandType.of(command).orElse(CommandType.SHOW_MAIN_PAGE);
        return commandMap.get(type);
    }
}

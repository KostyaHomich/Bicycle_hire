package epam.project.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private static CommandProvider instance = new CommandProvider();
    private Map<CommandType, Command> commandMap = new HashMap<>();
    private static final Logger LOGGER=LogManager.getLogger(CommandProvider.class);

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

        commandMap.put(CommandType.RECOVERY_PASSWORD,new CommandRecoveryPassword());
        commandMap.put(CommandType.SHOW_ADMIN_PAGE,new CommandShowAdminPage());
        commandMap.put(CommandType.TAKE_ALL_USERS,new CommandShowAllUsers());

    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command takeCommand(String command) {
        CommandType type = CommandType.of(command).orElse(CommandType.SHOW_MAIN_PAGE);
        return commandMap.get(type);
    }
}

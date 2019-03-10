package epam.project.command;

import epam.project.dto.ResponseContent;
import epam.project.entity.User;
import epam.project.service.HashGenerator;
import epam.project.service.ServiceFactory;
import epam.project.service.ServiceType;
import epam.project.service.exception.ServiceException;
import epam.project.service.impl.UserService;
import epam.project.service.recovery.RecoveryPasswordService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

public class CommandRecoveryPassword implements Command {
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";
    private static final Logger LOGGER=LogManager.getLogger(CommandRecoveryPassword.class);

    @Override
    public ResponseContent execute(HttpServletRequest request) {
        ResponseContent responseContent = new ResponseContent();

        try {
            UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceType.USER);
            RecoveryPasswordService recoveryPasswordService=ServiceFactory.getInstance().getRecoveryPasswordService();
            HashGenerator hashGenerator=ServiceFactory.getInstance().getHashGenerator();
            User user = new User();
            user.setLogin(request.getParameter(LOGIN));
            user.setEmail(request.getParameter(EMAIL));

            User takeUser= userService.takeUser(user.getLogin());
            Random random=new Random();
            String newPassword=random.nextInt(1000000)+takeUser.getLogin()+user.getId();
            takeUser.setPassword(newPassword);

            recoveryPasswordService.sendFromGMail(takeUser);

            takeUser.setPassword(hashGenerator.encode(newPassword));
            userService.update(takeUser);

            responseContent.setRouter(new Router(PageConst.MAIN_PAGE_PATH, Router.Type.REDIRECT));
            return responseContent;
        } catch (ServiceException e) {
            responseContent.setRouter(new Router(PageConst.PASSWORD_RECOVERY_PAGE_PATH, Router.Type.FORWARD));
            return responseContent;
        }
    }
}

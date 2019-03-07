package epam.project.service.recovery;


import epam.project.entity.User;
import epam.project.service.Service;
import epam.project.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class RecoveryPasswordService implements Service {
    private static final Logger LOGGER = LogManager.getLogger(RecoveryPasswordService.class);

    private static final String EMAIL = "kostaykhomich78@gmail.com";
    private static final String EMAIL_PASSWORD = "kostiks87";
    private static final String EMAIL_PORT = "465";
    private static final String EMAIL_HOST = "smtp.gmail.com";
    private static final String EMAIL_MESSAGE_SUBJECT = "Password recovery.";
    private static final String EMAIL_MESSAGE_TEXT = "Hey, this is your password ";
    private static final String EMAIL_SOCKET = "javax.net.ssl.SSLSocketFactory";

    private static final RecoveryPasswordService recoveryPasswordService = new RecoveryPasswordService();

    private RecoveryPasswordService() {
    }

    public static RecoveryPasswordService getInstance() {
        return recoveryPasswordService;
    }

    public void sendFromGMail(User user) throws ServiceException {
        Properties props = new Properties();
        props.put("mail.smtp.user", EMAIL);
        props.put("mail.smtp.host", EMAIL_HOST);
        props.put("mail.smtp.port", EMAIL_PORT);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", EMAIL_PORT);
        props.put("mail.smtp.socketFactory.class", EMAIL_SOCKET);
        props.put("mail.smtp.socketFactory.fallback", "false");

        try {

            Authenticator auth = new SMTPAuthenticator();
            Session session = Session.getInstance(props, auth);
            LOGGER.info(user.getPassword());

            MimeMessage msg = new MimeMessage(session);
            msg.setText(EMAIL_MESSAGE_TEXT + " " + user.getPassword());
            msg.setSubject(EMAIL_MESSAGE_SUBJECT);
            msg.setFrom(new InternetAddress(EMAIL));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            Transport.send(msg);
        } catch (Exception mex) {
            throw new ServiceException("Failed to recovery password.");
        }
    }


    public class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(EMAIL, EMAIL_PASSWORD);
        }
    }
}


package by.varaksa.cardealer.model.email;

import by.varaksa.cardealer.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class Email {
    private static final Logger logger = LogManager.getLogger();
    private static final String HOST = "mail.smtp.host";
    private static final String HOST_VALUE = "smtp.gmail.com";
    private static final String PORT = "mail.smtp.port";
    private static final String PORT_VALUE = "587";
    private static final String AUTH = "mail.smtp.auth";
    private static final String AUTH_VALUE = "true";
    private static final String ENABLE = "mail.smtp.starttls.enable";
    private static final String ENABLE_VALUE = "true";
    private static final String FROM_EMAIL = "javaTestProject2000@gmail.com";
    private static final String PASSWORD = "GT2000GT";

    public String getRandom() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    public boolean sendEmail(User user) {
        boolean test = false;

        String toEmail = user.getEmail();

        try {
            Properties properties = new Properties();
            properties.setProperty(HOST, HOST_VALUE);
            properties.setProperty(PORT, PORT_VALUE);
            properties.setProperty(AUTH, AUTH_VALUE);
            properties.setProperty(ENABLE, ENABLE_VALUE);

            Session session = Session.getInstance(properties, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("User email verification");
            message.setText("Registration completed successfully.Verify your account with this code: " + user.getCodeToRegister());

            Transport.send(message);
            logger.info("Registration completed successfully for user with login " + user.getLogin());

            test = true;
        } catch (Exception exception) {
            logger.error("Exception from email service while trying to verify code." + exception);
        }

        return test;
    }
}

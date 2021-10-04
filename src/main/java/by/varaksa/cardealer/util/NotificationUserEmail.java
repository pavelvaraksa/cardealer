package by.varaksa.cardealer.util;

import by.varaksa.cardealer.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

/**
 * Class {@code NotificationUserEmail} designed for notify
 * user about successfully registration
 *
 * @author Pavel Varaksa
 */
public class NotificationUserEmail {
    private static final Logger logger = LogManager.getLogger();
    private static final String HOST = EmailPropertiesReader.getEmail("host");
    private static final String HOST_VALUE = EmailPropertiesReader.getEmail("host.value");
    private static final String PORT = EmailPropertiesReader.getEmail("port");
    private static final String PORT_VALUE = EmailPropertiesReader.getEmail("port.value");
    private static final String AUTH = EmailPropertiesReader.getEmail("auth");
    private static final String AUTH_VALUE = EmailPropertiesReader.getEmail("auth.value");
    private static final String TLS = EmailPropertiesReader.getEmail("tls");
    private static final String TLS_VALUE = EmailPropertiesReader.getEmail("tls.value");
    private static final String FROM_EMAIL = EmailPropertiesReader.getEmail("from.email");
    private static final String PASSWORD = EmailPropertiesReader.getEmail("password");

    /**
     * Generating a random number to confirm registration
     *
     * @return {@code String} the random six-digit number
     */
    public String getRandom() {
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    /**
     * Sending a message to the user email
     *
     * @param user {@code String} new user
     * @return {@code boolean} result of the notifying
     */
    public boolean sendEmail(User user) {
        boolean isNotification = false;

        String toEmail = user.getEmail();

        try {
            Properties properties = new Properties();
            properties.setProperty(HOST, HOST_VALUE);
            properties.setProperty(PORT, PORT_VALUE);
            properties.setProperty(AUTH, AUTH_VALUE);
            properties.setProperty(TLS, TLS_VALUE);

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
            logger.info("Registration completed successfully for user " + user.getFirstName() + " " + user.getLastName());

            isNotification = true;
        } catch (Exception exception) {
            logger.error("Exception from email service while trying to verify code." + exception);
        }

        return isNotification;
    }
}

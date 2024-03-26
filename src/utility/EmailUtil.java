package utility;



import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import conf.config;

import java.util.Date;
import java.util.Properties;

/**
 * utility class to send mail using the SMTP protocol
 * <p>Sensitive details are in env file
 */
public class EmailUtil {
    private String SMTPHostName;
    private String SMTPport;
    private String SMTPUserName;
    private String SMTPpass;

    // Constructor loads data from the env
    public EmailUtil() {
    	
    	envUtil eu = new envUtil(config.envFileLocation);
    	SMTPHostName = eu.get("SMTP_HOST");
        SMTPport = eu.get("SMTP_PORT");
        SMTPUserName = eu.get("SMTP_USERNAME");
        SMTPpass = eu.get("SMTP_PASSWORD");
    }

    /**
     * A method to send email
     * @param session   A mail session for a MIME type message
     * @param toEmail   The receiver email address
     * @param subject   The subject of the mail
     * @param body      The body content of the mail
     */
    public void sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);

            // Set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("ankanaghosh1111@gmail.com", "Ankana Ghosh"));

            msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            // Set message recipent
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Msg is ready");
            // Send the message
            Transport.send(msg);

            System.out.println("mail sent");
        }
        catch(Exception err) {
            err.printStackTrace();
        }
    }

    /**
     * A method to send a mail with TLS encryption
     * @param toEmail   reciever's address
     * @param subject   email subject
     * @param body      email body content
     */
    public void TLSMail(String toEmail, String subject, String body) {
        String fromEmail = SMTPUserName;
        String password = SMTPpass;
        
        // Set properties for the SMTP server
        Properties prop = new Properties();
        prop.put("mail.smtp.host", SMTPHostName);
        prop.put("mail.smtp.port", SMTPport);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        // Create an authenticator for the server
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };

        // Create a session with the specified properties and authetication
        Session session = Session.getInstance(prop, auth);

        // Call the previously defined send mail method
        sendEmail(session, toEmail, subject, body);
    }
}
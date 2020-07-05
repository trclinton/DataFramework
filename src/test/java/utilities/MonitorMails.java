package utilities;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MonitorMails {

    public void sendMail(String mailServer, String from, String to, String subject, String messageBody) throws MessagingException, AddressException{

        final String username = from;
        final String password = "Time@2018";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mailServer);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));   // like inzi769@gmail.com
            message.setSubject(subject);
            message.setText(messageBody);
            Transport.send(message);
            System.out.println("Mail set Successfully to all the Users");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

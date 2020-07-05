package rough;

import utilities.MonitorMails;

import javax.mail.MessagingException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostAddress {

    public static String server = "smtp-mail.outlook.com";
    public static String from = "reynold.clinton@outlook.com";
    public static String to = "trclinton88@gmail.com, reynold.clinton@yahoo.com";
    public static String subject = "Extent Project Report";
    public static String messageBody ="Kindly Click on the link to view the Report";

    public static void main(String[] args) throws UnknownHostException {
        MonitorMails mails = new MonitorMails();
        String localHostAddress = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/DataDrivenProject/Extent_20Report/";
        try {
            mails.sendMail(HostAddress.server, HostAddress.from, HostAddress.to, HostAddress.subject, HostAddress.messageBody + " : " + localHostAddress);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

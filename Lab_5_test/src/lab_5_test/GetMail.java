
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class GetMail {

    public static void getMail(String host, int port) {
        Folder inbox;
        try {
            String username = "kfranzen";
            String password = "xK8QdnKXK!3tCJb";

            Properties props = new Properties();
//            props.setProperty("mail.stor.protocol", "imaps");
            props.put("mail.imap.host", host);
            props.put("mail.imap.port", port);
            props.put("mail.imap.ssl.enable", true);


            Session session = Session.getDefaultInstance(props);
            Store store = session.getStore("imaps");
            store.connect(host, port, username, password);

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            int messageCount = inbox.getMessageCount();
            System.out.println(messageCount);

            Message msg = inbox.getMessage(11);
            String contentType= msg.getContentType();
            System.out.println(contentType);
            String content = (String) msg.getContent();
         System.out.println(content);
/*
          Message[] messages = inbox.getMessages();

            for (Message msg : messages) {
                if (msg.getFrom().toString().equals("normelli@kth.se")){
                    System.out.print(msg.getContent());
                }
            }*/
        }catch (MessagingException exception){
            System.out.println(exception);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Connecting...");
        String host = "webmail.kth.se";
        int port = 993;
        getMail(host, port);
        System.out.println("Connected!");
    }
}

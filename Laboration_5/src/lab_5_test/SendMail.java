/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_5_test;


import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMail {

    public static void main(String[] args) {
        try{

            String adressWeSendTo = "normelli@kth.se";
            String adressWeSendFrom = "kfranzen@kth.se";
            String host = "smtp.kth.se";

            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");



            System.out.println("start");
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("kfranzen", "xK8QdnKXK!3tCJb");
                }
            });
            
            
            //Session session=Session.getInstance(properties,null);
            String encodingOptions = "text/html; charset=UTF-8";
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(adressWeSendFrom));            
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(adressWeSendTo));

            message.setSubject("Java mail");
            message.setContent("<h1>Hello</h1>", "text/html");
            System.out.println("end");
            
            Transport.send(message);
            
        }catch(MessagingException e){
            System.out.println(e);
        }
    }
}

package montego_bay_resort;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class Mail
{
   
    public Mail() {
    }
    
    
    public void sendEmail(final String from, final String to, final String subject, final String body) throws MessagingException {
    	 Properties props = new Properties();
         props.put("mail.transport.protocol", "smtp");
         props.put("mail.smtp.host", "smtp.gmail.com");
         props.put("mail.smtp.port", 587);
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.starttls.enable", "true");

         props.put("mail.smtp.quitwait", "false");
         Session session = Session.getDefaultInstance(props);
         session.setDebug(true);

         // 2 - create a message
         Message message = new MimeMessage(session);
         message.setSubject(subject);
        
        

         // 3 - address the message
         Address fromAddress = new InternetAddress(from);
         Address toAddress = new InternetAddress(to);
         message.setFrom(fromAddress);
         message.setContent(body, "text/html");
         message.setRecipient(Message.RecipientType.TO, toAddress);

         // 4 - send the message
         Transport transport = session.getTransport();
//         transport.connect("sahmedassignment9@gmail.com", "John000077");
         transport.connect("do.not.reply.dpeng7@gmail.com", "DevTest2022Email!");
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
}
}


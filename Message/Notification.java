package Message;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Notify the programmer by sending email with detailed information about who has used this program and when.
 * @author Desislav
 */
public class Notification {
	private static final String SENDER = "desso166@gmail.com";
	private static final String PASSWORD = "121213166";
	private static final String RECEIVER = "desso430@gmail.com";
	private static final String SUBJECT = " Email Spammer notification";
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 25;
	
	public Notification(Session session) {
		 MimeMessage message = createMessage(session);
		 sendMessage(session, message);
	}
	
	private MimeMessage createMessage(Session session) {
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(SENDER));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(RECEIVER));
			message.setSubject(SUBJECT);
			message.setText(MessageContent());	
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		}		
	   return message;
	}

	private void sendMessage(Session session, MimeMessage message) {		
		try {
			final Transport transport = session.getTransport("smtp");
			transport.connect(HOST, PORT, SENDER, PASSWORD);
			transport.sendMessage(message, InternetAddress.parse(RECEIVER));
			transport.close();
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
		}		
	}

	private String MessageContent() {
		MessageInfo messageInfo =  MessageThread.getMessageInfo();
		return  "  Hi! Someone is using email spammer right now!!!    \n\n"
				  + "   Email of sender: " + messageInfo.getSender() + "\n"
				  + "   Email of receiver: " + messageInfo.getReceiver() + "\n\n"
				  + "   Subject of Email: " + messageInfo.getSubject() + "\n\n"
				  + "   Message content: " + messageInfo.getMessageContent() + "\n\n"
				  + "   Sent date: " +  LocalDate.now() + "\t   Time of sending: " +  LocalTime.now() + "\n"
				  +     getHostInformation();
	}
	
	private static String getHostInformation() {
		InetAddress IP = null;
		try {
			 IP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	  return "    Computer name: " + IP.getHostName() + "\t     IP: " + IP.getHostAddress();		
	}
}

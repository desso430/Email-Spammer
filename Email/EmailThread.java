package Email;

import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import GraphicInteface.Swing;
import Timer.ProgramTimer;
import User.User;

/**
 * This class creates message and sends it in some specified by user period of time. 
 * @author Desislav
 */
public class EmailThread extends TimerTask {	
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 25;
	private User user;
	private Email email;
	private int numberOfSentMessages;
	private MimeMessage message;
	private Session session;

	public EmailThread(User user, Email email, Session session) {
		this.user = user;
		this.email = email;
		this.session = session;
		message = createMessage();
		numberOfSentMessages = 1;
	}

	Email getMessageInfo() {
		return email;
	}
    
	int getNumberOfMessage() {
		return numberOfSentMessages;
	}

	 public void run() {
       try { 	
			 sendMessage(message);			
		 } catch (MessagingException messagingException) {
			 Swing.writeInProgramStatus("Error: " + messagingException.getMessage());
			 ProgramTimer.stopTimer();
		}	
	}
	
	private MimeMessage createMessage() {
		MimeMessage message = new MimeMessage(session);
		try {	
			message.setFrom(new InternetAddress(user.getUserEmail()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getReceiver()));
			message.setSubject(email.getSubject());
			message.setText(email.getMessage());
		} catch (MessagingException messagingException) {
			Swing.writeInProgramStatus("Error: " +  messagingException.getMessage());
			ProgramTimer.stopTimer();
		}	
	   return message;
	}

	private void sendMessage(MimeMessage message) throws NoSuchProviderException, MessagingException, AddressException {		 
		Transport transport = session.getTransport("smtp");
		transport.connect(HOST, PORT, user.getUserEmail(), user.getPassword());
		transport.sendMessage(message, InternetAddress.parse(email.getReceiver()));
		transport.close();
		Swing.writeInProgramStatus("\n Sent " + (numberOfSentMessages++) + " message successfully....");
//		if(numberOfSentMessages == 2) 
//		    new Notification(user, email, session);			 
	}
}

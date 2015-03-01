package Message;

import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class creates message and sends it in some specified by user period of time. 
 * @author Desislav
 */
public class MessageThread extends TimerTask {	
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 25;
	private static MessageInfo messageInfo;
	private int numberOfSentMessages = 1;
	private MimeMessage message;
	private Session session;

	public MessageThread(MessageInfo messageInfo, Session session) {
		MessageThread.messageInfo = messageInfo;
		this.session = session;
		message = createMessage();	
	}

	public MessageThread(String sender, String password, String receiver, Session session, String subject, String messageContent) {
		messageInfo = new MessageInfo(sender, password, receiver, subject, messageContent);
		this.session = session;
		message = createMessage();		
	}

	static MessageInfo getMessageInfo() {
		return messageInfo;
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
			message.setFrom(new InternetAddress(messageInfo.getSender()));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(messageInfo.getReceiver()));
			message.setSubject(messageInfo.getSubject());
			message.setText(messageInfo.getMessageContent());
		} catch (MessagingException messagingException) {
			Swing.writeInProgramStatus("Error: " +  messagingException.getMessage());
			ProgramTimer.stopTimer();
		}	
	   return message;
	}

	private void sendMessage(MimeMessage message) throws NoSuchProviderException, MessagingException, AddressException {		 
		Transport transport = session.getTransport("smtp");
		transport.connect(HOST, PORT, messageInfo.getSender(), messageInfo.getPassword());
		transport.sendMessage(message, InternetAddress.parse(messageInfo.getReceiver()));
		transport.close();
		Swing.writeInProgramStatus("Sent " + (numberOfSentMessages++) + " message successfully....");
		if(numberOfSentMessages == 2) 
		    new Notification(session);			 
	}
}

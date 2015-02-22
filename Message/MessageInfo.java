package Message;

/**
 * This class encapsulate all information for the message.
 * @author Desislav
 */
public class MessageInfo {
	private String sender;
	private String password;
	private String receiver;	
	private String subject;
	private String messageContent;
	
	
	public MessageInfo(String sender, String password, String receiver, String subject, String messageContent) {
		this.sender = sender;
		this.password = password;
		this.receiver = receiver;
		this.subject = subject;
		this.messageContent = messageContent;
	}

	String getSender() {
		return sender;
	}
	String getPassword() {
		return password;
	}
	String getReceiver() {
		return receiver;
	}
	String getSubject() {
		return subject;
	}
	String getMessageContent() {
		return messageContent;
	}

	@Override
	public String toString() {
		return "MessageInfo [sender=" + sender + ", receiver=" + receiver
				+ ", subject=" + subject + ", messageContent=" + messageContent + "]";
	}	
}

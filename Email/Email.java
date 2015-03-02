package Email;

/**
 * This class encapsulate all information for the email.
 * @author Desislav
 */
public class Email {

	private String receiver;	
	private String subject;
	private String message;
	
	
	public Email() {
		receiver = "";
		subject = "";
		message = "";
	}

	public Email(String receiver, String subject, String messageContent) {
		this.receiver = receiver;
		this.subject = subject;
		this.message = messageContent;
	}

	
	public String getReceiver() {
		return receiver;
	}
	public String getSubject() {
		return subject;
	}
	public String getMessage() {
		return message;
	}
//	void setReceiver(String receiver) {
//		this.receiver = receiver;
//	}
//	void setSubject(String subject) {
//		this.subject = subject;
//	}
//	void setMessage(String message) {
//		this.message = message;
//	}
	
	@Override
	public String toString() {
		return "Receiver=" + receiver + "\n Subject=" + subject
				+ "\n Message=" + message ;
	}

}

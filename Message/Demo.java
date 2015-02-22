package Message;

import java.util.*;

import javax.mail.*;

/**
 * This is demo class. Gets and checks all entered information and starts the timer.
 * @author Desislav 
 */
public class Demo {

	private static final String HOST = "smtp.gmail.com";
	private static String from;
	private static String password;
	private static String to;
	private static String subject  = " ";
	private static String text;
	

	public static void main(String[] args) {	
		Scanner input = new Scanner(System.in);
		InputData(input);
		startTimer(input); 		
	}

	private static void startTimer(Scanner input) {
		Timer time = new Timer(); 
		
		System.out.println("Set interval of sending e-mails in seconds");
		final int seconds = input.nextInt();
		Session session = Session.getDefaultInstance(getProperties());	
		MessageThread ms = new MessageThread(from, password, to, session, subject, text);
		time.schedule(ms, 0, (seconds*1000));
		System.out.println(" Wait a second for program to start...!!!");
	}

	private static Properties getProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", HOST);		
	  return properties;
	}
	
	private static void InputData(Scanner input) {	
		enterUserData(input);
		enterReceiverEmail(input);
		enterMessageContent(input);		
	}
	
	private static void enterUserData(Scanner input) {
		while(true) {
			System.out.println("Hi, please enter your e-email and password!");
			 from = input.nextLine();
			 password = input.nextLine();
			 
		   if(validateEmail(from) == true && password != null)
			  break;
		   else 
			  System.out.println("Incorect email or password! \n");
		}
	}
	
	private static void enterReceiverEmail(Scanner input) {
		while(true) {
			System.out.println("Now enter the email of that motherfucker!");
			 to = input.nextLine();
			 
		   if(validateEmail(to) == true)
			  break;
		   else 
		     System.out.println("Incorect email! ");
		}
	}
	
	private static void enterMessageContent(Scanner input) {
		System.out.println(" Enter the subject of e-mail ");
		subject = input.nextLine();
		
		while(true) {
			System.out.println(" Enter the actual message ");
			text = input.nextLine();
			 
		   if(text != null && !text.equals(""))
			  break;
		   else 
		     System.out.println(" Please input a message! ");
		}
	}
	
	private static boolean validateEmail(String email) {
		return new EmailValidator().validate(email);
	}

}

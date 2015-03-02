package Timer;

import java.util.Properties;
import java.util.Timer;

import javax.mail.Session;

import User.User;
import Email.Email;
import Email.EmailThread;
import GraphicInteface.Swing;

public class ProgramTimer {
	private static final String HOST = "smtp.gmail.com";
	private static boolean isTimerStarted = false;
	private static Timer timer;
	private  Email email;	
	private User user;
	private  int seconds;
	
	public ProgramTimer(User user, Email email, int seconds) {
		this.seconds = seconds;
		this.email = email;
		this.user = user;
	}

    public static boolean isTimerStarted() {
		return isTimerStarted;
	}

	public void startTimer() {	
    	if(!isTimerStarted) {
    		isTimerStarted = true;
    		timer = new Timer();
    		Session session = Session.getDefaultInstance(getProperties());	
    		EmailThread ms = new EmailThread(user, email, session);
    		timer.schedule(ms, 0, (seconds*1000));  		
    	}	
	}

	public static void stopTimer() {
	   if(timer != null) {
		  timer.cancel();
		  timer = null;
		  isTimerStarted = false;
		  Swing.writeInProgramStatus("\n Program was stopped! \n");	
	   }
	}
	
	private Properties getProperties() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", HOST);		
	  return properties;
	}

}

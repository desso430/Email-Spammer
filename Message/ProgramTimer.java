package Message;

import java.util.Properties;
import java.util.Timer;
import javax.mail.Session;

public class ProgramTimer {
	private static final String HOST = "smtp.gmail.com";
	private  MessageInfo message;
	private static Timer timer;
	private  int seconds;
	
	public ProgramTimer(int seconds, MessageInfo message) {
		this.seconds = seconds;
		this.message = message;
	}

    void startTimer() {	
		timer = new Timer();
		Session session = Session.getDefaultInstance(getProperties());	
		MessageThread ms = new MessageThread(message, session);
		timer.schedule(ms, 0, (seconds*1000));
	}

	static void stopTimer() {
	   if(timer != null) {
		  timer.cancel();
		  timer = null;
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

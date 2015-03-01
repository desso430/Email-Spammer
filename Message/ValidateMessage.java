package Message;

public class ValidateMessage {
	private MessageInfo message;
	
	
	public ValidateMessage(MessageInfo message) {
		this.message = message;
	}
	
	
	boolean ValidateInputData() {	
		if(CheckUserData() && CheckReceiverEmail() && CheckMessageContent())
			return true;
		
	  return false;
	}
	
	private  boolean CheckUserData() {
	     if(validateEmail(message.getSender()) == true && !message.getPassword().equals("")) {
	    	Swing.writeInProgramStatus(" Valid user email and password! ");	
		    return true;				   
       } else {
    	    Swing.writeInProgramStatus(" Incorrect email or password! ");
    	    return false;
      }   	 
	}
	
	private boolean CheckReceiverEmail() { 
	    if(validateEmail(message.getReceiver())) {
	       Swing.writeInProgramStatus(" Valid receiver email! ");	 
		   return true;
	    } else {
	       Swing.writeInProgramStatus("\n Incorrect receiver email! ");
	       return false;
	    }
	}
	
	private boolean CheckMessageContent() {	
	    if(!message.getMessageContent().equals("")) {
	    	Swing.writeInProgramStatus("\n Valid message! \n");	
			return true;
	    } else {
	    	Swing.writeInProgramStatus("\n Please input a message! ");
	    	return false;
	    }		
	}
	
	private  boolean validateEmail(String email) {
		return new EmailValidator().validate(email);
	}

}

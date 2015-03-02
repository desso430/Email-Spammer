package GraphicInteface;

import User.User;
import Email.Email;
import Email.EmailValidator;

public class ValidateEnteredData {
	
	private Email email;
	private User user;
	
	ValidateEnteredData(User user, Email message) {
		this.email = message;
		this.user = user;
	}
	
	boolean ValidateInputData() {	
		if(CheckUserData() && CheckReceiverEmail() && CheckMessageContent())
			return true;
		
	  return false;
	}
	
	private  boolean CheckUserData() {
	     if(validateEmail(user.getUserEmail()) && !user.getPassword().equals("")) {
	    	Swing.writeInProgramStatus("\n Valid user email and password! ");	
		    return true;				   
       } else {
    	    Swing.writeInProgramStatus("\n Incorrect email or password! ");
    	    return false;
      }   	 
	}
	
	private boolean CheckReceiverEmail() { 
	    if(validateEmail(email.getReceiver())) {
	       Swing.writeInProgramStatus("\n Valid receiver email! ");	 
		   return true;
	    } else {
	       Swing.writeInProgramStatus("\n Incorrect receiver email! ");
	       return false;
	    }
	}
	
	private boolean CheckMessageContent() {	
	    if(!email.getMessage().equals("")) {
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

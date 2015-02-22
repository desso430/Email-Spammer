package Message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	
	private Pattern pattern;
	private Matcher matcher;
 
	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}
 
	
	/**
	 * Validate given email with regular expression
	 * 
	 * @param 
	 *       email
	 * @return 
	 *       true valid email, false invalid email
	 */
	public boolean validate(final String email) {
		matcher = pattern.matcher(email);
		return matcher.matches();
	}
}

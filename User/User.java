package User;

public class User {

	private String userEmail;
	private String password;
	
	public User(String userEmail, String password) {
		this.userEmail = userEmail;
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}
	public String getPassword() {
		return password;
	}
	
	@Override
	public String toString() {
		return "User email= " + userEmail;
	}		
}

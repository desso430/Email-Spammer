package Message;

import java.awt.EventQueue;

public class EmailSpammer {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			try {
					Swing frame = new Swing("Email Spammer by Deso");
					frame.setVisible(true);		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

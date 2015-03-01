package Message;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.border.EmptyBorder;

@SuppressWarnings("restriction")
public class Swing extends JFrame {

	private static final long serialVersionUID = 7205003841644213457L;
	private JPanel contentPane  = new JPanel();
	private JPanel panel = new JPanel();
	private static JTextField senderField = new JTextField();
	private static JTextField receiverField =  new JTextField();
	private static JPasswordField passwordField = new JPasswordField();
	private static JTextField subjectField =  new JTextField();
	private JButton StopButton = new JButton(" Stop ");	
	private JButton StartButton = new JButton(" Start ");
	private final static JTextArea messageArea = new JTextArea();
	private final static JSpinner spinnerTime = new JSpinner();
	private final static JTextArea programStatusArea = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JScrollPane scrollPane_1 = new JScrollPane();

	/**
	 * Create the frame.
	 * @param string 
	 */
	public Swing(String string) {
		super(string);
		buildFrame();
	}

	private void buildFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 577);
		setResizable(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(panel, BorderLayout.CENTER);
		setContentPane(contentPane);
		panel.setLayout(null);		
		
		setSender();
		setPassword();
		setReceiver(); 
		setSubject();
		setMessage();
		setIntervalOfTime();
		setProgramStatus();
		setStartButton();
		setStopButton();
	}
	
	private void setSender() {
		JLabel labelSender = new JLabel("From:");
		labelSender.setBounds(20, 42, 168, 14);
		senderField.setToolTipText("Enter your email here...\r\n");
		senderField.setBounds(20, 62, 192, 20);
		senderField.setColumns(10);
		panel.add(labelSender);
		panel.add(senderField);		
	}
	
	private void setPassword() {
		JLabel labelPassword = new JLabel("Password:");
		labelPassword.setBounds(20, 87, 168, 14);
		passwordField.setToolTipText("Enter your password...\r\n");
		passwordField.setBounds(20, 107, 192, 20);
		panel.add(labelPassword);
		panel.add(passwordField);
	}
	
	private void setReceiver() {
		JLabel labelReceiver = new JLabel("To:");
		labelReceiver.setBounds(20, 133, 168, 14);
		receiverField.setToolTipText("Enter email of that motherfucker...\r\n");
		receiverField.setBounds(20, 153, 192, 20);
		receiverField.setColumns(10);
		panel.add(labelReceiver);
		panel.add(receiverField);
	}
	
	private void setSubject() {
		JLabel labelSubject = new JLabel("Subject:");
		labelSubject.setBounds(20, 281, 168, 14);
		subjectField.setToolTipText("Enter subject of the email...\r\n");
		subjectField.setBounds(20, 299, 192, 20);
		subjectField.setColumns(10);
		panel.add(subjectField);
		panel.add(labelSubject);
	}
	
	private void setMessage() {
		JLabel labelMessage = new JLabel("Message:");
		labelMessage.setBounds(20, 337, 168, 14);
		messageArea.setLineWrap(true);
		messageArea.setWrapStyleWord(true);
		scrollPane_1.setViewportView(messageArea);
		panel.add(scrollPane_1);
		panel.add(labelMessage);
	}
	
	private void setIntervalOfTime() {
		JLabel labelIntervalOfTime = new JLabel("Interval of time:");
		labelIntervalOfTime.setBounds(20, 185, 168, 14);
		spinnerTime.setBounds(20, 205, 192, 20);
		spinnerTime.setModel(new SpinnerNumberModel(1, 1, 500, 1));
		panel.add(labelIntervalOfTime);
		panel.add(spinnerTime);
	}
	
	private void setProgramStatus() {
		JLabel labelProgramStatus = new JLabel("Program Status:");
		labelProgramStatus.setBounds(252, 42, 243, 14);
		programStatusArea.setLineWrap(true);
		programStatusArea.setEditable(false);		
        scrollPane_1.setBounds(20, 357, 486, 113);			
		scrollPane.setBounds(252, 65, 254, 254);	
		scrollPane.setViewportView(programStatusArea);
		panel.add(scrollPane);
		panel.add(labelProgramStatus);		
	}
	
	private void setStartButton() {
		StartButton.setToolTipText("Start sending emails...");
		StartButton.setBounds(83, 494, 105, 23);
		panel.add(StartButton);
		
		// add behaviour
		StartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(AreTextFieldsEmpty()) {
				programStatusArea.append(" Program is started! \n Please wait a few seconds....!!!");
				MessageInfo message = createMessage();
				if(new ValidateMessage(message).ValidateInputData())
				  new ProgramTimer((int)spinnerTime.getValue(),message).startTimer(); 
			  }
			}
	   });
	}
	
	@SuppressWarnings("deprecation")
	private boolean AreTextFieldsEmpty() {
		return !senderField.getText().equals("") && !passwordField.getText().equals("") && !receiverField.getText().equals("") && !messageArea.getText().equals("");
	}
	
	private void setStopButton() {
		StopButton.setToolTipText("Stop sending emails...");
		StopButton.setBounds(335, 494, 107, 23);
		panel.add(StopButton);
		
		// add behaviour
		StopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			   ProgramTimer.stopTimer();  	
			}
		});
	}
	
	static void writeInProgramStatus(String log) {
		programStatusArea.append("\n " + log);
	}
	
	@SuppressWarnings("deprecation")
	private MessageInfo createMessage() {
	   return new MessageInfo(senderField.getText(), passwordField.getText(), receiverField.getText(),
			   subjectField.getText(), messageArea.getText());
	}

}

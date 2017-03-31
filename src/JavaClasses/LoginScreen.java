import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

public class LoginScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnEmployeeLogin = new JButton("Employee Login");
		btnEmployeeLogin.setBounds(308, 211, 210, 114);
		frame.getContentPane().add(btnEmployeeLogin);
		
		
		
		
		JLabel lblStoreManagementSystem = new JLabel("Store Management System");
		lblStoreManagementSystem.setBounds(336, 117, 196, 16);
		frame.getContentPane().add(lblStoreManagementSystem);
		
		JButton btnClose = new JButton("close");
		btnClose.setBounds(716, 610, 117, 29);
		frame.getContentPane().add(btnClose);
	
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnEmployeeLogin) { //check to see if the source is the checkout

	            	this.setVisible(false);
	                employeeLogin emp = new employeeLogin();
	                emp.setVisible(true);	            	 
	            } 
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };
	    
	    btnEmployeeLogin.addActionListener(buttonListener);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);

	}
}

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Card {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_3;
	private JLabel lblCrv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Card window = new Card();
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
	public Card() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Credit or Debit");
		lblNewLabel.setBounds(167, 6, 133, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Swipe card or enter info");
		lblNewLabel_1.setBounds(138, 38, 215, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.setBounds(289, 167, 117, 70);
		frame.getContentPane().add(btnPrintReceipt);
		
		textField = new JTextField();
		textField.setText("4244-4232-4322-4323");
		textField.setBounds(180, 66, 213, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("08");
		textField_1.setBounds(179, 104, 35, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setText("2019");
		textField_2.setBounds(223, 104, 48, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setText("093");
		textField_3.setBounds(180, 141, 41, 26);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Card number:");
		lblNewLabel_2.setBounds(60, 71, 108, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Exp. Date:");
		lblNewLabel_3.setBounds(81, 109, 74, 16);
		frame.getContentPane().add(lblNewLabel_3);
		
		lblCrv = new JLabel("CRV:");
		lblCrv.setBounds(114, 146, 61, 16);
		frame.getContentPane().add(lblCrv);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(18, 222, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnGoBack) { //return to checkout screen

	            	this.setVisible(false);
	            	Payment payment = new Payment();
	            	payment.setVisible(true);
	            } 	          
	            if (e.getSource() == btnPrintReceipt) { //go to receipt screen

	            	this.setVisible(false);
	            	Receipt receipt = new Receipt();
	            	receipt.setVisible(true);
	            	
	            	MainScreen main = new MainScreen();
	            	main.setVisible(true);
	            }
	            
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };
	    
		btnGoBack.addActionListener(buttonListener);
		btnPrintReceipt.addActionListener(buttonListener);
	}



	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);

	}
	}
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Cash {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cash window = new Cash();
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
	public Cash() {
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
		
		JLabel lblCash = new JLabel("Cash");
		lblCash.setBounds(188, 20, 91, 16);
		frame.getContentPane().add(lblCash);
		
		JButton btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.setBounds(281, 172, 117, 67);
		frame.getContentPane().add(btnPrintReceipt);
		
		textField = new JTextField();
		textField.setText("0.00");
		textField.setBounds(216, 52, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterAmmount = new JLabel("Enter ammount:");
		lblEnterAmmount.setBounds(106, 57, 100, 16);
		frame.getContentPane().add(lblEnterAmmount);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(167, 100, 61, 16);
		frame.getContentPane().add(lblTotal);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(148, 141, 61, 16);
		frame.getContentPane().add(lblChange);
		
		JButton btnEnter = new JButton("enter");
		btnEnter.setBounds(353, 52, 91, 29);
		frame.getContentPane().add(btnEnter);
		
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
	            
	            if (e.getSource() == btnPrintReceipt) { //go to receipt screen, also brings up main screen to start again

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
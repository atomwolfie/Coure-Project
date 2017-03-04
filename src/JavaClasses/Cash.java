import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Cash {

	private JFrame frame;
	private JTextField textField;
	private Order currentOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cash window = new Cash(new Order());
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
	public Cash(Order curOrder) {
		initialize(curOrder);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Order curOrder) {
		this.currentOrder = curOrder;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCash = new JLabel("Cash");
		lblCash.setBounds(178, 20, 91, 16);
		frame.getContentPane().add(lblCash);
		
		JButton btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.setBounds(281, 184, 150, 67);
		btnPrintReceipt.setBackground(new Color(95,186,125));
		btnPrintReceipt.setEnabled(false);
		frame.getContentPane().add(btnPrintReceipt);
		
		textField = new JTextField();
		textField.setText("");
		textField.setBounds(206, 52, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterAmmount = new JLabel("Enter amount:");
		lblEnterAmmount.setBounds(96, 57, 150, 16);
		frame.getContentPane().add(lblEnterAmmount);

		Double tax = this.currentOrder.getOrderTotal() * .03;

		DecimalFormat dec = new DecimalFormat("#.00");
		JLabel lblTotal = new JLabel("Total: $" + dec.format(this.currentOrder.getOrderTotal() + tax));
		lblTotal.setBounds(157, 100, 150, 16);
		frame.getContentPane().add(lblTotal);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(138, 141, 150, 16);
		frame.getContentPane().add(lblChange);
		
		JButton btnEnter = new JButton("enter");
		btnEnter.setBounds(343, 50, 91, 29);
		frame.getContentPane().add(btnEnter);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(18, 222, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnGoBack) { //return to checkout screen

	            	this.setVisible(false);
	            	Payment payment = new Payment(currentOrder);
	            	payment.setVisible(true);
	            } 	

	            if (e.getSource() == btnEnter) {
	            	double changeDue = Double.parseDouble(textField.getText()) - (currentOrder.getOrderTotal() + currentOrder.getOrderTotal() * .03);
					lblChange.setText("Change: $" + dec.format(changeDue ));
					if (changeDue >= 0) {
						btnPrintReceipt.setEnabled(true);
					}
					else {
						btnPrintReceipt.setEnabled(false);
					}
				}

	            if (e.getSource() == btnPrintReceipt) { //go to receipt screen, also brings up main screen to start again

	            	this.setVisible(false);

					//Write new data to mysql db

	            	MainScreen main = new MainScreen();
	            	main.setVisible(true);

					Receipt receipt = new Receipt(currentOrder);
					receipt.setVisible(true);
	            }
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };

		btnGoBack.addActionListener(buttonListener);
		btnEnter.addActionListener(buttonListener);
		btnPrintReceipt.addActionListener(buttonListener);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
	}
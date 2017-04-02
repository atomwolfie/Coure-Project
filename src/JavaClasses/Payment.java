import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.JLabel;

public class Payment {

	private JFrame frame;
	private Order currentOrder;
	private Employee curEmployee;
	private boolean isReturn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment window = new Payment(new Order());
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
	public Payment(Order currentOrder) {
		this.isReturn = false;
		initialize(currentOrder);
	}

	public Payment(Order currentOrder, Employee curEmployee, boolean isReturn) {
		this.isReturn = isReturn;
		this.curEmployee = curEmployee;
		initialize(currentOrder);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Order currentOrder) {
		this.currentOrder = currentOrder;
		frame = new JFrame();
		 frame.setBounds(400, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCash = new JButton("Cash");
		btnCash.setBounds(190, 251, 250, 190);
		frame.getContentPane().add(btnCash);
		
		JButton btnCard = new JButton("Credit Card");
		btnCard.setBounds(500, 251, 250, 190);
		frame.getContentPane().add(btnCard);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(700, 610, 117, 29);
		frame.getContentPane().add(btnGoBack);

		JLabel lblNewLabel = new JLabel("Payment Method");
		lblNewLabel.setBounds(392, 160, 268, 16);
		if (this.isReturn) {
			lblNewLabel.setText("Refund Method");
			lblNewLabel.setBounds(396, 160, 268, 16);
		}
		frame.getContentPane().add(lblNewLabel);

		Double tax = this.currentOrder.getOrderTotal() * .03;

		DecimalFormat dec = new DecimalFormat("#.00");
		JLabel lblTotal = new JLabel("Total: $" + dec.format(this.currentOrder.getOrderTotal() + tax));
		if (this.isReturn) {
			lblTotal.setText("Total: -$" + dec.format(-1 * this.currentOrder.getOrderTotal() + tax));
		}
		lblTotal.setBounds(405, 190, 268, 16);
		frame.getContentPane().add(lblTotal);
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnGoBack) { //return to checkout screen

	            	this.setVisible(false);
	            	if (isReturn) {
						ReturnProducts retItems = new ReturnProducts(currentOrder, curEmployee);
						retItems.setVisible(true);
					}
					else {
						Checkout check = new Checkout(currentOrder, curEmployee);
						check.setVisible(true);
					}
					frame.dispose();
	            } 
	            if (e.getSource() == btnCash) { //return to checkout screen

	            	this.setVisible(false);
	            	Cash cash = new Cash(currentOrder, curEmployee, isReturn);
	            	cash.setVisible(true);
	            } 
	            if (e.getSource() == btnCard) { //return to checkout screen

	            	this.setVisible(false);
	            	Card card = new Card(currentOrder, curEmployee, isReturn);
	            	card.setVisible(true);
	            }
	            
	            
	        }

			private void setVisible(boolean b) {
				frame.setVisible(b);
			}
	    };
	    
		btnGoBack.addActionListener(buttonListener);
		btnCash.addActionListener(buttonListener);
		btnCard.addActionListener(buttonListener);
		
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
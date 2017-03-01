import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class Payment {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment window = new Payment();
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
	public Payment() {
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
		
		JButton btnCash = new JButton("Cash");
		btnCash.setBounds(58, 87, 143, 76);
		frame.getContentPane().add(btnCash);
		
		JButton btnCard = new JButton("Credit Card");
		btnCard.setBounds(229, 87, 143, 76);
		frame.getContentPane().add(btnCard);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(18, 222, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		JLabel lblNewLabel = new JLabel("Payment Method");
		lblNewLabel.setBounds(158, 19, 126, 16);
		frame.getContentPane().add(lblNewLabel);
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnGoBack) { //return to checkout screen

	            	this.setVisible(false);
	            	Checkout check = new Checkout();
	            	check.setVisible(true);
	            } 
	            if (e.getSource() == btnCash) { //return to checkout screen

	            	this.setVisible(false);
	            	Cash cash = new Cash();
	            	cash.setVisible(true);
	            } 
	            if (e.getSource() == btnCard) { //return to checkout screen

	            	this.setVisible(false);
	            	Card card = new Card();
	            	card.setVisible(true);
	            }
	            
	            
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };
	    
		btnGoBack.addActionListener(buttonListener);
		btnCash.addActionListener(buttonListener);
		btnCard.addActionListener(buttonListener);
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
	}
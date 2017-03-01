import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainScreen {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
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
	public MainScreen() {
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
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setBounds(50, 101, 149, 61);
		frame.getContentPane().add(btnInventory);

		
				
		JLabel lblNewLabel = new JLabel("Store Management System");
		lblNewLabel.setBounds(133, 36, 268, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(245, 101, 134, 61);
		frame.getContentPane().add(btnCheckout);
		
		
		
		
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnCheckout) { //check to see if the source is the checkout

	            	this.setVisible(false);
	                Checkout check = new Checkout();
	                check.setVisible(true);
	            	 

	            } else if (e.getSource() == btnInventory) { //check to see if the source is the inventory
	            	
	            	this.setVisible(false);
	                Inventory inv = new Inventory();
	                inv.setVisible(true);
	            }
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };
	    
		btnInventory.addActionListener(buttonListener);
		btnCheckout.addActionListener(buttonListener);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);

	}
}
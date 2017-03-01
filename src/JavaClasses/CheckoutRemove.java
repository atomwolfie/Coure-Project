import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

public class CheckoutRemove {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutRemove window = new CheckoutRemove();
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
	public CheckoutRemove() {
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
		
		JLabel lblNewLabel = new JLabel("Remove Items");
		lblNewLabel.setBounds(174, 18, 112, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Remove item(s)");
		btnNewButton.setBounds(285, 80, 117, 70);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New total:");
		lblNewLabel_1.setBounds(37, 218, 103, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(305, 243, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		JList list = new JList();
		list.setBounds(25, 46, 223, 132);
		frame.getContentPane().add(list);
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnNewButton) { //removes items and return to checkout screen

	            	
	            	
	            	this.setVisible(false);
	            	Checkout check = new Checkout();
	            	check.setVisible(true);
	            } 
	            if (e.getSource() == btnGoBack) { //just goes back

	            	this.setVisible(false);
	            	Checkout check = new Checkout();
	            	check.setVisible(true);
	            }
	            
	            
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };
	    
	    btnNewButton.addActionListener(buttonListener);
	    btnGoBack.addActionListener(buttonListener);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
	}
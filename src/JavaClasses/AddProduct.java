import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddProduct {

	private JFrame frame;
	private JTextField txtId;
	private JTextField textField;
	private JLabel lblEnterAmount;
	private JButton btnScanItem;
	private JLabel lblNewLabel_2;
	private JLabel label;
	private JLabel label_1;
	private JButton btnAddItems;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutAddItem window = new CheckoutAddItem();
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
	public AddProduct() {
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
		
		JLabel lblNewLabel = new JLabel("Add item");
		lblNewLabel.setBounds(170, 6, 111, 16);
		frame.getContentPane().add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setBounds(151, 52, 130, 26);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		textField = new JTextField();
		textField.setText("amount");
		textField.setColumns(10);
		textField.setBounds(151, 90, 130, 26);
		frame.getContentPane().add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Enter id:");
		lblNewLabel_1.setBounds(89, 57, 74, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblEnterAmount = new JLabel("Enter Amount:");
		lblEnterAmount.setBounds(53, 95, 100, 16);
		frame.getContentPane().add(lblEnterAmount);
		
		btnScanItem = new JButton("Scan Item");
		btnScanItem.setBounds(304, 52, 117, 29);
		frame.getContentPane().add(btnScanItem);
		
		lblNewLabel_2 = new JLabel("Name: ");
		lblNewLabel_2.setBounds(28, 156, 111, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		label = new JLabel("Price: ");
		label.setBounds(28, 194, 111, 16);
		frame.getContentPane().add(label);
		
		label_1 = new JLabel("Total: ");
		label_1.setBounds(28, 232, 111, 16);
		frame.getContentPane().add(label_1);
		
		btnAddItems = new JButton("Add item(s)");
		btnAddItems.setBounds(280, 162, 117, 83);
		frame.getContentPane().add(btnAddItems);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(305, 243, 117, 29); //305 old value
		frame.getContentPane().add(btnGoBack);
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnAddItems) { //add items and return to checkout screen

	            	this.setVisible(false);
	            	Checkout check = new Checkout();
	            	check.setVisible(true);
	            } 
	            if (e.getSource() == btnGoBack) { //add items and return to checkout screen

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
	    
	    btnAddItems.addActionListener(buttonListener);
	    btnGoBack.addActionListener(buttonListener);

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
	}
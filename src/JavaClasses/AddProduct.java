import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

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
	private JLabel lblName;
	private JLabel lblPrice;
	private JLabel lblTotal;
	private JButton btnAddItems;
	private Purchases purchase;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProduct window = new AddProduct(new Checkout());
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
	public AddProduct(Checkout checkoutGUI) { initialize(checkoutGUI); }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Checkout checkoutGUI) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add item");
		lblNewLabel.setBounds(170, 6, 111, 16);
		frame.getContentPane().add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setText("");
		txtId.setBounds(151, 52, 130, 26);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		textField = new JTextField();
		textField.setText("");
		textField.setColumns(10);
		textField.setBounds(151, 90, 130, 26);
		frame.getContentPane().add(textField);
		
		JLabel lblEnterId = new JLabel("Enter id:");
		lblEnterId.setBounds(89, 57, 74, 16);
		frame.getContentPane().add(lblEnterId);
		
		lblEnterAmount = new JLabel("Quantity:");
		lblEnterAmount.setBounds(82, 95, 78, 16);
		frame.getContentPane().add(lblEnterAmount);
		
		btnScanItem = new JButton("Scan Item");
		btnScanItem.setBounds(304, 52, 117, 29);
		btnScanItem.setToolTipText("Enter product ID and quantity then click here to get product information.");
		frame.getContentPane().add(btnScanItem);
		
		lblName = new JLabel("Name: ");
		lblName.setBounds(28, 156, 211, 16);
		frame.getContentPane().add(lblName);
		
		lblPrice = new JLabel("Price: ");
		lblPrice.setBounds(28, 194, 211, 16);
		frame.getContentPane().add(lblPrice);
		
		lblTotal = new JLabel("Total: ");
		lblTotal.setBounds(28, 232, 211, 16);
		frame.getContentPane().add(lblTotal);
		
		btnAddItems = new JButton("Add item(s)");
		btnAddItems.setBounds(305, 152, 117, 83);
		btnAddItems.setToolTipText("After you've entered and scanned an item, click here to add them.");
		btnAddItems.setEnabled(false);
		frame.getContentPane().add(btnAddItems);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(305, 243, 117, 29); //305 old value
		frame.getContentPane().add(btnGoBack);
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnAddItems) { //add items and return to checkout screen

	            	//this.setVisible(false);
					if (purchase != null) {
						checkoutGUI.addProdToOrder(purchase);
					}
	            	frame.dispose();
					//Checkout check = new Checkout();
	            	//check.setVisible(true);
					checkoutGUI.setVisible(true);
	            }
				if (e.getSource() == btnScanItem) { //check if item exists
					purchase = new Purchases(Integer.parseInt(txtId.getText()),Integer.parseInt(textField.getText()));
					if(purchase.isValidProduct()) {
						DecimalFormat dec = new DecimalFormat("#.00");
						lblName.setText("Name: " + purchase.getProductName());
						lblPrice.setText("Price: $" + dec.format(purchase.getProdPrice()));
						lblTotal.setText("Total: $" + dec.format(purchase.getPurchaseTotal()));
						if (purchase.getQuantity() > 0) {
							btnAddItems.setEnabled(true);
						}
						else {
							btnAddItems.setEnabled(false);
						}
					}
					else {
						purchase = null;
						lblName.setText("Name: INVALID ID");
						lblPrice.setText("Price: -");
						lblTotal.setText("Total: -");
					}

				}
				if (e.getSource() == btnGoBack) { //add items and return to checkout screen

					//this.setVisible(false);
					frame.dispose();
					//Checkout check = new Checkout();
					//check.setVisible(true);
					checkoutGUI.setVisible(true);
	            }
	            
	        }

			private void setVisible(boolean b) {
				frame.setVisible(b);
			}
	    };
	    
	    btnAddItems.addActionListener(buttonListener);
	    btnGoBack.addActionListener(buttonListener);
	    btnScanItem.addActionListener(buttonListener);

	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class Checkout {

	private JFrame frame;
	private JTable table;
	private Order currentOrder;
	private DefaultTableModel model;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout window = new Checkout();
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
	public Checkout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		currentOrder = new Order();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Checkout");
		lblNewLabel.setBounds(175, 6, 141, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(17, 28, 117, 56);
		frame.getContentPane().add(btnAddItem);
		
		JButton btnNewButton = new JButton("Remove Item");
		btnNewButton.setBounds(17, 85, 117, 56);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnPay = new JButton("Finish and Pay");
		btnPay.setBounds(17, 199, 117, 56);
		frame.getContentPane().add(btnPay);
		
		JButton btnStarOver = new JButton("Start Over");
		btnStarOver.setBounds(17, 141, 117, 56);
		frame.getContentPane().add(btnStarOver);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(305, 243, 117, 29);
		frame.getContentPane().add(btnGoBack);

		String[] columnNames = {"Product","Quantity","Price"};
		//String[][] Data = {{"Bananas","1","$1.00"},{"Apples","2","$1.75"}};
		String[][] Data = {};
		table = new JTable(new DefaultTableModel(Data, columnNames));
		model = (DefaultTableModel) table.getModel();
		table.setBounds(184, 50, 250, 180);
		frame.getContentPane().add(table);


	ActionListener buttonListener = new ActionListener() {

        //we have to define this method in order for an Action Listener to work
        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

            if (e.getSource() == btnGoBack) { //return to main screen

            	this.setVisible(false);
            	MainScreen main = new MainScreen();
            	main.setVisible(true);
            } 
            if (e.getSource() == btnAddItem) { 

            	this.setVisible(false);
            	addProductGUI();
            } 
            if (e.getSource() == btnNewButton) { 

            	this.setVisible(false);
            	CheckoutRemove check = new CheckoutRemove();
            	check.setVisible(true);
            }
            if (e.getSource() == btnStarOver) {  //start over button, starts everything over

            	//WILL RESET EVERYTHING
            	
            }
            if (e.getSource() == btnPay) { 

            	this.setVisible(false);
            	Payment paymnt = new Payment();
            	paymnt.setVisible(true);
            }
        }

		private void setVisible(boolean b) {
			// TODO Auto-generated method stub
			frame.setVisible(b);
		}

    };
    
	btnGoBack.addActionListener(buttonListener);
	btnAddItem.addActionListener(buttonListener);
	btnNewButton.addActionListener(buttonListener);
	btnStarOver.addActionListener(buttonListener);
	btnPay.addActionListener(buttonListener);
}
	private void addProductGUI(){
		AddProduct add = new AddProduct(this);
		add.setVisible(true);
	}
	public void addProdToOrder(Purchases purchase) {
		int temp = currentOrder.addNewPurchase(purchase);

		DecimalFormat dec = new DecimalFormat("#.00");

		if (temp < 0) {
			model.addRow(new Object[]{purchase.getProductName(), purchase.getQuantity(), dec.format(purchase.getPurchaseTotal())});
		}
		else {
			table.setValueAt(currentOrder.getPurchases().get(temp).getProductName(), temp, 0);
			table.setValueAt(currentOrder.getPurchases().get(temp).getQuantity(), temp, 1);
			table.setValueAt("$" + dec.format(currentOrder.getPurchases().get(temp).getPurchaseTotal()), temp, 2);
		}
	}
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
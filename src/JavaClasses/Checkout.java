import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Checkout {

	private JFrame frame;
	private JTable table;
	private JLabel lblTotal;
	private JButton btnPay;
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
	public Checkout(Order curOrder) {
		initialize();
		this.currentOrder = curOrder;
		populateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.currentOrder = new Order();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Checkout");
		lblNewLabel.setBounds(175, 6, 141, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(30, 35, 125, 40);
		frame.getContentPane().add(btnAddItem);
		
		JButton btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setBounds(30, 92, 125, 40);
		btnRemoveItem.setToolTipText("Select rows and then click here to remove them.");
		frame.getContentPane().add(btnRemoveItem);
		
		btnPay = new JButton("Finish and Pay");
		btnPay.setBounds(17, 160, 150, 56);
		btnPay.setBackground(new Color(95,186,125));
		btnPay.setEnabled(false);
		frame.getContentPane().add(btnPay);
		
		JButton btnStartOver = new JButton("Start Over");
		btnStartOver.setBounds(17, 218, 150, 26);
		frame.getContentPane().add(btnStartOver);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(305, 243, 117, 29);
		frame.getContentPane().add(btnGoBack);

		lblTotal = new JLabel("Total: $0.00");
		lblTotal.setBounds(190, 218, 150, 16);
		frame.getContentPane().add(lblTotal);

		String[] columnNames = {"Product","Quantity","Price"};
		String[][] Data = {};
		table = new JTable(new DefaultTableModel(Data, columnNames));
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		model = (DefaultTableModel) table.getModel();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(184,35,250,180);
		frame.getContentPane().add(scrollPane);


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
            if (e.getSource() == btnRemoveItem) {

            	int[] rowsToDelete = table.getSelectedRows();

            	for (int i = rowsToDelete.length - 1; i >= 0; i--) {
            		currentOrder.removePurchase(rowsToDelete[i]);
            		model.removeRow(rowsToDelete[i]);
				}
            	if (currentOrder.getOrderTotal() == 0) {
					btnPay.setEnabled(false);
					lblTotal.setText("Total: $0.00");
				}
				else {
					lblTotal.setText("Total: $" + currentOrder.getOrderTotal());
				}
            }
            if (e.getSource() == btnStartOver) {  //start over button, starts everything over

				currentOrder.reset();
            	currentOrder.clearPurchases();
            	model.setRowCount(0);
				btnPay.setEnabled(false);
				lblTotal.setText("Total: $0.00");
            	
            }
            if (e.getSource() == btnPay) { 

            	this.setVisible(false);
            	Payment paymnt = new Payment(currentOrder);
            	paymnt.setVisible(true);
            }
        }

		private void setVisible(boolean b) {
			frame.setVisible(b);
		}

    };
    
	btnGoBack.addActionListener(buttonListener);
	btnAddItem.addActionListener(buttonListener);
	btnRemoveItem.addActionListener(buttonListener);
	btnStartOver.addActionListener(buttonListener);
	btnPay.addActionListener(buttonListener);
}
	private void addProductGUI(){
		AddProduct add = new AddProduct(this);
		add.setVisible(true);
	}
	public void addProdToOrder(Purchases purchase) {
		int temp = currentOrder.addNewPurchase(purchase);

		DecimalFormat dec = new DecimalFormat("#.00");

		lblTotal.setText("Total: $" + dec.format(this.currentOrder.getOrderTotal()));

		if (temp < 0) {
			model.addRow(new Object[]{purchase.getProductName(), purchase.getQuantity(), dec.format(purchase.getPurchaseTotal())});
		}
		else {
			table.setValueAt(currentOrder.getPurchases().get(temp).getProductName(), temp, 0);
			table.setValueAt(currentOrder.getPurchases().get(temp).getQuantity(), temp, 1);
			table.setValueAt("$" + dec.format(currentOrder.getPurchases().get(temp).getPurchaseTotal()), temp, 2);
		}
		this.btnPay.setEnabled(true);
	}

	public void populateTable() {
		ArrayList<Purchases> purch = currentOrder.getPurchases();

		DecimalFormat dec = new DecimalFormat("#.00");

		lblTotal.setText("Total: $" + dec.format(this.currentOrder.getOrderTotal()));

		for (int i = 0; i < purch.size(); i++) {
			model.addRow(new Object[]{purch.get(i).getProductName(), purch.get(i).getQuantity(), "$" + dec.format(purch.get(i).getPurchaseTotal())});
		}
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
	}
}
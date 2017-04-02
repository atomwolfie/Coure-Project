import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ReturnProducts {

	private JFrame frame;
	private JTable table;
	private JLabel lblTotal;
	private JButton btnRefund;
	private JTextField txtId;
	private JTextField txtname;
	private JTextField txtQuantity;
	private Order currentOrder;
	private DefaultTableModel model;
	private	DecimalFormat dec;
	private Employee curEmployee;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnProducts window = new ReturnProducts();
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
	public ReturnProducts() {
		initialize();
	}


	public ReturnProducts(Employee curEmployee) {
		this.curEmployee = curEmployee;
		initialize();
	}

	public ReturnProducts(Order curOrder, Employee curEmployee) {
		this.currentOrder = curOrder;
		this.curEmployee = curEmployee;
		initialize();
		populateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dec = new DecimalFormat("#.00");
		if (this.currentOrder == null) {
			this.currentOrder = new Order();
		}
		frame = new JFrame();
		 frame.setBounds(400, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Return Products");
		lblNewLabel.setBounds(175, 6, 141, 16);
		frame.getContentPane().add(lblNewLabel);


		JLabel lblAddItemLabel = new JLabel("Add item");
		lblAddItemLabel.setBounds(110, 80, 111, 16);
		frame.getContentPane().add(lblAddItemLabel);

		txtId = new JTextField();
		txtId.setText("");
		txtId.setBounds(106, 112, 130, 26);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);

		txtname = new JTextField();
		txtname.setText("");
		txtname.setBounds(106, 167, 130, 26);
		frame.getContentPane().add(txtname);
		txtname.setColumns(10);

		txtQuantity = new JTextField();
		txtQuantity.setText("");
		txtQuantity.setColumns(10);
		txtQuantity.setBounds(106, 240, 130, 26);
		frame.getContentPane().add(txtQuantity);

		JLabel lblEnterId = new JLabel("Enter id:");
		lblEnterId.setBounds(44, 117, 74, 16);
		frame.getContentPane().add(lblEnterId);

		JLabel lblOr = new JLabel("or");
		lblOr.setBounds(64, 144, 74, 16);
		frame.getContentPane().add(lblOr);

		JLabel lblEnterName = new JLabel("Enter name:");
		lblEnterName.setBounds(19, 172, 94, 16);
		frame.getContentPane().add(lblEnterName);

		JLabel lblEnterAmount = new JLabel("Quantity:");
		lblEnterAmount.setBounds(37, 245, 78, 16);
		frame.getContentPane().add(lblEnterAmount);

		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(135, 288, 100, 40);
		frame.getContentPane().add(btnAddItem);
		
		btnRefund = new JButton("Finish and Refund");
		btnRefund.setBounds(57, 470, 165, 56);
		btnRefund.setBackground(new Color(95,186,125));
		if (this.currentOrder == null || this.currentOrder.getOrderTotal() == 0) {
			btnRefund.setEnabled(false);
		}
		else {
			btnRefund.setEnabled(true);
		}
		frame.getContentPane().add(btnRefund);
		
		JButton btnStartOver = new JButton("Start Over");
		btnStartOver.setBounds(57, 538, 165, 26);
		frame.getContentPane().add(btnStartOver);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(700, 610, 117, 29);
		frame.getContentPane().add(btnGoBack);

		lblTotal = new JLabel("Total: $0.00");
		lblTotal.setBounds(284, 558, 150, 16);
		frame.getContentPane().add(lblTotal);

		String[] columnNames = {"Product","-","Quantity","+","Price","X"};
		String[][] Data = {};

		TableModel modelo = new DefaultTableModel(Data, columnNames)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		table = new JTable(modelo);
		table.getColumnModel().getColumn(1).setMaxWidth(20);
		table.getColumnModel().getColumn(3).setMaxWidth(20);
		table.getColumnModel().getColumn(5).setMaxWidth(20);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		model = (DefaultTableModel) table.getModel();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(284,85,570,450);
		frame.getContentPane().add(scrollPane);


	ActionListener buttonListener = new ActionListener() {

        //we have to define this method in order for an Action Listener to work
        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

            if (e.getSource() == btnGoBack) { //return to main screen

            	this.setVisible(false);
            	MainScreen main = new MainScreen(curEmployee);
            	main.setVisible(true);
            	frame.dispose();
            }

            if (e.getSource() == btnAddItem) {
				scanForItem();
            } 

            if (e.getSource() == btnStartOver) {  //start over button, starts everything over
				currentOrder.reset();
            	currentOrder.clearPurchases();
            	model.setRowCount(0);
				btnRefund.setEnabled(false);
				lblTotal.setText("Total: $0.00");
            }

            if (e.getSource() == btnRefund) {

            	this.setVisible(false);
            	Payment paymnt = new Payment(currentOrder, curEmployee, true);
            	paymnt.setVisible(true);
            }
        }

		private void setVisible(boolean b) {
			frame.setVisible(b);
		}

    };
    
	btnGoBack.addActionListener(buttonListener);
	btnAddItem.addActionListener(buttonListener);
	btnStartOver.addActionListener(buttonListener);
	btnRefund.addActionListener(buttonListener);

	table.addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent e) {
			JTable temp = (JTable)e.getSource();
			if (temp.getSelectedColumn() == 1) {
				decrRow(temp.getSelectedRow());
			}
			if (temp.getSelectedColumn() == 3) {
				incrRow(temp.getSelectedRow());
			}
			if (temp.getSelectedColumn() == 5) {
				removeRow(temp.getSelectedRow());
			}
		}
	});
}

	private void incrRow(int row) {
		currentOrder.decrementPurchaseQuantity(row, 1);

		this.table.setValueAt(-1 * this.currentOrder.getPurchases().get(row).getQuantity(), row, 2);
		this.table.setValueAt("-$" + dec.format(-1 * this.currentOrder.getPurchases().get(row).getPurchaseTotal()), row, 4);

		this.lblTotal.setText("Total: -$" + dec.format(-1 * this.currentOrder.getOrderTotal()));
	}

	private void decrRow(int row) {
		currentOrder.incrementPurchaseQuantity(row, 1);

		this.table.setValueAt(-1 * this.currentOrder.getPurchases().get(row).getQuantity(), row, 2);
		this.table.setValueAt("-$" + dec.format(-1 * this.currentOrder.getPurchases().get(row).getPurchaseTotal()), row, 4);

		if (currentOrder.getPurchases().get(row).getQuantity() == 0) {
			currentOrder.removePurchase(row);
			model.removeRow(row);
		}

		if (currentOrder.getOrderTotal() == 0) {
			btnRefund.setEnabled(false);
			lblTotal.setText("Total: $0.00");
		}
		else {
			this.lblTotal.setText("Total: -$" + dec.format(-1 * this.currentOrder.getOrderTotal()));
		}
	}

	private void removeRow(int row) {
		currentOrder.removePurchase(row);
		model.removeRow(row);

		if (currentOrder.getOrderTotal() == 0) {
			btnRefund.setEnabled(false);
			lblTotal.setText("Total: $0.00");
		}
		else {
			this.lblTotal.setText("Total: -$" + dec.format(-1 * this.currentOrder.getOrderTotal()));
		}
	}

	private void invalidInfo(JTextField lbl) {
		lbl.setForeground(Color.RED);
	}
	private void scanForItem () {
		Purchases purchase;
		if (!this.txtId.getText().isEmpty()) {
			purchase = new Purchases(Integer.parseInt(this.txtId.getText()),-1 * Integer.parseInt(this.txtQuantity.getText()), true);
		}
		else {
			purchase = new Purchases(this.txtname.getText(),-1 * Integer.parseInt(this.txtQuantity.getText()), true);
		}
		if(purchase.isValidProduct()) {
			if (purchase.getQuantity() < 0) {
				Color defColor = new JTextField().getForeground();
				this.addProdToOrder(purchase);
				this.txtId.setText("");
				this.txtname.setText("");
				this.txtQuantity.setText("");
				this.txtId.setForeground(defColor);
				this.txtname.setForeground(defColor);
				this.txtQuantity.setForeground(defColor);
			}
			else {
				invalidInfo(this.txtQuantity);
			}
		}
		else {
			if (this.txtId.getText().isEmpty()) {
				invalidInfo(this.txtname);
			}
			else {
				invalidInfo(this.txtId);
			}
		}
	}
	public void addProdToOrder(Purchases purchase) {
		int temp = this.currentOrder.addNewPurchase(purchase);

		this.lblTotal.setText("Total: -$" + dec.format(-1 * this.currentOrder.getOrderTotal()));

		if (temp < 0) {
			this.model.addRow(new Object[]{purchase.getProductName(), "-", -1 * purchase.getQuantity(), "+", "-$" + dec.format(-1 * purchase.getPurchaseTotal()), "X"});
		}
		else {
			this.table.setValueAt(this.currentOrder.getPurchases().get(temp).getProductName(), temp, 0);
			this.table.setValueAt(-1 * this.currentOrder.getPurchases().get(temp).getQuantity(), temp, 2);
			this.table.setValueAt("-$" + dec.format(-1 * this.currentOrder.getPurchases().get(temp).getPurchaseTotal()), temp, 4);
		}
		if (!this.btnRefund.isEnabled()) {
			this.btnRefund.setEnabled(true);
		}
	}

	public void populateTable() {
		ArrayList<Purchases> purch = this.currentOrder.getPurchases();

		this.lblTotal.setText("Total: -$" + dec.format(-1 * this.currentOrder.getOrderTotal()));

		for (int i = 0; i < purch.size(); i++) {
			this.model.addRow(new Object[]{purch.get(i).getProductName(), "-", -1 * purch.get(i).getQuantity(), "+", "-$" + dec.format(-1 * purch.get(i).getPurchaseTotal()), "X"});
		}
	}

	public void setVisible(boolean b) {
		this.frame.setVisible(b);
	}
}
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTable;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;





public class Inventory {

	private JFrame frame;
	private JTable table;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtProvider;
	private JTextField txtType;
	private JTextField txtInStock;
	private Employee curEmployee;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inventory window = new Inventory();
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
	public Inventory() {
		initialize();
	}

	public Inventory(Employee curEmployee) {
		this.curEmployee = curEmployee;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 739, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(292, 139, 426, 292);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setBounds(100, 187, 130, 26);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(100, 225, 130, 26);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setText("price");
		txtPrice.setBounds(100, 265, 130, 26);
		frame.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		txtProvider = new JTextField();
		txtProvider.setText("Provider");
		txtProvider.setBounds(100, 303, 130, 26);
		frame.getContentPane().add(txtProvider);
		txtProvider.setColumns(10);
		
		txtType = new JTextField();
		txtType.setText("Type");
		txtType.setBounds(100, 340, 130, 26);
		frame.getContentPane().add(txtType);
		txtType.setColumns(10);
		
		txtInStock = new JTextField();
		txtInStock.setText("# In Stock");
		txtInStock.setBounds(100, 378, 130, 26);
		frame.getContentPane().add(txtInStock);
		txtInStock.setColumns(10);
		
		
		JLabel lblProductName = new JLabel("Name:");
		lblProductName.setBounds(47, 230, 82, 16);
		frame.getContentPane().add(lblProductName);
		
		JLabel lblEnterId = new JLabel("Id:");
		lblEnterId.setBounds(68, 192, 61, 16);
		frame.getContentPane().add(lblEnterId);
		
		JLabel lblEnterPrice = new JLabel("Price:");
		lblEnterPrice.setBounds(52, 270, 82, 16);
		frame.getContentPane().add(lblEnterPrice);
		
		JLabel lblEnterProvider = new JLabel("Provider:");
		lblEnterProvider.setBounds(37, 308, 92, 16);
		frame.getContentPane().add(lblEnterProvider);
		
		JLabel lblEnterType = new JLabel("Type:");
		lblEnterType.setBounds(57, 345, 72, 16);
		frame.getContentPane().add(lblEnterType);
		
		JLabel lblEnter = new JLabel("# in Stock:");
		lblEnter.setBounds(24, 383, 92, 16);
		frame.getContentPane().add(lblEnter);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(30, 416, 106, 29);
		frame.getContentPane().add(btnAddItem);

		
		JLabel lblInventory = new JLabel("Inventory");
		lblInventory.setBounds(346, 72, 138, 16);
		frame.getContentPane().add(lblInventory);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(601, 477, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		btnGoBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
//			System.out.println("go back was pushed");
//			this.setVisible(false);
//            	MainScreen main = new MainScreen();
//            	main.setVisible(true);
//            	frame.dispose();	
			System.out.println("employee getting passed to MainSreen" + curEmployee);
			this.setVisible(false);
        	MainScreen main = new MainScreen(curEmployee);
        	main.setVisible(true);
        	frame.dispose();
			}

			public void setVisible(boolean b) {
				// TODO Auto-generated method stub
				//this.frame.setVisible(b);		
			}
			});
		
		
		
		
		JButton btnEditSelectedItem = new JButton("edit selected item");
		btnEditSelectedItem.setBounds(302, 436, 181, 29);
		frame.getContentPane().add(btnEditSelectedItem);
			
		btnEditSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//get row
				int row = table.getSelectedRow();
				TableModel model = table.getModel();
				
				txtId.setText(model.getValueAt(row,0).toString());
				
				txtName.setText(model.getValueAt(row,1).toString());
				txtPrice.setText(model.getValueAt(row,2).toString());
				txtProvider.setText(model.getValueAt(row,3).toString());
				txtType.setText(model.getValueAt(row,4).toString());
				txtInStock.setText(model.getValueAt(row,5).toString());			
			}
			});
		
		JButton updateNewBtn = new JButton("update item");
		updateNewBtn.setBounds(148, 416, 117, 29);
		frame.getContentPane().add(updateNewBtn);
		
		
		updateNewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			String productName = txtName.getText();
				
				//EDIT ITEM IN DATABASE
        		try {
					//String url = "jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false";
					//Connection con = (Connection) DriverManager.getConnection(url, "root", "W01fp@ck");
					Connection myCon = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
					Statement myStmt = (Statement) myCon.createStatement();
        		
        		
        			if(txtPrice.getText().isEmpty()){
        				System.out.println("price not updated");
        			}else{
        				String price = txtPrice.getText();
    	        		double  newProductPrice = Double.parseDouble(price);
        				
        		String updateTableSQL = "UPDATE products SET productprice = ? WHERE productname = ?";
        		java.sql.PreparedStatement preparedStatement = myCon.prepareStatement(updateTableSQL);
           		preparedStatement.setDouble(1, newProductPrice);
        		preparedStatement.setString(2, productName);
        		// execute insert SQL statement		
        		preparedStatement.executeUpdate();
        			}
        		
        			
        		if(txtId.getText().isEmpty()){
        		System.out.println("id not updated");
        		}
        		else{
        			String stringId = txtId.getText();
	        		double id = Double.parseDouble(stringId);
        		String updateTableSQL2 = "UPDATE products SET productid = ? WHERE productname = ?";
        		java.sql.PreparedStatement preparedStatement2 = myCon.prepareStatement(updateTableSQL2);
           		preparedStatement2.setDouble(1, id);
        		preparedStatement2.setString(2, productName);
        		// execute insert SQL statement		
        		preparedStatement2.executeUpdate();
        		}
        		
        		if(txtName.getText().isEmpty()){
        			System.out.println("name not updated");
        		}else{	
	        		String newProductName = txtName.getText();
//        		String updateTableSQL3 = "UPDATE products SET productname = ? WHERE productname = ?";
//        		java.sql.PreparedStatement preparedStatement3 = myCon.prepareStatement(updateTableSQL3);
//           		preparedStatement3.setString(1, newProductName);
//        		preparedStatement3.setString(2, productName);
//        		// execute insert SQL statement		
//        		preparedStatement3.executeUpdate();
	        		System.out.println("productname: " +productName);
	        		System.out.println("new productname: " +newProductName);

	        		DBConnection.dbUpdateRecord("products", "productname =\"" + productName + "\"", "productname = " + newProductName );
        		}
        		
        		if(txtType.getText().isEmpty()){
        			System.out.println("type not updated");
        		}else{
	        	String newProductType = txtType.getText();
        		String updateTableSQL4 = "UPDATE products SET type = ? WHERE productname = ?";
        		java.sql.PreparedStatement preparedStatement4 = myCon.prepareStatement(updateTableSQL4);
           		preparedStatement4.setString(1, newProductType);
        		preparedStatement4.setString(2, productName);
        		// execute insert SQL statement		
        		preparedStatement4.executeUpdate();
        		}
        		
        		if(txtProvider.getText().isEmpty()){
        			System.out.println("provider not updated");
        		}else{
        			String newProductProvider = txtProvider.getText();
        		
        		String updateTableSQL5 = "UPDATE products SET provider = ? WHERE productname = ?";
        		java.sql.PreparedStatement preparedStatement5 = myCon.prepareStatement(updateTableSQL5);
           		preparedStatement5.setString(1, newProductProvider);
        		preparedStatement5.setString(2, productName);
        		// execute insert SQL statement		
        		preparedStatement5.executeUpdate();
        		}
        		}
        		catch (Exception e1){
        		    e1.printStackTrace();
        		}
        				
				
				int row = table.getSelectedRow();			
			
				Inventory myInv = new Inventory();
	         	myInv.setVisible(true);
	         	frame.dispose();
				
			
			
			}
			});
		
		
		//Loads the products immediatlley so you don't have to click load products like before
		try {
			Connection con = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
		String query  = "select * from products";
		
		java.sql.PreparedStatement  pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		//~~~~~~~~~~~~~~~ I commented this code out to test if the db connection stuff would work ~~~~~~~~~~~~~~~~~~~~
		table.setModel(DbUtils.resultSetToTableModel(rs));
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			//do stuff				
		}
	
		catch (Exception e1){
		    System.out.println("NOT WORKING");
			e1.printStackTrace();
		}
		
		
		
		
		JButton btnDelete = new JButton("delete selected item");
		btnDelete.setBounds(492, 436, 181, 29);
		frame.getContentPane().add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			
			TableModel model = table.getModel();
			 txtName.setText(model.getValueAt(row,1).toString());
			 String Name = txtName.getText(); 
			txtName.setText("name");	
			 
			 try {

				 
				 Connection myConn2 = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
         		// 2. Create a statement
         		java.sql.Statement myStmt = myConn2.createStatement();
         		// 3. Execute SQL query
         		String sql = "delete from products where productname = ?";
         		
         		PreparedStatement preparedStmt = (PreparedStatement) myConn2.prepareStatement(sql);
         		
         		preparedStmt.setString(1, Name);
         		preparedStmt.execute();
         		}
         		catch (Exception exc) {
         		exc.printStackTrace();
         		}		 
			 Inventory myInv = new Inventory();
         	myInv.setVisible(true);
         	frame.dispose();
			}
			});
		
		
		
		
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				String stringId = txtId.getText();
        		double id = Double.parseDouble(stringId);
        		System.out.println("id:" + stringId);
            	 
        		String productName = txtName.getText();

        		String price = txtPrice.getText();
        		double  productPrice = Double.parseDouble(price);
        		
        		
        		String productType = txtType.getText();
        		
        		String productProvider = txtProvider.getText();
				
				String stringStock = txtInStock.getText();
				double stock = Double.parseDouble(stringStock);
        		
        		
        		try {
					//String url = "jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false";
					//Connection con = (Connection) DriverManager.getConnection(url, "root", "W01fp@ck");
					Connection myCon = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
        		Statement myStmt = (Statement) myCon.createStatement();
        		       		
        		
        		  String sql = "INSERT into products"
        		            + "(productid,productname,productprice,provider,type,inStock) VALUES"
        		            + "(?,?,?,?,?,?)";
        		    java.sql.PreparedStatement ps = myCon.prepareStatement(sql);
        		    ps.setDouble(1, id);
        		    ps.setString(2, productName);
        		    ps.setDouble(3, productPrice);
        		    ps.setString(4, productProvider);
        		    ps.setString(5, productType);
        		    ps.setDouble(6, stock);
        		    ps.executeUpdate();
       		
        			txtId.setText("id");
        			txtName.setText("name");
        			txtPrice.setText("price");
        			txtProvider.setText("Provider");
        			txtType.setText("Type");
        			txtInStock.setText("# In Stock");


        		}
        		catch (Exception e1){
        		    e1.printStackTrace();
        		}
        		Inventory myInv = new Inventory();
            	myInv.setVisible(true);
            	frame.dispose();
			}
			
		});
	
			}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		this.frame.setVisible(b);

	}
}

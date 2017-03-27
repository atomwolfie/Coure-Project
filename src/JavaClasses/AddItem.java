import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.internal.ws.api.server.Container;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class AddItem {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtType;
	private JTextField txtProvider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddItem window = new AddItem();
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
	public AddItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		 frame.setBounds(400, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewItem = new JLabel("New Item");
		lblNewItem.setBounds(174, 6, 97, 16);
		frame.getContentPane().add(lblNewItem);
		
		JLabel lblItemId = new JLabel("Item id:");
		lblItemId.setBounds(70, 58, 61, 16);
		frame.getContentPane().add(lblItemId);
		
		JLabel lblItemName = new JLabel("Item name:");
		lblItemName.setBounds(45, 99, 84, 16);
		frame.getContentPane().add(lblItemName);
		
		JLabel lblItemPrice = new JLabel("Item price:");
		lblItemPrice.setBounds(49, 142, 80, 16);
		frame.getContentPane().add(lblItemPrice);
		
		JLabel lblItemType = new JLabel("Item type:");
		lblItemType.setBounds(51, 185, 84, 16);
		frame.getContentPane().add(lblItemType);
		
		JLabel lblProvider = new JLabel("Provider:");
		lblProvider.setBounds(59, 224, 80, 16);
		frame.getContentPane().add(lblProvider);
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setBounds(128, 54, 130, 26);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		

		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(128, 94, 130, 26);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
//		String productName = txtName.getText();
//		System.out.println("product name:" + productName);

		
		txtPrice = new JTextField();
		txtPrice.setText("price");
		txtPrice.setBounds(128, 137, 130, 26);
		frame.getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
				
//		String price = txtPrice.getText();
//		double  productPrice = Double.parseDouble(price);
//		System.out.println("product price:" + productPrice);

		
		
		txtType = new JTextField();
		txtType.setText("type");
		txtType.setBounds(128, 180, 130, 26);
		frame.getContentPane().add(txtType);
		txtType.setColumns(10);
		
		
//		String productType = txtType.getText();
//		System.out.println("product type:" + productType);

		
		txtProvider = new JTextField();
		txtProvider.setText("Provider");
		txtProvider.setBounds(128, 219, 130, 26);
		frame.getContentPane().add(txtProvider);
		txtProvider.setColumns(10);
		
//		String productProvider = txtType.getText();
//		System.out.println("product provider:" + productProvider);

		
		
		
		JButton btnSaveItem = new JButton("Save Item");
		btnSaveItem.setBounds(304, 135, 117, 68);
		btnSaveItem.setBackground(new Color(95,186,125));
		frame.getContentPane().add(btnSaveItem);

		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(304, 215, 117, 29);
		frame.getContentPane().add(btnGoBack);
	
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

				if (e.getSource() == btnGoBack) {

					this.setVisible(false);
					Inventory invent = new Inventory();
					invent.setVisible(true);

				}
	            if (e.getSource() == btnSaveItem) { //check to see if the source is the checkout

	            	String stringId = txtId.getText();
	        		double id = Double.parseDouble(stringId);
	        		System.out.println("id:" + stringId);
	            	 
	        		String productName = txtName.getText();
	        		System.out.println("product name:" + productName);

	        		String price = txtPrice.getText();
	        		double  productPrice = Double.parseDouble(price);
	        		System.out.println("product price:" + productPrice);
	        		
	        		
	        		String productType = txtType.getText();
	        		System.out.println("product type:" + productType);
	        		
	        		String productProvider = txtProvider.getText();
	        		System.out.println("product provider:" + productProvider);

	        		DBConnection.dbInsertInto("products (productid,productname,productprice,provider,type)", id
							+ ",\"" + productName + "\"," + productPrice + ",\"" + productProvider + "\",\"" + productType + "\"");

	        		//try catch here
	        		
	        		/*try {
	        		    String url = "jdbc:mysql://localhost:3306/store?autoReconnect=true&useSSL=false";
	        		    Connection myCon = (Connection) DriverManager.getConnection(url, "storeuser", "*fad!@plo*");
	        		Statement myStmt = (Statement) myCon.createStatement();
	        		
	        		
	        		
	        		
	        		
	        		
	        		  String sql = "INSERT into products"
	        		            + "(productid,productname,productprice,provider,type) VALUES"
	        		            + "(?,?,?,?,?)";
	        		    PreparedStatement ps = myCon.prepareStatement(sql);
	        		    ps.setDouble(1, id);
	        		    ps.setString(2, productName);
	        		    ps.setDouble(3, productPrice);
	        		    ps.setString(4, productProvider);
	        		    ps.setString(5, productType);
	        		    ps.executeUpdate();
	        		
	  

	        		
	        		}
	        		catch (Exception e1){
	        		    e1.printStackTrace();
	        		}*/
	        		
	        		this.setVisible(true);
	        		Inventory inv = new Inventory();
	                inv.setVisible(true);
	                frame.dispose();
	            } 
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };

		btnGoBack.addActionListener(buttonListener);
	    btnSaveItem.addActionListener(buttonListener);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);

	}
}
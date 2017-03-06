import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JButton;

public class DeleteItem {

	private JFrame frame;
	private JTextField txtName;
	private String prodName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteItem window = new DeleteItem();
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
	public DeleteItem() {
		this.prodName = "name";
		initialize();
	}


	/**
	 * Create the application.
	 */
	public DeleteItem(String prodName) {
		this.prodName = prodName;
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
		
		JLabel lblDeleteItem = new JLabel("Delete Item");
		lblDeleteItem.setBounds(189, 6, 98, 16);
		frame.getContentPane().add(lblDeleteItem);
		
		JLabel lblEnterNameOf = new JLabel("Enter name of Item: ");
		lblEnterNameOf.setBounds(64, 72, 153, 16);
		frame.getContentPane().add(lblEnterNameOf);
		
		txtName = new JTextField();
		txtName.setText(prodName);
		txtName.setBounds(204, 67, 130, 26);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JButton btnDeleteItem = new JButton("Delete Item");
		btnDeleteItem.setBounds(179, 147, 117, 62);
		btnDeleteItem.setBackground(new Color(208,48,44));
		frame.getContentPane().add(btnDeleteItem);

		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(179, 223, 117, 29);
		frame.getContentPane().add(btnGoBack);
	

		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnDeleteItem) { 

	            	String productName = txtName.getText();
	            	String url = "jdbc:mysql://localhost:3306/store?autoReconnect=true&useSSL=false";
	            	//try catch here
	            	try {
	            		// 1. Get a connection to database
	            		Connection myConn = DriverManager.getConnection(url, "storeuser", "*fad!@plo*");
	            		// 2. Create a statement
	            		Statement myStmt = myConn.createStatement();
	            		// 3. Execute SQL query
	            		String sql = "delete from products where productname = ?";
	            		
	            		PreparedStatement preparedStmt = (PreparedStatement) myConn.prepareStatement(sql);
	            		
	            		preparedStmt.setString(1, productName);
	            		preparedStmt.execute();
	            		}
	            		catch (Exception exc) {
	            		exc.printStackTrace();
	            		}
	            	
	            	
	            	this.setVisible(false);                	
	            	Inventory invent = new Inventory();
	            	invent.setVisible(true);
	          
	            } 
	            if (e.getSource() == btnGoBack) { 

	            	this.setVisible(false);                	
	            	Inventory invent = new Inventory();
	            	invent.setVisible(true);
	          
	            }
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };
	    
		
		btnDeleteItem.addActionListener(buttonListener);
		btnGoBack.addActionListener(buttonListener);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}

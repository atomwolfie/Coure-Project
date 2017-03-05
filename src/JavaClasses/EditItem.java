import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class EditItem {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtNewId;
	private JTextField txtNewName;
	private JTextField txtNewPrice;
	private JTextField txtNewType;
	private JTextField txtNewProvider;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditItem window = new EditItem();
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
	public EditItem() {
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
		
		JLabel lblEditItem = new JLabel("Edit Item");
		lblEditItem.setBounds(172, 6, 61, 16);
		frame.getContentPane().add(lblEditItem);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(304, 228, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		JButton btnEditItem = new JButton("Edit Item");
		btnEditItem.setBounds(304, 148, 117, 68);
		frame.getContentPane().add(btnEditItem);
		
		JLabel lblNameOfItem = new JLabel("Name of item to edit:");
		lblNameOfItem.setBounds(6, 56, 146, 16);
		frame.getContentPane().add(lblNameOfItem);
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(148, 51, 130, 26);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblEditId = new JLabel("edit id:");
		lblEditId.setBounds(75, 104, 61, 16);
		frame.getContentPane().add(lblEditId);
		
		txtNewId = new JTextField();
		txtNewId.setText("");
		txtNewId.setBounds(148, 99, 130, 26);
		frame.getContentPane().add(txtNewId);
		txtNewId.setColumns(10);
		
		txtNewName = new JTextField();
		txtNewName.setText("");
		txtNewName.setBounds(148, 133, 130, 26);
		frame.getContentPane().add(txtNewName);
		txtNewName.setColumns(10);
		
		JLabel lblEditName = new JLabel("edit name:");
		lblEditName.setBounds(54, 138, 75, 16);
		frame.getContentPane().add(lblEditName);
		
		txtNewPrice = new JTextField();
		txtNewPrice.setText("");
		txtNewPrice.setBounds(148, 168, 130, 26);
		frame.getContentPane().add(txtNewPrice);
		txtNewPrice.setColumns(10);
		
		JLabel lblEditPrice = new JLabel("edit price:");
		lblEditPrice.setBounds(54, 173, 71, 16);
		frame.getContentPane().add(lblEditPrice);
		
		txtNewType = new JTextField();
		txtNewType.setText("");
		txtNewType.setBounds(148, 206, 130, 26);
		frame.getContentPane().add(txtNewType);
		txtNewType.setColumns(10);
		
		JLabel lblEditType = new JLabel("edit type:");
		lblEditType.setBounds(54, 211, 61, 16);
		frame.getContentPane().add(lblEditType);
		
		txtNewProvider = new JTextField();
		txtNewProvider.setText("");
		txtNewProvider.setBounds(148, 244, 130, 26);
		frame.getContentPane().add(txtNewProvider);
		txtNewProvider.setColumns(10);
		
		JLabel lblEditProvider = new JLabel("edit provider:");
		lblEditProvider.setBounds(35, 249, 94, 16);
		frame.getContentPane().add(lblEditProvider);
				
		txtNewId.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    changed();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    changed();
			  }

			  public void changed() {
			     if (txtNewId.getText().equals("")){
			    	 txtNewId.setEnabled(false);
			     }
			     else {
			    	 txtNewId.setEnabled(true);
			    }
			  }
			});
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	        	if (e.getSource() == btnGoBack) { 

	            	this.setVisible(false);                	
	            	Inventory invent = new Inventory();
	            	invent.setVisible(true);
	          
	            }
	        	if (e.getSource() == btnEditItem) { 
	        			        		
	        		String productName = txtName.getText();

	        		
	        		//EDIT ITEM IN DATABASE
	        		try {
	        		    String url = "jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false";
	        		    Connection myCon = (Connection) DriverManager.getConnection(url, "root", "W01fp@ck");
	        		Statement myStmt = (Statement) myCon.createStatement();
	        		
	        		
	        			if(txtNewPrice.getText().isEmpty()){
	        				System.out.println("price not updated");
	        			}else{
	        				String price = txtNewPrice.getText();
	    	        		double  newProductPrice = Double.parseDouble(price);
	        				
	        				String updateTableSQL = "UPDATE products SET productprice = ? WHERE productname = ?";
	        		PreparedStatement preparedStatement = myCon.prepareStatement(updateTableSQL);
	           		preparedStatement.setDouble(1, newProductPrice);
	        		preparedStatement.setString(2, productName);
	        		// execute insert SQL statement		
	        		preparedStatement.executeUpdate();
	        			}
	        		
	        			
	        		if(txtNewId.getText().isEmpty()){
	        		System.out.println("id not updated");
	        		}
	        		else{
	        			String stringId = txtNewId.getText();
		        		double id = Double.parseDouble(stringId);
	        		String updateTableSQL2 = "UPDATE products SET productid = ? WHERE productname = ?";
	        		PreparedStatement preparedStatement2 = myCon.prepareStatement(updateTableSQL2);
	           		preparedStatement2.setDouble(1, id);
	        		preparedStatement2.setString(2, productName);
	        		// execute insert SQL statement		
	        		preparedStatement2.executeUpdate();
	        		}
	        		
	        		if(txtNewName.getText().isEmpty()){
	        			System.out.println("name not updated");
	        		}else{	
		        		String newProductName = txtNewName.getText();
	        		String updateTableSQL3 = "UPDATE products SET productname = ? WHERE productname = ?";
	        		PreparedStatement preparedStatement3 = myCon.prepareStatement(updateTableSQL3);
	           		preparedStatement3.setString(1, newProductName);
	        		preparedStatement3.setString(2, productName);
	        		// execute insert SQL statement		
	        		preparedStatement3.executeUpdate();
	        		}
	        		
	        		if(txtNewType.getText().isEmpty()){
	        			System.out.println("type not updated");
	        		}else{
		        	String newProductType = txtNewType.getText();
	        		String updateTableSQL4 = "UPDATE products SET type = ? WHERE productname = ?";
	        		PreparedStatement preparedStatement4 = myCon.prepareStatement(updateTableSQL4);
	           		preparedStatement4.setString(1, newProductType);
	        		preparedStatement4.setString(2, productName);
	        		// execute insert SQL statement		
	        		preparedStatement4.executeUpdate();
	        		}
	        		
	        		if(txtNewProvider.getText().isEmpty()){
	        			System.out.println("provider not updated");
	        		}else{
	        			String newProductProvider = txtNewProvider.getText();
	        		
	        		String updateTableSQL5 = "UPDATE products SET provider = ? WHERE productname = ?";
	        		PreparedStatement preparedStatement5 = myCon.prepareStatement(updateTableSQL5);
	           		preparedStatement5.setString(1, newProductProvider);
	        		preparedStatement5.setString(2, productName);
	        		// execute insert SQL statement		
	        		preparedStatement5.executeUpdate();
	        		}
	        		}
	        		catch (Exception e1){
	        		    e1.printStackTrace();
	        		}
	        		
	        		
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
	    btnGoBack.addActionListener(buttonListener);
	    btnEditItem.addActionListener(buttonListener);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}

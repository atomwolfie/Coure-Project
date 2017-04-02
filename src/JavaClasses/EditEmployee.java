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





public class EditEmployee {

	private JFrame frame;
	private JTable table;
	private JTextField txtId;
	//private JTextField txtName;
	private JTextField txtLastName;
	private JTextField txtStatus;
	private JTextField txtPic;
	private JTextField txtPass;
	//private JTextField txtFirstName;
	private JTextField txtFirstName_1;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditEmployee window = new EditEmployee();
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
	public EditEmployee() {
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
		
		txtFirstName_1 = new JTextField();
		txtFirstName_1.setText("first name");
		txtFirstName_1.setBounds(100, 227, 130, 26);
		frame.getContentPane().add(txtFirstName_1);
		txtFirstName_1.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setText("last name");
		txtLastName.setBounds(100, 265, 130, 26);
		frame.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		
		txtStatus = new JTextField();
		txtStatus.setText("cashier or manager");
		txtStatus.setBounds(100, 303, 130, 26);
		frame.getContentPane().add(txtStatus);
		txtStatus.setColumns(10);
		
		txtPic = new JTextField();
		txtPic.setText("filepath");
		txtPic.setBounds(100, 340, 130, 26);
		frame.getContentPane().add(txtPic);
		txtPic.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setText("password");
		txtPass.setBounds(100, 378, 130, 26);
		frame.getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		
		JLabel employeeFirst = new JLabel("first name:");
		employeeFirst.setBounds(24, 232, 82, 16);
		frame.getContentPane().add(employeeFirst);
		
		JLabel lblEnterId = new JLabel("Id:");
		lblEnterId.setBounds(68, 192, 61, 16);
		frame.getContentPane().add(lblEnterId);
		
		JLabel employeeLast = new JLabel("last name:");
		employeeLast.setBounds(24, 270, 82, 16);
		frame.getContentPane().add(employeeLast);
		
		JLabel employeeStat = new JLabel("status:");
		employeeStat.setBounds(44, 308, 92, 16);
		frame.getContentPane().add(employeeStat);
		
		JLabel employeePic = new JLabel("photo:");
		employeePic.setBounds(57, 345, 72, 16);
		frame.getContentPane().add(employeePic);
		
		JLabel employeePass = new JLabel("password:");
		employeePass.setBounds(24, 383, 92, 16);
		frame.getContentPane().add(employeePass);
		
		JButton btnAddUser = new JButton("Add user");
		btnAddUser.setBounds(30, 416, 106, 29);
		frame.getContentPane().add(btnAddUser);

		
		JLabel lblInventory = new JLabel("Edit Employees");
		lblInventory.setBounds(346, 72, 138, 16);
		frame.getContentPane().add(lblInventory);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(601, 477, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		
		
		//TODO this needs to work normally
		btnGoBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			try{
				
	    		ResultSet myRs = DBConnection.dbSelectAllFromTableWhere("employees", "employeefirstname=\"" + txtFirstName_1.getText() + "\"");
	    		
	    		myRs.next();
	    		
				Employee employee = new Employee(myRs.getInt("employeeid")); 
				
				this.setVisible(false);
				MainScreen main = new MainScreen(employee);
				main.setVisible(true);
				frame.dispose();
			}
			catch(Exception e1){
				e1.printStackTrace();
			}
						//if correct go to main screen
			
		
			

			}

		private void setVisible(boolean b) {
			// TODO Auto-generated method stub
			frame.setVisible(b);
		}
				
			

		
			});
		
		

		
		JButton btnEditSelectedItem = new JButton("edit selected user");
		btnEditSelectedItem.setBounds(302, 436, 181, 29);
		frame.getContentPane().add(btnEditSelectedItem);
			
		btnEditSelectedItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//get row
				int row = table.getSelectedRow();
				TableModel model = table.getModel();
				
				txtId.setText(model.getValueAt(row,0).toString());
				
				txtFirstName_1.setText(model.getValueAt(row,1).toString());
				txtLastName.setText(model.getValueAt(row,2).toString());
				txtStatus.setText(model.getValueAt(row,3).toString());
				txtPic.setText(model.getValueAt(row,4).toString());
				txtPass.setText(model.getValueAt(row,5).toString());			
			}
			});
		
		JButton updateNewBtn = new JButton("update user");
		updateNewBtn.setBounds(148, 416, 117, 29);
		frame.getContentPane().add(updateNewBtn);
		
		
		
		updateNewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			String empFirstName = txtFirstName_1.getText();
				
			String stringId = txtId.getText();
    		Integer id = Integer.parseInt(stringId);
			
				//EDIT ITEM IN DATABASE
        		try {
					
					Connection myCon = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
					Statement myStmt = (Statement) myCon.createStatement();
        		
        		
					//update last name
        			if(txtLastName.getText().isEmpty()){
        				System.out.println("last name not updated");
        			}
        			else{
        				DBConnection.dbUpdateRecord("employees", "employeelastname =\"" + txtLastName.getText() + "\"", "employeeid = " + id );
        			}
        		
        			//update first name       			
        			if(txtFirstName_1.getText().isEmpty()){
        				System.out.println("first name not updated");
        			}
        			else{
        				DBConnection.dbUpdateRecord("employees", "employeefirstname =\"" + empFirstName + "\"", "employeeid = " + id );
        			}

        			//update status
        			if(txtStatus.getText().isEmpty()){
        				System.out.println("status not updated");
        			}
        			else{
        				DBConnection.dbUpdateRecord("employees", "employeestatus =\"" + txtStatus.getText() + "\"", "employeeid = " + id );
        			}
        			      			
        			
        			//update pic file path
        			if(txtPic.getText().isEmpty()){
        				System.out.println("status not updated");
        			}
        			else{
        				DBConnection.dbUpdateRecord("employees", "employeepicfilepath =\"" + txtPic.getText() + "\"", "employeeid = " + id );
        			}
        			
        			//update password
        			if(txtPass.getText().isEmpty()){
        				System.out.println("status not updated");
        			}
        			else{
        				DBConnection.dbUpdateRecord("employees", "employeepassword =\"" + txtPass.getText() + "\"", "employeeid = " + id );
        			}
        					
        			
        		}
        		catch (Exception e1){
        		    e1.printStackTrace();
        		}
        				
				
				int row = table.getSelectedRow();			
			
				try {
					Connection con = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
				String query  = "select * from employees";
				
				java.sql.PreparedStatement  pst = con.prepareStatement(query);
				ResultSet rs = pst.executeQuery();

				table.setModel(DbUtils.resultSetToTableModel(rs));
					//do stuff				
				}
				catch (Exception e1){
				    System.out.println("NOT WORKING");
					e1.printStackTrace();
				}
			
			
			}
			});
		
		
		//Loads the products immediately so you don't have to click load products like before
		try {
			Connection con = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
		String query  = "select * from employees";
		
		java.sql.PreparedStatement  pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		table.setModel(DbUtils.resultSetToTableModel(rs));
			//do stuff				
		}
	
		catch (Exception e1){
		    System.out.println("NOT WORKING");
			e1.printStackTrace();
		}
		
		
		
		
		JButton btnDelete = new JButton("delete selected user");
		btnDelete.setBounds(492, 436, 181, 29);
		frame.getContentPane().add(btnDelete);
		

				
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int row = table.getSelectedRow();
			
			TableModel model = table.getModel();
			txtFirstName_1.setText(model.getValueAt(row,1).toString());
			 String Name = txtFirstName_1.getText(); 
			 txtFirstName_1.setText("name");	
			 
			 try {

				 
				 Connection myConn2 = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
         		// 2. Create a statement
         		java.sql.Statement myStmt = myConn2.createStatement();
         		// 3. Execute SQL query
         		String sql = "delete from employees where employeefirstname = ?";
         		
         		PreparedStatement preparedStmt = (PreparedStatement) myConn2.prepareStatement(sql);
         		
         		preparedStmt.setString(1, Name);
         		preparedStmt.execute();
         		}
         		catch (Exception exc) {
         		exc.printStackTrace();
         		}		 
			 EditEmployee myEdit = new EditEmployee();
         	myEdit.setVisible(true);
         	frame.dispose();
			}
			});
		
		
		
		
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				String stringId = txtId.getText();
        		double id = Double.parseDouble(stringId);
        		System.out.println("id:" + stringId);
            	 
        		String employeeFirstName = txtFirstName_1.getText();

        		String employeeLastName = txtLastName.getText();
        		
        		
        		String employeStatus = txtPic.getText();
        		
        		String employeePicFilePath = txtStatus.getText();
				
				String employeePassword = txtPass.getText();
        		
        		
        		try {
					
					Connection myCon = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
        		Statement myStmt = (Statement) myCon.createStatement();
        		       		
        		
        		  String sql = "INSERT into employees"
        		            + "(employeeid,employeefirstname,employeelastname,employeestatus,employeepicfilepath,employeepassword) VALUES"
        		            + "(?,?,?,?,?,?)";
        		    java.sql.PreparedStatement ps = myCon.prepareStatement(sql);
        		    ps.setDouble(1, id);
        		    ps.setString(2, employeeFirstName);
        		    ps.setString(3, employeeLastName);
        		    ps.setString(4, employeStatus);
        		    ps.setString(5, employeePicFilePath);
        		    ps.setString(6, employeePassword);
        		    ps.executeUpdate();
       		
        			txtId.setText("id");
        			txtFirstName_1.setText("first name");
        			txtLastName.setText("last name");
        			txtStatus.setText("cashier/manager");
        			txtPic.setText("file path");
        			txtPass.setText("password");

        		}
        		catch (Exception e1){
        		    e1.printStackTrace();
        		}
   			 EditEmployee myEdit = new EditEmployee();
   			myEdit.setVisible(true);
            	frame.dispose();
			}
			
		});
	
			}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		this.frame.setVisible(b);

	}
}
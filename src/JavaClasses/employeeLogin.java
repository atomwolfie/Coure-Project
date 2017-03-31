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
import java.util.Collections;
import java.util.Vector;

import javax.swing.table.*;





public class employeeLogin {

	private JFrame frame;
	private JTable table;
	private JTextField txtName;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employeeLogin window = new employeeLogin();
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
	public employeeLogin() {
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
		
//		
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(100, 225, 130, 26);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		
		JLabel lblProductName = new JLabel("Name:");
		lblProductName.setBounds(47, 230, 82, 16);
		frame.getContentPane().add(lblProductName);

		
		JLabel lblInventory = new JLabel("Employee Login");
		lblInventory.setBounds(346, 72, 138, 16);
		frame.getContentPane().add(lblInventory);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(601, 477, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		textField = new JTextField();
		textField.setText("********");
		textField.setBounds(100, 273, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(27, 278, 72, 16);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("login");
		btnLogin.setBounds(138, 311, 117, 29);
		frame.getContentPane().add(btnLogin);
		
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			String passEntered = textField.getText();
			System.out.println("pass entered: " + passEntered);
			//compare passwords
			String realPass;
			String employeename = txtName.getText();
			System.out.println("employee name: " + employeename);
			Employee employee = null;
		    //get real password
			
			try{
					
	    		ResultSet myRs = DBConnection.dbSelectAllFromTableWhere("employees", "employeefirstname=\"" + employeename + "\"");
	    		
	    		myRs.next();
	    		
	     		realPass = myRs.getString("employeepassword"); 
				 employee = new Employee(myRs.getInt("employeeid")); 

			}
			catch(Exception e1){
				e1.printStackTrace();
				realPass = "nothing";
			}
						//if correct go to main screen
			
			if(passEntered.equals(realPass)){
		
				this.setVisible(false);
				MainScreen main = new MainScreen(employee);
				main.setVisible(true);
				frame.dispose();

			}
			else{
				System.out.println("password not correct");
			}
			
			//if not little window saying incorrect password
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
			});
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 149, 253, 252);
		frame.getContentPane().add(scrollPane);
		

					
		//get employees into vector
		Vector<String> employeeNames = new Vector<String>(10,2);
		
		try{
			 Connection myConn2 = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
      		java.sql.Statement myStmt = myConn2.createStatement();
     		String sql = "select * from employees";
     		
     		ResultSet myRs = myStmt.executeQuery("select * from employees");
     		
     		while(myRs.next()){
     		employeeNames.add(myRs.getString("employeefirstname"));
     		}
     		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		//sort employees
		Collections.sort(employeeNames);
		
		//put them into the jlist
		JList list = new JList(employeeNames);
		scrollPane.setViewportView(list);
		
		JButton btnSelectEmployee = new JButton("select employee");
		btnSelectEmployee.setBounds(343, 411, 141, 29);
		frame.getContentPane().add(btnSelectEmployee);
		
		btnSelectEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtName.setText((String) list.getSelectedValue());				
			}
		});
		
		
	
		
		btnGoBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			this.setVisible(false);
            	LoginScreen myLogin = new LoginScreen();
            	myLogin.setVisible(true);
            	frame.dispose();		
			}

			public void setVisible(boolean b) {
				// TODO Auto-generated method stub
				//this.frame.setVisible(b);		
			}
			});
	
			}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		this.frame.setVisible(b);

	}
}

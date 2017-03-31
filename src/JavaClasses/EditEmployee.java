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
	private JTextField txtName;
	private JTextField textField;

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
		
		txtName = new JTextField();
		txtName.setText("name");
		txtName.setBounds(100, 225, 130, 26);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		
		JLabel lblProductName = new JLabel("Name:");
		lblProductName.setBounds(47, 230, 82, 16);
		frame.getContentPane().add(lblProductName);

		
		JLabel lblInventory = new JLabel("Manage Employees");
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
		
		JButton btnSelectUser = new JButton("select user");
		btnSelectUser.setBounds(288, 433, 117, 29);
		frame.getContentPane().add(btnSelectUser);
			
		try {
			Connection con = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
		String query  = "select * from employees";
		
		java.sql.PreparedStatement  pst = con.prepareStatement(query);
		ResultSet rs = pst.executeQuery();

		table.setModel(DbUtils.resultSetToTableModel(rs));
		}
	
		catch (Exception e1){
		    System.out.println("NOT WORKING");
			e1.printStackTrace();
		}
		
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
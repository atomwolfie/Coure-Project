import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.*;
import java.util.Collections;
import java.util.Vector;



public class RewardPoints{

	private JFrame frame;
	private JTextField txtName;
	private JTextField textField;
	private Employee curEmployee;
	private Customer curCustomerGuy;
	private Order currentOrder;
	private boolean isReturn;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RewardPoints window = new RewardPoints(new Order());
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
	
	
	public RewardPoints(Order curOrder) {
		this.isReturn = false;
		initialize(curOrder);
	}
	
	public RewardPoints(Order curOrder, Employee curEmployee, boolean isReturn) {
		this.curEmployee = curEmployee;
		this.isReturn = isReturn;
		this.currentOrder = curOrder;
		initialize(curOrder);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Order curOrder) {
		this.currentOrder = curOrder;

		System.out.println("Order total: " + this.currentOrder.getOrderTotal());
		
		frame = new JFrame();
		frame.setBounds(480, 177, 739, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(601, 477, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		textField = new JPasswordField();
		textField.setText("");
		textField.setBounds(138, 219, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Search name:");
		lblPassword.setBounds(51, 224, 92, 16);
		frame.getContentPane().add(lblPassword);
		
		JButton btnLogin = new JButton("enter");
		btnLogin.setBounds(151, 257, 117, 29);
		frame.getContentPane().add(btnLogin);
		
		
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(346, 149, 253, 252);
		frame.getContentPane().add(scrollPane);
					
		//get employees into vector
		Vector<String> custNames = new Vector<String>(10,2);
		
		try{
			 Connection myConn2 = (Connection) DriverManager.getConnection(DBConnection.dbUrl, DBConnection.dbUser, DBConnection.dbPassword);
      		java.sql.Statement myStmt = myConn2.createStatement();
     		
     		ResultSet myRs = myStmt.executeQuery("select * from customers");
     		
     		while(myRs.next()){
     			custNames.add(myRs.getString("customername"));
     		}
     		
		}
		catch(Exception e){
			e.printStackTrace();
		}

		//sort customers
		Collections.sort(custNames);
		
		//put them into the jlist
		JList list = new JList(custNames);
		scrollPane.setViewportView(list);
		
		JButton btnSelectEmployee = new JButton("Select Customer");
		btnSelectEmployee.setBounds(343, 411, 151, 29);
		frame.getContentPane().add(btnSelectEmployee);
		
		btnSelectEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				textField.setText((String) list.getSelectedValue());		
				}
			});
		
		JLabel lblSelectCustomer = new JLabel("Select Customer");
		lblSelectCustomer.setBounds(265, 74, 130, 16);
		frame.getContentPane().add(lblSelectCustomer);
		
		
		JButton btnContinue = new JButton("continue");
		btnContinue.setBounds(481, 477, 117, 29);
		frame.getContentPane().add(btnContinue);
		
		
		
		
				btnContinue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							
						textField.setText((String) list.getSelectedValue());		
						String name = textField.getText();
						System.out.println(name);
						
						try{

							ResultSet myRs = DBConnection.dbSelectAllFromTableWhere("customers", "customername=\"" + name + "\"");

							myRs.next();

							Customer curCustomer = new Customer(myRs.getString(1));
							curCustomerGuy = curCustomer;
						}
						catch(Exception e1){
							e1.printStackTrace();
						}
						
						
						this.setVisible(false);
						RewardPointsPartTwo reward = new RewardPointsPartTwo(currentOrder, curEmployee, curCustomerGuy,isReturn);
						reward.setVisible(true);
			            	frame.dispose();		
						}

						public void setVisible(boolean b) {
							frame.setVisible(b);
						}
					});
		
		

		btnGoBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			this.setVisible(false);
			Payment payment = new Payment(currentOrder, curEmployee, isReturn);
			payment.setVisible(true);
            	frame.dispose();		
			}

			public void setVisible(boolean b) {
				frame.setVisible(b);
			}
		});
	
	}

	public void setVisible(boolean b) {
		this.frame.setVisible(b);

	}
}

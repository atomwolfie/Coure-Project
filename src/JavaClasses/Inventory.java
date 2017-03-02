import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.internal.ws.api.server.Container;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;

public class Inventory {

	private JFrame frame;
	private JTextField txtSearch;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Inventory");
		lblNewLabel.setBounds(175, 15, 117, 21);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnSaveChanges = new JButton("add item");
		btnSaveChanges.setBounds(220, 110, 156, 29);
		frame.getContentPane().add(btnSaveChanges);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(305, 243, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		
		
		JButton btnEdit = new JButton("edit");
		btnEdit.setBounds(220, 80, 117, 29);
		frame.getContentPane().add(btnEdit);
		
		ArrayList <String> items = new ArrayList();
		String[] str = new String[items.size()];
		
		
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} 
		try {
		    String url = "jdbc:mysql://localhost:3306/demo";
		    Connection con = DriverManager.getConnection(url, "root", "W01fp@ck");		
		Statement myStmt = con.createStatement();
		
		ResultSet myRs = myStmt.executeQuery("select * from products");
				
		while(myRs.next()){
			
			//System.out.println(myRs.getString("productname"));
			items.add(myRs.getString("productname"));
			
		}
		
		}
		catch (Exception e){
		    e.printStackTrace();
		}
		

		
		JList list = new JList(items.toArray(str));
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
	
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectedIndex(1);
		list.setBounds(21, 70, 182, 163);
		frame.getContentPane().add(list);
		//java.awt.Container contentPane = frame.getContentPane();
	    //contentPane.add(scrollPane, BorderLayout.CENTER);

        
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnSaveChanges) { //saves changes to database and closes window and returns to main screen

	            	this.setVisible(false);
	               // check.setVisible(true);
	            	MainScreen main = new MainScreen();
	            	main.setVisible(true);
	            } 
	            if (e.getSource() == btnGoBack) { //return to main screen

	            	this.setVisible(false);
	            	MainScreen main = new MainScreen();
	            	main.setVisible(true);
	            } 
	        }

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
	    };
	    
		btnSaveChanges.addActionListener(buttonListener);
		btnGoBack.addActionListener(buttonListener);

	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}

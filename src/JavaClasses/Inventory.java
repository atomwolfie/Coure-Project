import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JMenuBar;

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
		
		JButton btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setBounds(258, 211, 156, 29);
		frame.getContentPane().add(btnSaveChanges);
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(305, 243, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		txtSearch = new JTextField();
		txtSearch.setText("Search");
		txtSearch.setBounds(294, 12, 130, 26);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setBounds(304, 38, 117, 29);
		frame.getContentPane().add(btnEnter);
		
		JList list = new JList();
		list.setBounds(21, 70, 182, 163);
		frame.getContentPane().add(list);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(21, 14, 132, 22);
		frame.getContentPane().add(menuBar);
	
	
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

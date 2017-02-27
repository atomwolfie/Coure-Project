import java.awt.EventQueue;

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
	}
}

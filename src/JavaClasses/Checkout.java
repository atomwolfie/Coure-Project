import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;

public class Checkout {

	private JFrame frame;
	private JTable table;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout window = new Checkout();
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
	public Checkout() {
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
		
		JLabel lblNewLabel = new JLabel("Checkout");
		lblNewLabel.setBounds(175, 6, 141, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setBounds(17, 28, 117, 56);
		frame.getContentPane().add(btnAddItem);
		
		JButton btnNewButton = new JButton("Remove Item");
		btnNewButton.setBounds(17, 85, 117, 56);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Finish and Pay");
		button.setBounds(17, 199, 117, 56);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Start Over");
		button_1.setBounds(17, 141, 117, 56);
		frame.getContentPane().add(button_1);
		
		table = new JTable();
		table.setBounds(184, 50, 250, 180);
		frame.getContentPane().add(table);
	}

}

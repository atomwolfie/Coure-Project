import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTable;

public class CheckoutRemove {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutRemove window = new CheckoutRemove();
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
	public CheckoutRemove() {
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
		
		JLabel lblNewLabel = new JLabel("Remove Items");
		lblNewLabel.setBounds(174, 18, 112, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Remove item(s)");
		btnNewButton.setBounds(278, 164, 117, 70);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("New total:");
		lblNewLabel_1.setBounds(37, 218, 103, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JList list = new JList();
		list.setBounds(25, 46, 223, 132);
		frame.getContentPane().add(list);
		
	
	}
}

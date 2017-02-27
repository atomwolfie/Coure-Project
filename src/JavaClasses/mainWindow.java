import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class mainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
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
	public mainWindow() {
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
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setBounds(50, 101, 149, 61);
		frame.getContentPane().add(btnInventory);
		
		JLabel lblNewLabel = new JLabel("Store Management System");
		lblNewLabel.setBounds(133, 36, 268, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setBounds(245, 101, 134, 61);
		frame.getContentPane().add(btnCheckout);
	}
}

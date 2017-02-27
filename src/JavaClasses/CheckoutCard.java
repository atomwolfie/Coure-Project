import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class CheckoutCard {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckoutCard window = new CheckoutCard();
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
	public CheckoutCard() {
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
		
		JLabel lblCash = new JLabel("Cash");
		lblCash.setBounds(188, 20, 91, 16);
		frame.getContentPane().add(lblCash);
		
		JButton btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.setBounds(281, 172, 117, 67);
		frame.getContentPane().add(btnPrintReceipt);
		
		textField = new JTextField();
		textField.setText("0.00");
		textField.setBounds(216, 52, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterAmmount = new JLabel("Enter ammount:");
		lblEnterAmmount.setBounds(106, 57, 100, 16);
		frame.getContentPane().add(lblEnterAmmount);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(167, 100, 61, 16);
		frame.getContentPane().add(lblTotal);
		
		JLabel lblChange = new JLabel("Change:");
		lblChange.setBounds(148, 141, 61, 16);
		frame.getContentPane().add(lblChange);
	}

}

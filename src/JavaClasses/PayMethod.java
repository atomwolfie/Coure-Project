import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class PayMethod {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayMethod window = new PayMethod();
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
	public PayMethod() {
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
		
		JButton btnCash = new JButton("Cash");
		btnCash.setBounds(58, 87, 143, 76);
		frame.getContentPane().add(btnCash);
		
		JButton button = new JButton("Credit Card");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(229, 87, 143, 76);
		frame.getContentPane().add(button);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(18, 222, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		JLabel lblNewLabel = new JLabel("Payment Method");
		lblNewLabel.setBounds(158, 19, 126, 16);
		frame.getContentPane().add(lblNewLabel);
	}

}

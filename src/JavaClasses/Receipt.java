import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.List;
import javax.swing.JList;

public class Receipt {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receipt window = new Receipt();
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
	public Receipt() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 468);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblReceipt = new JLabel("Receipt");
		lblReceipt.setBounds(174, 6, 61, 29);
		frame.getContentPane().add(lblReceipt);
	
		
		JLabel lblNewLabel = new JLabel("Mr. Smith's Store");
		lblNewLabel.setBounds(153, 47, 143, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1125 Main St. Logan Utah 84173");
		lblNewLabel_1.setBounds(104, 57, 258, 40);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("----------------------------------------");
		label.setBounds(45, 258, 373, 16);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("----------------------------------------");
		label_1.setBounds(45, 305, 373, 16);
		frame.getContentPane().add(label_1);
		
		JLabel lblTax = new JLabel("tax:");
		lblTax.setBounds(174, 285, 61, 16);
		frame.getContentPane().add(lblTax);
		
		JLabel lblTotal = new JLabel("total:");
		lblTotal.setBounds(174, 327, 61, 16);
		frame.getContentPane().add(lblTotal);
		
		JList list = new JList();
		list.setBounds(55, 98, 317, 148);
		frame.getContentPane().add(list);
		
		JLabel lblNewLabel_2 = new JLabel("tel: (435) 757 - 2211");
		lblNewLabel_2.setBounds(141, 368, 143, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblMrsmithgroceryemailcom = new JLabel("email: mrsmithgrocery@email.com");
		lblMrsmithgroceryemailcom.setBounds(141, 396, 248, 16);
		frame.getContentPane().add(lblMrsmithgroceryemailcom);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}

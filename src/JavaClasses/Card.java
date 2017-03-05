import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Card {

	private JFrame frame;
	private JTextField txtfldName;
	private JTextField txtfldCardNumb;
	private JTextField txtfldExpMonth;
	private JTextField txtfldExpYear;
	private JTextField txtfldCRV;
	private JLabel lblExpDate;
	private JLabel lblCardNumb;
	private JLabel lblCrv;
	private JLabel lblName;
	private Order currentOrder;
	private CardValidator validator;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Card window = new Card(new Order());
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
	public Card(Order curOrder) {
		initialize(curOrder);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Order curOrder) {
		this.validator = new CardValidator();
		this.currentOrder = curOrder;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Credit or Debit");
		lblNewLabel.setBounds(167, 6, 133, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Swipe card or enter info");
		lblNewLabel_1.setBounds(138, 38, 215, 16);
		frame.getContentPane().add(lblNewLabel_1);

		JButton btnPrintReceipt = new JButton("Print Receipt");
		btnPrintReceipt.setBounds(281, 184, 150, 67);
		btnPrintReceipt.setBackground(new Color(95,186,125));
		frame.getContentPane().add(btnPrintReceipt);

		txtfldName = new JTextField();
		txtfldName.setText("Fulano Smith");
		txtfldName.setBounds(180, 65, 213, 26);
		frame.getContentPane().add(txtfldName);
		txtfldName.setColumns(10);
		
		txtfldCardNumb = new JTextField();
		txtfldCardNumb.setText("4244-4232-4322-4323");
		txtfldCardNumb.setBounds(180, 104, 213, 26);
		frame.getContentPane().add(txtfldCardNumb);
		txtfldCardNumb.setColumns(10);
		
		txtfldExpMonth = new JTextField();
		txtfldExpMonth.setText("08");
		txtfldExpMonth.setBounds(179, 141, 35, 26);
		frame.getContentPane().add(txtfldExpMonth);
		txtfldExpMonth.setColumns(10);
		
		txtfldExpYear = new JTextField();
		txtfldExpYear.setText("2019");
		txtfldExpYear.setBounds(223, 141, 48, 26);
		frame.getContentPane().add(txtfldExpYear);
		txtfldExpYear.setColumns(10);
		
		txtfldCRV = new JTextField();
		txtfldCRV.setText("093");
		txtfldCRV.setBounds(180, 180, 41, 26);
		frame.getContentPane().add(txtfldCRV);
		txtfldCRV.setColumns(10);

		lblName = new JLabel("Full Name:");
		lblName.setBounds(70, 71, 108, 16);
		frame.getContentPane().add(lblName);
		
		lblCardNumb = new JLabel("Card number:");
		lblCardNumb.setBounds(60, 109, 108, 16);
		frame.getContentPane().add(lblCardNumb);
		
		lblExpDate = new JLabel("Exp. Date:");
		lblExpDate.setBounds(81, 146, 74, 16);
		frame.getContentPane().add(lblExpDate);
		
		lblCrv = new JLabel("CRV:");
		lblCrv.setBounds(114, 185, 61, 16);
		frame.getContentPane().add(lblCrv);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(18, 222, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		ActionListener buttonListener = new ActionListener() {

	        //we have to define this method in order for an Action Listener to work
	        public void actionPerformed(ActionEvent e) { //'e' is the Action Event which is a button being clicked in our case

	            if (e.getSource() == btnGoBack) { //return to checkout screen

	            	this.setVisible(false);
	            	Payment payment = new Payment(currentOrder);
	            	payment.setVisible(true);
	            } 	          
	            if (e.getSource() == btnPrintReceipt) { //go to receipt screen
					boolean infoValid = true;
					if (!validator.fullNameIsValid(txtfldName.getText())) {
						invalidInfo(lblName);
						infoValid = false;
					}
					else {
						validInfo(lblName);
					}
					if (!validator.cardNumberIsValid(txtfldCardNumb.getText())) {
						invalidInfo(lblCardNumb);
						infoValid = false;
					}
					else {
						validInfo(lblCardNumb);
					}
					if (!validator.expDateIsValid(Integer.parseInt(txtfldExpMonth.getText()), Integer.parseInt(txtfldExpYear.getText()))) {
						invalidInfo(lblExpDate);
						infoValid = false;
					}
					else {
						validInfo(lblExpDate);
					}
					if (!validator.crvIsValid(txtfldCRV.getText())) {
						invalidInfo(lblCrv);
						infoValid = false;
					}
					else {
						validInfo(lblCrv);
					}
					if (!infoValid) {
						return;
					}

	            	this.setVisible(false);
	            	Customer cust = new Customer(txtfldName.getText());
					int custId = cust.writeToDatabase();
					currentOrder.setPaymentMethod("Card");
					currentOrder.setCustId(custId);
					currentOrder.writeToDatabase();
					frame.dispose();
	            	//Write new data to mysql db
	            	MainScreen main = new MainScreen();
	            	main.setVisible(true);
					Receipt receipt = new Receipt(currentOrder);
					receipt.setVisible(true);
	            }
	            
	        }

			private void setVisible(boolean b) {
				frame.setVisible(b);
			}
	    };
	    
		btnGoBack.addActionListener(buttonListener);
		btnPrintReceipt.addActionListener(buttonListener);
	}

	public void invalidInfo(JLabel lbl) {
		lbl.setForeground(Color.RED);
	}
	public void validInfo(JLabel lbl) {
		lbl.setForeground(Color.GREEN);
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);

	}
}
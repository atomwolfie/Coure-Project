import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RewardPointsPartTwo {

	private JFrame frame;
	private JTextField txtPoints;
	private Employee curEmployee;
	private Customer curCustomer;
	private Customer customer;
	private Order currentOrder;
	private boolean isReturn;
	private double points;
	private double orderTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RewardPointsPartTwo window = new RewardPointsPartTwo(new Order());
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	public RewardPointsPartTwo(Order curOrder) {
		this.isReturn = false;
		this.currentOrder = curOrder;
		initialize(curOrder);
	}
	
	public RewardPointsPartTwo(Order curOrder, Employee curEmployee, Customer curCustomer,boolean isReturn) {
		this.curEmployee = curEmployee;
		this.curCustomer = curCustomer;
		this.currentOrder = curOrder;
		this.isReturn = isReturn;
		initialize(curOrder);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Order curOrder) {
		this.currentOrder = curOrder;

		orderTotal = this.currentOrder.getOrderTotal();
			points = (this.curCustomer.getCustPoints())/10;
		
		
		frame = new JFrame();
		frame.setBounds(480, 177, 739, 545);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblRewardPoints = new JLabel("Reward Points");
		lblRewardPoints.setBounds(325, 29, 130, 36);
		frame.getContentPane().add(lblRewardPoints);
		
		JLabel lblCustomer = new JLabel("Customer id: " + this.curCustomer.getCustName());
		lblCustomer.setBounds(106, 94, 214, 16);
		frame.getContentPane().add(lblCustomer);
		
		JLabel lblPoints = new JLabel("points (in Dollars): $" + (this.curCustomer.getCustPoints())/10);
		lblPoints.setBounds(68, 126, 214, 16);
		frame.getContentPane().add(lblPoints);
		
		JLabel lblOrderTotal = new JLabel("Order Total: $ " + this.currentOrder.getOrderTotal());
		lblOrderTotal.setBounds(106, 214, 255, 16);
		frame.getContentPane().add(lblOrderTotal);
		
		JLabel lblAmmountToPay = new JLabel("Ammount to pay w/ points:");
		lblAmmountToPay.setBounds(21, 258, 199, 16);
		frame.getContentPane().add(lblAmmountToPay);
		
		txtPoints = new JTextField();
		txtPoints.setText("0.00");
		txtPoints.setBounds(207, 253, 130, 26);
		frame.getContentPane().add(txtPoints);
		txtPoints.setColumns(10);
		
		JButton btnCalculate = new JButton("calculate");
		btnCalculate.setBounds(366, 253, 117, 29);
		frame.getContentPane().add(btnCalculate);
		
	
		
		JLabel lblPointsRemaining = new JLabel("Points remaining: $" + points);
		lblPointsRemaining.setBounds(88, 310, 232, 16);
		frame.getContentPane().add(lblPointsRemaining);
		
		JLabel lblOrderTotalRemaining = new JLabel("Order Total Remaining: $" + orderTotal);
		lblOrderTotalRemaining.setBounds(55, 347, 274, 16);
		frame.getContentPane().add(lblOrderTotalRemaining);
		
		
		
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	


				
			String ammountWantedString = txtPoints.getText();
			double ammountWanted = Double.parseDouble(ammountWantedString);
			
			if(ammountWanted > points){
				JOptionPane.showMessageDialog(frame, "not enough points. Enter lower amount");
				txtPoints.setText("0.00");
				lblPointsRemaining.setText("Points remaining: $" + points);
				lblOrderTotalRemaining.setText("Order Total Remaining: $" + orderTotal);
			return;
			}
			//also need to check to see if more points than needed is used.
			if(ammountWanted > orderTotal){
				JOptionPane.showMessageDialog(frame, "Points exceeds Order total");
				txtPoints.setText("0.00");
				lblPointsRemaining.setText("Points remaining: $" + points);
				lblOrderTotalRemaining.setText("Order Total Remaining: $" + orderTotal);
				return;
			}
			
			else{
				//calculate remaining points and new total after.
				lblPointsRemaining.setText("points remaining: $" + (points - ammountWanted));
				lblOrderTotalRemaining.setText("Order Total Remaining: $"+ (orderTotal - ammountWanted));
			}
			
				}
			});
		
		
		
		JButton btnGoBack = new JButton("go back");
		btnGoBack.setBounds(594, 464, 117, 29);
		frame.getContentPane().add(btnGoBack);
		
		
		
		
		
		
		JButton btnContinue = new JButton("continue");
		btnContinue.setBounds(465, 464, 117, 29);
		frame.getContentPane().add(btnContinue);
		
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	

				String ammountWantedString = txtPoints.getText();
				double ammountWanted = Double.parseDouble(ammountWantedString);

				double leftOver = orderTotal - ammountWanted;
				//subtract points from customer
				
				double pointsLeft = points - ammountWanted;
				
				DBConnection.dbUpdateRecord("customers", "rewardPoints =\"" + pointsLeft*10 + "\"", "customerid = " + curCustomer.getCustName() );
				
				
				
				if(leftOver == 0.0){
	           
				//go to receipt	
					this.setVisible(false);
	            	Customer cust = new Customer(curCustomer.getCustName());
					int custId = cust.writeToDatabase();
					currentOrder.setPaymentMethod("Reward points");
					currentOrder.setCustId(custId);
					currentOrder.writeToDatabase(isReturn);
	            	MainScreen main = new MainScreen(curEmployee);
					Receipt receipt = new Receipt(currentOrder, isReturn);
					main.setVisible(true);
					receipt.setVisible(true);
					frame.dispose();
					
				}
				else{
					//go back to payment method to pay the rest
					currentOrder.setOrderTotal(leftOver);
					Payment paymnt = new Payment(currentOrder, curEmployee, false);
	            	paymnt.setVisible(true);
				}
			
				}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				frame.setVisible(b);
			}
			});
		
		
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {

	private String dateTime;
	private double orderTotal;
	private int custId;
	private String paymentMethod;
	private ArrayList<Purchases> purchases;
	private OrderValidator validator;

	public int addNewPurchase(Purchases purchase) {

		this.orderTotal += purchase.getPurchaseTotal();

		for (int i=0; i < this.purchases.size(); i++) {
			if (this.purchases.get(i).getProdId() == purchase.getProdId()){
				this.purchases.get(i).incrementQuantity(purchase.getQuantity());

				return i;
			}
		}
		this.purchases.add(purchase);
		return -1;
	}

	public void removePurchase(Purchases purchase) {
		for (int i=0; i < this.purchases.size(); i++) {
			if (this.purchases.get(i) == purchase) {
				this.orderTotal -= this.purchases.get(i).getPurchaseTotal();
				this.purchases.remove(i);
			}
		}
	}

	public void removePurchase(int purchaseIndex) {
		this.orderTotal -= this.purchases.get(purchaseIndex).getPurchaseTotal();
		this.purchases.remove(purchaseIndex);
	}

	public void clearPurchases() { purchases.clear(); }

	public ArrayList<Purchases> getPurchases() { return this.purchases; }

	public String getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(String dateTime) {
		if (this.validator.dateTimeIsValid(dateTime)) {
			this.dateTime = dateTime;
		}
	}

	public double getOrderTotal() {
		return this.orderTotal;
	}

	public void setOrderTotal(double orderTotal) {
		if (this.validator.orderTotalIsValid((orderTotal))) {
			this.orderTotal = orderTotal;
		}
	}

	public int getCustId() {
		return this.custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		if (validator.paymentMethodIsValid(paymentMethod)) {
			this.paymentMethod = paymentMethod;
		}
	}

	public void reset(){
		this.orderTotal = 0;
		this.paymentMethod = null;
		clearPurchases();
	}

	public int writeToDatabase(){
		int orderid = -1;
		try {
			String url = "jdbc:mysql://localhost:3306/store?autoReconnect=true&useSSL=false";
			Connection con = DriverManager.getConnection(url, "storeuser", "*fad!@plo*");
			Statement myStmt = con.createStatement();
			DecimalFormat dec = new DecimalFormat("#.00");
			if (this.custId != -1) {
				myStmt.executeUpdate("INSERT INTO orders (customerid,ordertotal,paymentmethod,date_time) VALUES ("
						+ "'" + this.custId
						+ "','" + dec.format(this.orderTotal * 1.03)
						+ "','" + this.paymentMethod
						+ "','" + this.dateTime
						+ "')");
			}
			else {
				myStmt.executeUpdate("INSERT INTO orders (customerid,ordertotal,paymentmethod,date_time) VALUES (NULL"
						+ ",'" + dec.format(this.orderTotal * 1.03)
						+ "','" + this.paymentMethod
						+ "','" + this.dateTime
						+ "')");
			}

			ResultSet myRsProducts = myStmt.executeQuery("SELECT * FROM orders ORDER BY orderid DESC LIMIT 1");
			myRsProducts.next();
			orderid = myRsProducts.getInt("orderid");

			for (int i = 0; i < this.purchases.size(); i++) {
				this.purchases.get(i).setOrderId(orderid);
				this.purchases.get(i).writeToDatabase();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return orderid;
	}

	public Order() {
		java.util.Date date = new java.util.Date();

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.validator = new OrderValidator();
		if (this.validator.dateTimeIsValid(sdf.format(date))) {
			dateTime = sdf.format(date);
		}
		else {
			dateTime = "1000-01-01 00:00:00";
		}
		this.orderTotal = 0;
		this.custId = -1;
		this.paymentMethod = null;
		this.purchases = new ArrayList();

	}

}
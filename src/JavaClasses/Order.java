import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order {

	private String dateTime;
	private double orderTotal;
	private int custId;
	private int phoneNum;
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

	public void incrementPurchaseQuantity(int purchIndex, int decrAmount) {
		this.purchases.get(purchIndex).incrementQuantity(decrAmount);
		this.orderTotal += this.purchases.get(purchIndex).getProdPrice() * decrAmount;
	}

	public void decrementPurchaseQuantity(int purchIndex, int decrAmount) {
		this.purchases.get(purchIndex).decrementQuantity(decrAmount);
		this.orderTotal -= this.purchases.get(purchIndex).getProdPrice() * decrAmount;
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

	public int writeToDatabase(boolean isReturn){
		int orderid = -1;
		String tableAndCols = "orders (customerid,ordertotal,paymentmethod,date_time)";
		String values;

		DecimalFormat dec = new DecimalFormat("#.00");

		if (this.custId != -1) {
			values = "\"" + this.custId + "\",\""
					+ dec.format(this.orderTotal * 1.03) + "\",\""
					+ this.paymentMethod + "\",\""
					+ this.dateTime + "\"";
			DBConnection.dbInsertInto(tableAndCols, values);
		}
		else {
			values = "NULL,\""
					+ dec.format(this.orderTotal * 1.03) + "\",\""
					+ this.paymentMethod + "\",\""
					+ this.dateTime + "\"";
			DBConnection.dbInsertInto(tableAndCols, values);
		}

		ResultSet myRsProducts = DBConnection.dbSelectAllFromTableOrderBy("orders", "orderid DESC LIMIT 1");
		try {
			myRsProducts.next();
			orderid = myRsProducts.getInt("orderid");

			for (int i = 0; i < this.purchases.size(); i++) {
				this.purchases.get(i).setOrderId(orderid);
				this.purchases.get(i).writeToDatabase(isReturn);
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
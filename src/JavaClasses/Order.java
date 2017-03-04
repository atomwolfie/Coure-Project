import java.util.ArrayList;

public class Order {

	private String dateTime;
	private double orderTotal;
	private int custId;
	private String paymentMethod;
	private int orderId;
	private ArrayList<Purchases> purchases;

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
		// TODO Implement
	}

	public void removePurchase(int purchaseIndex) {
		// TODO Implement
		this.orderTotal -= this.purchases.get(purchaseIndex).getPurchaseTotal();
		this.purchases.remove(purchaseIndex);
	}

	public void clearPurchases() { purchases.clear(); }

	public ArrayList<Purchases> getPurchases() { return this.purchases; }

	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * 
	 * @param dateTime
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public double getOrderTotal() {
		return this.orderTotal;
	}

	/**
	 * 
	 * @param orderTotal
	 */
	public void setOrderTotal(double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int getCustId() {
		return this.custId;
	}

	/**
	 * 
	 * @param custId
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}

	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	/**
	 * 
	 * @param paymentMethod
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getOrderId() {
		return this.orderId;
	}

	/**
	 * 
	 * @param orderId
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void reset(){
		this.orderTotal = 0;
		this.paymentMethod = null;
		clearPurchases();
	}

	public Order() {
		java.util.Date date = new java.util.Date();

		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		dateTime = sdf.format(date);
		this.orderTotal = 0;
		//custId;
		this.paymentMethod = null;
		//this.orderId;
		this.purchases = new ArrayList();

	}

}
public class Order {

	private string dateTime;
	private double orderTotal;
	private int custId;
	private string paymentMethod;
	private int orderId;

	public string getDateTime() {
		return this.dateTime;
	}

	/**
	 * 
	 * @param dateTime
	 */
	public void setDateTime(string dateTime) {
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

	public string getPaymentMethod() {
		return this.paymentMethod;
	}

	/**
	 * 
	 * @param paymentMethod
	 */
	public void setPaymentMethod(string paymentMethod) {
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

}
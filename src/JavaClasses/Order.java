public class Order {

	private String dateTime;
	private double orderTotal;
	private int custId;
	private String paymentMethod;
	private int orderId;

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

	public Order() {
		// TODO - implement Order.Order
		throw new UnsupportedOperationException();
	}

}
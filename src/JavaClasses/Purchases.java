public class Purchases {

	private int prodId;
	private int orderId;
	private int quantity;
	private double purchaseTotal;

	public int getProdId() {
		return this.prodId;
	}

	/**
	 * 
	 * @param prodId
	 */
	public void setProdId(int prodId) {
		this.prodId = prodId;
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

	public int getQuantity() {
		return this.quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPurchaseTotal() {
		return this.purchaseTotal;
	}

	/**
	 * 
	 * @param purchaseTotal
	 */
	public void setPurchaseTotal(double purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}

}
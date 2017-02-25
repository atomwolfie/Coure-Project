public class Product {

	private string prodName;
	private string type;
	private string provider;
	private double price;
	private int prodId;

	public string getProdName() {
		return this.prodName;
	}

	/**
	 * 
	 * @param prodName
	 */
	public void setProdName(string prodName) {
		this.prodName = prodName;
	}

	public string getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(string type) {
		this.type = type;
	}

	public string getProvider() {
		return this.provider;
	}

	/**
	 * 
	 * @param provider
	 */
	public void setProvider(string provider) {
		this.provider = provider;
	}

	public double getPrice() {
		return this.price;
	}

	/**
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

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

}
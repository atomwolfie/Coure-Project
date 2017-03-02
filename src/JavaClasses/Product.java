public class Product {

	private String prodName;
	private String type;
	private String provider;
	private double price;
	private int prodId;

	public String getProdName() {
		return this.prodName;
	}

	/**
	 * 
	 * @param prodName
	 */
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getProvider() {
		return this.provider;
	}

	/**
	 * 
	 * @param provider
	 */
	public void setProvider(String provider) {
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

	/**
	 * 
	 * @param prodName
	 * @param type
	 * @param provider
	 * @param price
	 * @param prodId
	 */
	public Product(String prodName, String type, String provider, double price, int prodId) {
		// TODO - implement Product.Product
		throw new UnsupportedOperationException();
	}

}
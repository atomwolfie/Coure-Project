import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Purchases {

	private int prodId;
	private int orderId;
	private int quantity;
	private String productName;
	private double prodPrice;
	private double purchaseTotal;
	private boolean isValidProduct;
	private boolean isValidOrder;
	private PurchasesValidator validator;
	private ArrayList<String> items;
	private	String[] str;
	//private String url = "jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false";

	public int getProdId() {
		return this.prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		if (this.validator.quantityIsValid(quantity)) {
			this.quantity = quantity;
		}
	}

	public void incrementQuantity(int quantity) {
		this.quantity += quantity;
		this.purchaseTotal += this.prodPrice * quantity;
	}

	public void decrementQuantity(int quantity) {
		this.quantity -= quantity;
		this.purchaseTotal -= this.prodPrice * quantity;
	}

	public double getProdPrice() { return prodPrice; }

	public String getProductName() { return productName; }

	public double getPurchaseTotal() {
		return this.purchaseTotal;
	}

	public void setPurchaseTotal(double purchaseTotal) {
		if (this.validator.purchaseTotalIsValid(purchaseTotal)) {
			this.purchaseTotal = purchaseTotal;
		}
	}


	public boolean isValidProduct() { return isValidProduct; }

	public boolean isValidOrder() {	return isValidOrder; }

	public void writeToDatabase() {
		DecimalFormat dec = new DecimalFormat("#.00");
		DBConnection.dbInsertInto("purchases", "\"" + this.prodId + "\",\"" + this.orderId + "\",\""
				+ this.quantity + "\",\"" + dec.format(this.purchaseTotal) + "\"");
		/*try {
			String url = "jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false";
			Connection con = DriverManager.getConnection(url, "root", "W01fp@ck");
			Statement myStmt = con.createStatement();
			DecimalFormat dec = new DecimalFormat("#.00");
			myStmt.executeUpdate("INSERT INTO purchases VALUES ('"
					+ this.prodId
					+ "','" + this.orderId
					+ "','" + this.quantity
					+ "','" + dec.format(this.purchaseTotal)
					+ "')");
		}
		catch (Exception e){
			e.printStackTrace();
		}*/
	}

	public Purchases(String name, int quantity) {
		this.validator = new PurchasesValidator();
		items = new ArrayList();
		str = new String[items.size()];

		ResultSet myRsProducts = DBConnection.dbSelectAllFromTableWhere("products", "productname=\"" + name + "\"");

		this.orderId = -1;
		try {
			if (myRsProducts.next()) {
				this.prodId = myRsProducts.getInt("productid");
				if (this.validator.quantityIsValid(quantity)) {
					this.quantity = quantity;
				} else {
					this.isValidProduct = false;
				}
				this.prodPrice = myRsProducts.getFloat("productprice");
				this.purchaseTotal = this.prodPrice * this.quantity;
				this.productName = myRsProducts.getString("productname");
				this.isValidProduct = true;
			} else {
				this.isValidProduct = false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, "root", "W01fp@ck");
			Statement myStmt = con.createStatement();
			ResultSet myRsProducts = myStmt.executeQuery("SELECT * FROM products WHERE productname=\"" + name + "\"");
			this.orderId = -1;
			if(myRsProducts.next()){
				this.prodId = myRsProducts.getInt("productid");
				if (this.validator.quantityIsValid(quantity)) {
					this.quantity = quantity;
				}
				else {
					this.isValidProduct = false;
				}
				this.prodPrice = myRsProducts.getFloat("productprice");
				this.purchaseTotal = this.prodPrice * this.quantity;
				this.productName = myRsProducts.getString("productname");
				this.isValidProduct = true;
			}
			else {
				this.isValidProduct = false;
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}*/
	}

	public Purchases(int prodId, int quantity) {
		this.validator = new PurchasesValidator();
		items = new ArrayList();
		str = new String[items.size()];

		ResultSet myRsProducts = DBConnection.dbSelectAllFromTableWhere("products", "productid=\"" + prodId + "\"");

		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		try {
			/*Connection con = DriverManager.getConnection(url, "root", "W01fp@ck");
			Statement myStmt = con.createStatement();
			ResultSet myRsProducts = myStmt.executeQuery("select * from products where productid=" + prodId);*/
			this.orderId = -1;
			if(myRsProducts.next()){
				this.prodId = prodId;
				if (this.validator.quantityIsValid(quantity)) {
					this.quantity = quantity;
				}
				else {
					this.isValidProduct = false;
				}
				this.prodPrice = myRsProducts.getFloat("productprice");
				this.purchaseTotal = this.prodPrice * this.quantity;
				this.productName = myRsProducts.getString("productname");
				this.isValidProduct = true;
			}
			else {
				this.isValidProduct = false;
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public Purchases(int prodId, int orderId, int quantity) {

		this.validator = new PurchasesValidator();
		items = new ArrayList();
		str = new String[items.size()];


		/*try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/

		ResultSet myRsProducts = DBConnection.dbSelectAllFromTableWhere("products", "productid=\"" + prodId + "\"");

		try {
			/*Connection con = DriverManager.getConnection(url, "root", "W01fp@ck");
			Statement myStmt = con.createStatement();
			ResultSet myRsProducts = myStmt.executeQuery("select * from products where productid=" + prodId);*/

			if(myRsProducts.next()){
				this.prodId = prodId;
				if (this.validator.quantityIsValid(quantity)) {
					this.quantity = quantity;
				}
				else {
					this.isValidProduct = false;
				}
				this.prodPrice = myRsProducts.getFloat("productprice");
				this.purchaseTotal = this.quantity*this.prodPrice;
				this.productName = myRsProducts.getString("productname");
				this.isValidProduct = true;
			}

			ResultSet myRsOrders = DBConnection.dbSelectAllFromTableWhere("orders", "orderid=\"" + orderId + "\"");

			//ResultSet myRsOrders = myStmt.executeQuery("select * from orders where orderid=" + orderId);

			if(myRsOrders.next()){
				this.orderId = orderId;
				this.isValidOrder = true;
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
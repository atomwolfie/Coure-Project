import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
	private ArrayList<String> items;
	private	String[] str;
	private String url = "jdbc:mysql://localhost:3306/joestore?autoReconnect=true&useSSL=false";

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



	public double getProdPrice() { return prodPrice; }

	public String getProductName() { return productName; }

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


	public boolean isValidProduct() { return isValidProduct; }

	public boolean isValidOrder() {	return isValidOrder; }

	/**
	 * 
	 * @param prodId
	 * @param orderId
	 */
	public Purchases(int prodId, int orderId) {
		items = new ArrayList();
		str = new String[items.size()];


		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, "java", "java");
			Statement myStmt = con.createStatement();
			ResultSet myRsProducts = myStmt.executeQuery("select * from products where productid=" + prodId);

			if(myRsProducts.next()){
				this.prodId = prodId;
				this.quantity = 1;
				this.prodPrice = myRsProducts.getFloat("productprice");
				this.purchaseTotal = this.prodPrice;
				this.productName = myRsProducts.getString("productname");
				this.isValidProduct = true;
			}

			ResultSet myRsOrders = myStmt.executeQuery("select * from orders where orderid=" + orderId);

			if(myRsOrders.next()){
				this.orderId = orderId;
				this.isValidOrder = true;
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param prodId
	 * @param orderId
	 * @param quantity
	 */
	public Purchases(int prodId, int orderId, int quantity) {

		items = new ArrayList();
		str = new String[items.size()];


		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(url, "java", "java");
			Statement myStmt = con.createStatement();
			ResultSet myRsProducts = myStmt.executeQuery("select * from products where productid=" + prodId);

			if(myRsProducts.next()){
				this.prodId = prodId;
				this.quantity = quantity;
				this.prodPrice = myRsProducts.getFloat("productprice");
				this.purchaseTotal = this.quantity*this.prodPrice;
				this.productName = myRsProducts.getString("productname");
				this.isValidProduct = true;
			}

			ResultSet myRsOrders = myStmt.executeQuery("select * from orders where orderid=" + orderId);

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
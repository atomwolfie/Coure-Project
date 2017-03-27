import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

	private String custName;
	private int custId;
	private int phoneNumb;
	private String email;

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getCustId() {
		return this.custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getPhoneNumb() {
		return this.phoneNumb;
	}

	public void setPhoneNumb(int phoneNumb) {
		CustomerValidator validator = new CustomerValidator();
		if (validator.phoneNumbIsValid(phoneNumb)) {
			this.phoneNumb = phoneNumb;
		}
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		CustomerValidator validator = new CustomerValidator();
		if (validator.emailIsValid(email)) {
			this.email = email;
		}
	}

	public int writeToDatabase() {
		int customerid = -1;
		ResultSet myRsProducts = DBConnection.dbSelectAllFromTableWhere("customers", "customername=\"" + this.custName + "\"");

		try {
			if (!myRsProducts.next()) {
				DBConnection.dbInsertInto("customers", "NULL" + ",\"" +this.custName + "\"" + ", NULL" + ", NULL");
				ResultSet myRsProducts2 = DBConnection.dbSelectAllFromTableOrderBy("customers", "customerid DESC LIMIT 1");
				myRsProducts2.next();
				customerid = myRsProducts2.getInt("customerid");
			}
			// In the future when we collect additional info update records here
			else {
				customerid = myRsProducts.getInt("customerid");
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}

		/*try {
			String url = "jdbc:mysql://localhost:3306/demo?autoReconnect=true&useSSL=false";
			Connection con = DriverManager.getConnection(url, "root", "W01fp@ck");
			Statement myStmt = con.createStatement();
			ResultSet myRsProducts = myStmt.executeQuery("SELECT * FROM customers WHERE customername='" + this.custName + "'");
			if (!myRsProducts.next()) {
				myStmt.executeUpdate("INSERT INTO customers (customername,email,phonenumber) VALUES ("
						+ "'" +this.custName + "'"
						+ ", NULL"
						+ ", NULL"
						+ ")");
				ResultSet myRsProducts2 = myStmt.executeQuery("SELECT * FROM customers ORDER BY customerid DESC LIMIT 1");
				myRsProducts2.next();
				customerid = myRsProducts2.getInt("customerid");
			}
			// In the future when we collect additional info update records here
			else {
				customerid = myRsProducts.getInt("customerid");
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}*/
		return customerid;
	}

	public Customer(String custName) {
		this.custName = custName;
	}
}
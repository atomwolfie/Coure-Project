public class Customer {

	private string custName;
	private int custId;
	private int phoneNumb;
	private string email;

	public string getCustName() {
		return this.custName;
	}

	/**
	 * 
	 * @param custName
	 */
	public void setCustName(string custName) {
		this.custName = custName;
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

	public int getPhoneNumb() {
		return this.phoneNumb;
	}

	/**
	 * 
	 * @param phoneNumb
	 */
	public void setPhoneNumb(int phoneNumb) {
		this.phoneNumb = phoneNumb;
	}

	public string getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(string email) {
		this.email = email;
	}

}
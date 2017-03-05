public class PurchasesValidator {

	public boolean quantityIsValid(int quantity) {
		return quantity > 0;
	}

	public boolean purchaseTotalIsValid(double purchaseTotal) {
		return purchaseTotal > 0;
	}
}
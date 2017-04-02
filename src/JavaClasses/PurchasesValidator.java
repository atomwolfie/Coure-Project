public class PurchasesValidator {

	public boolean quantityIsValid(int quantity) {
		return quantity > 0;
	}

	public boolean quantityIsValid(int quantity, boolean isReturn) {
		if (isReturn) {
			return quantity < 0;
		}
		return quantity > 0;
	}

	public boolean purchaseTotalIsValid(double purchaseTotal) { return purchaseTotal > 0; }

	public boolean purchaseTotalIsValid(double purchaseTotal, boolean isReturn) {
		if (isReturn) {
			return purchaseTotal < 0;
		}
		return purchaseTotal > 0;
	}
}
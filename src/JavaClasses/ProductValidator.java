public class ProductValidator {

	public boolean typeIsValid(String prodType) {
		// Can specify later what are valid 'Types'
		return true;
	}

	public boolean providerIsValid(String provider) {
		// Can specify later what are valid 'Providers'
		return true;
	}

	public boolean prodPriceIsValid(double prodPrice) {
		return prodPrice > 0;
	}
}
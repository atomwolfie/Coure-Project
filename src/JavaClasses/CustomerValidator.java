public class CustomerValidator {

	public boolean phoneNumbIsValid(int phoneNumb) {
		return String.valueOf(phoneNumb).length() == 10;
	}

	public boolean emailIsValid(String email) {
		boolean containsAmpersand = false;
		boolean containsPeriodAfterAmp = false;
		char temp;
		for (int i = 0; i < email.length(); i++) {
			temp = email.charAt(i);
			if (temp == '@') {
				if (containsAmpersand) {
					return false;
				}
				else {
					containsAmpersand = true;
				}
			}
			else if (containsAmpersand && temp == '.') {
				containsPeriodAfterAmp = true;
			}
		}
		return containsAmpersand && containsPeriodAfterAmp;
	}

}
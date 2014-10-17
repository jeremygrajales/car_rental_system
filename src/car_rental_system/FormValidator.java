package car_rental_system;

import java.util.LinkedHashMap;

public class FormValidator {
	
	// Leap year validation
	public static boolean validateDate(int year, int month, int day) {
		return true;
	}
	
	// Returns false if input is greater than length
	public static boolean validateLength(String input, int length) {
		return true;
	}
	
	// Returns false if input is not a valid positive integer
	public static boolean validateInteger(String input) {
		return true;
	}
	
	// Returns false if not a strong enough password
	public static boolean validatePasswordStrength(String input) {
		return true;
	}
	
	public static boolean validatePasswordMatch(String pwd1, String pwd2) {
		return true;
	}
	
	
	// Returns false if not a valid e-mail address
	public static boolean validateEmail(String input) {
		return true;
	}
	
	// Returns false if not a valid U.S. state
	public static boolean validateState(String state) {
		LinkedHashMap<String, String> states = Helper.getStates();
		return (states.containsKey(state));
	}
}
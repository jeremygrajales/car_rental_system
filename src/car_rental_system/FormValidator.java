package car_rental_system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {
	
	// Date validation // return true if valid
	public static boolean validateDate(int year, int month, int day) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		df.setLenient(false);	
		try {
			df.parse(year + "-" + month + "-" + day);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static boolean validateDateBefore(Date earlier, Date later) {
		return earlier.before(later);
	}
	
	public static boolean validateDateAfter(Date earlier, Date later) {
		return later.after(earlier);
	}
	
	// Returns false if input is blank
	public static boolean validateNotBlank(String input, boolean trim) {
		return (!trim && !input.isEmpty()) || (trim && !input.trim().isEmpty());
	}
	
	// Returns false if input is greater than length
	public static boolean validateLength(String input, int minLength, int maxLength) {
		
		//trim white spaces left and right of input
		input = input.trim();
		
		//get length of input
		int len = input.length();
		
		//compare lengths and check if valid
		return len >= minLength && len <= maxLength;
	}
	
	// Returns false if input is not a valid positive integer
	public static boolean validateInteger(String input) {
		//check to see if input is only numbers 0 to 9
		return input.matches("[0-9]+");
	}
	
	// Returns false if not a strong enough password
	public static boolean validatePasswordStrength(String input) {
		
		//checks to see if there is at least 1 capital letter, 1 lower case and 1 digit.
		Pattern pattern = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]))");
		
		//match given password to specified pattern
		Matcher matcher = pattern.matcher(input);
		
		// returns true if pattern is matched
		return matcher.matches();
	}
		
	public static boolean validatePasswordMatch(String pwd1, String pwd2) {
		return pwd1.equals(pwd2);
	}
	
	// Returns false if not a valid e-mail address
	public static boolean validateEmail(String input) {
		//use regular expression pattern to check valid email address format //example@email.com
		return input.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]{3})$");
	}

	// Returns false if not a valid U.S. state
	public static boolean validateState(String state) {
		LinkedHashMap<String, String> states = Helper.getStates();
		return states.containsKey(state);
	}
}
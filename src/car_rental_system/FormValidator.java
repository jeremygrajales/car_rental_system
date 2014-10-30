package car_rental_system;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {
	
	// Leap year validation // return true if valid
	public static void validateDate(int year, int month, int day) throws InvalidFieldException {
		
		//check to see if february 29 is valid for given year
		if(month == 2 && day == 29){
			if ((year % 400 != 0) || ((year % 4 != 0) && (year % 100 == 0)))
				throw new InvalidFieldException();
		} 
	}
	
	// Returns false if input is blank
	public static void validateNotBlank(String input, boolean trim) throws InvalidFieldException {
		if((trim && input.trim().isEmpty()) || (!trim && input.isEmpty()))
			throw new InvalidFieldException();
	}
	
	// Returns false if input is greater than length
	public static void validateLength(String input, int length) throws InvalidFieldException {
		
		//trim white spaces left and right of input
		input = input.trim();
		
		//get length of input
		int len = input.length();
		
		//compare lengths and check if valid
		if(len > length)
			throw new InvalidFieldException();
	}
	
	// Returns false if input is not a valid positive integer
	public static void validateInteger(String input) throws InvalidFieldException {
		
		//check to see if input is only numbers 0 to 9
		if(!input.matches("[0-9]+"))
			throw new InvalidFieldException();
	}
	
	// Returns false if not a strong enough password
	public static void validatePasswordStrength(String input) throws InvalidFieldException {
		
		//checks to see if there is at least 1 capital letter, 1 lower case and 1 digit. minimum = 8 maximum = 20
		Pattern pattern = Pattern.compile("((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,20})");
		
		//match given password to specified pattern
		Matcher matcher = pattern.matcher(input);
		
		// returns true if pattern is matched
		if(matcher.matches() == true)
			throw new InvalidFieldException();
	}
		
	public static void validatePasswordMatch(String pwd1, String pwd2) throws InvalidFieldException {
		if (!pwd1.equals(pwd2))
			throw new InvalidFieldException();
	}
	
	// Returns false if not a valid e-mail address
	public static void validateEmail(String input) throws InvalidFieldException {
		//use regular expression pattern to check valid email address format //example@email.com
		if(!input.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z]{3})$"))
			throw new InvalidFieldException();
	}

	// Returns false if not a valid U.S. state
	public static void validateState(String state) throws InvalidFieldException {
		LinkedHashMap<String, String> states = Helper.getStates();
		if(!states.containsKey(state))
			throw new InvalidFieldException();
	}
}
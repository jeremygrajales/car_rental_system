package car_rental_system;

public class InvalidFieldException extends Exception {

	public InvalidFieldException() {
		this("");
	}
	
	public InvalidFieldException(String message) {
		super(message);
	}
	
}

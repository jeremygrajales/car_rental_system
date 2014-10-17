package car_rental_system;

import java.sql.*;
import java.util.ArrayList;

public class Reservation {
	
	// Reservation fields
	private int id;
	
	/*
	 * Creates a new Reservation in the DB and returns
	 * a corresponding Reservation object.
	 */
	public static Reservation create() {
		
		Reservation reservation = null;
		
		return reservation;		
	}
	
	/*
	 * Selects reservation by a given reservation ID
	 * and returns a Reservation object.
	 */
	public static Reservation getById(int id) {
		
		// Create null Reservation
		Reservation reservation = null;
		
		// Query the database for a matching Reservation
		
		return reservation;
		
	}
	
	/*
	 * Selects all reservations by a given user ID and 
	 * returns them in an ArrayList<Reservation> object.
	 */
	public static ArrayList<Reservation> getByUserId(int userId) {
		
		// Create ArrayList for reservations
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		
		// Query the database for a matching Reservations and store each in reservations
		
		return reservations;
		
	}
	
	/*
	 * Cancels the Reservation specified
	 * by the id.
	 */
	public static void cancel(int id) {
		
	}
	
	/*
	 * Cancels the Reservation.
	 */
	public void cancel() {
		Reservation.cancel(this.id);
	}
	
}

package car_rental_system;

import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class Reservation {
	
	// Reseravation ENUMs
	static String STATUS_UNCONFIRMED = "unconfirmed";	
	static String STATUS_CONFIRMED = "confirmed";	
	
	// Reservation fields
	private int id;
	private String location;
	private Timestamp pickupTimestamp;
	private Timestamp returnTimestamp;
	private int customerId;
	private int vehicleTypeId;
	private int vehicleId;
	private String status;
	
	/*
	 * Creates a new Reservation in the DB and returns
	 * a corresponding Reservation object.
	 */
	public static Reservation create(String location, String pDate, String pTime, String rDate, String rTime, int custId, int vehicleTypeId ) {
	
		// Create null Reservation
		Reservation reservation = null;
		
		// Produce unique random reservation
		int reservationId = Helper.getRandomId();
		while(getById(reservationId) != null){
			reservationId = Helper.getRandomId();
		}
		
		Connection conn = Database.connect();
		try {
			// Generate timestamps as longs 
			long pTimestamp = Helper.parseDateTime(pDate, pTime);
			long rTimestamp = Helper.parseDateTime(rDate, rTime);
			
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO `reservation` values(%d, %s, %d, %d, %d, %s, null, null);", reservationId, location, pTimestamp, rTimestamp, custId, Reservation.STATUS_UNCONFIRMED, vehicleTypeId);
			stmt.executeUpdate(sql);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		reservation = Reservation.getById(reservationId);
		
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
		Connection conn = Database.connect();
		try {
				Statement stmt = conn.createStatement();
			
				String sql = String.format("SELECT * FROM 'reservation' WHERE 'id'='%d';", id);
				ResultSet result = stmt.executeQuery(sql);
				
				if(result.next())
				{
					reservation = new Reservation();
					reservation.id = result.getInt("id");
					reservation.location = result.getString("pickup_location");
					reservation.pickupTimestamp = result.getTimestamp("pickup_timestamp");
					reservation.returnTimestamp = result.getTimestamp("return_timestamp");
					reservation.customerId = result.getInt("cust_id");
					reservation.vehicleTypeId = result.getInt("vehicle_type_id");
					reservation.vehicleId = result.getInt("vehicle_id");
					reservation.status = result.getString("status");
				}
		     }
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return reservation;
	}
	
	/*
	 * Selects all reservations by a given user ID and 
	 * returns them in an ArrayList<Reservation> object.
	 */
	public static ArrayList<Reservation> getByUserId(int userId) {
		
		// Create ArrayList for reservations
		ArrayList<Reservation> reservations = new ArrayList<Reservation>();
		
		// Query the database for a matching Reservation
		Connection conn = Database.connect();
		try {
				Statement stmt = conn.createStatement();
			
				String sql = String.format("SELECT * FROM 'reservation' WHERE 'cust_id'='%d';", userId);
				ResultSet result = stmt.executeQuery(sql);
				
				// Loop through all results 
				while(result.next())
				{
					// Create null Reservation
					Reservation reservation = new Reservation();
					// Get fields from result
					reservation = new Reservation();
					reservation.id = result.getInt("id");
					reservation.location = result.getString("pickup_location");
					reservation.pickupTimestamp = result.getTimestamp("pickup_timestamp");
					reservation.returnTimestamp = result.getTimestamp("return_timestamp");
					reservation.customerId = result.getInt("cust_id");
					reservation.vehicleTypeId = result.getInt("vehicle_type_id");
					reservation.vehicleId = result.getInt("vehicle_id");
					reservation.status = result.getString("status");
					// Add reservation to list
					reservations.add(reservation);
				}
		     }
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return reservations;
	}
	
	/*
	 * Cancels the Reservation specified
	 * by the id.
	 */
	public static void cancel(int id) {
		Connection conn = Database.connect();
		try {
			Statement stmt = conn.createStatement();
			
				String sql = String.format("DELETE FROM 'reservation' WHERE 'id'='%d';", id);
				stmt.executeUpdate(sql);
				
		     }
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Cancels the Reservation.
	 */
	public void cancel() {
		Reservation.cancel(this.id);
	}
	
}

package car_rental_system;

import java.sql.*;
import java.util.ArrayList;

public class User {

	public int id;
	public String nameFirst;
	public String nameLast;
	public Date dob;
	public String email;
	public String addressStreet;
	public String addressState;
	public String addressCity;
	public int addressZip;
	public String hashTuple;
	public boolean authenticated = false;
	
	// Create new User in the DB and return a new User instance
	public static User create(String nameFirst, String nameLast, Date dob, String email, String addressStreet, String addressCity, String addressState, int addressZip, String hashTuple) {
		
		User user = null;
		Connection conn = Database.connect();
		try {
			Statement stmt = conn.createStatement();
			// Check if User already exists
			if(!User.exists(email)) {
				String query = String.format("INSERT INTO `customer` VALUES (null, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', null, null) ", nameFirst, nameLast, dob.toString(), email, addressStreet, addressCity, addressState, addressZip+"", hashTuple);
				stmt.executeUpdate(query);
				// Generate a User instance
				user = User.getByEmail(email);
			}
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		// return User instance
		return user;		
	}
	
	public static boolean exists(String email) {
		return User.getByEmail(email) != null;
	}
	
	public static User getByEmail(String email) {
		
		// Clean the email
		Helper.cleanString(email);
		
		// Create null User
		User user = null;
		
		// Query the database for a matching User
		Connection conn = null;
		try {
			conn = Database.connect();
			Statement stmt = conn.createStatement();
			String query = String.format("SELECT * FROM `customer` WHERE `email`='%s';", email);
			ResultSet result = stmt.executeQuery(query);
			if(result.next()) {
				// Generate a User instance
				user = new User();
				user.id = result.getInt("id");
				user.nameFirst = result.getString("name_first");
				user.nameLast = result.getString("name_last");
				user.dob = result.getDate("dob");
				user.email = result.getString("email");
				user.addressStreet = result.getString("address_street");
				user.addressCity = result.getString("address_city");
				user.addressState = result.getString("address_state");
				user.addressZip = result.getInt("address_zip");
				user.hashTuple = result.getString("hash");
			}
			conn.close();
		}
		catch(SQLException e) {
			System.out.println("DB EXCEPTION...");
		}
		// return User instance
		return user;	
	}
	
	// Uses query-less authentication // since we already have the hash in this instance
	public boolean authenticate(String password) {
		authenticated = Authenticator.authenticate(this, password);
		return authenticated;
	}	
	
	public ArrayList<Reservation> getReservations() {
		return Reservation.getByUserId(this.id);
	}
	
	public boolean isAdmin() {
		return User.isAdmin(this);
	}
	
	public static boolean isAdmin(User user) {
		
		// Query the database for a matching User
		Connection conn = Database.connect();
		try {
			Statement stmt = conn.createStatement();
			String query = String.format("SELECT * FROM `admin` WHERE `id`='%d';", user.id);
			ResultSet result = stmt.executeQuery(query);
			return result.next();
		}
		catch(SQLException e) {
			// 
		}
		return false;
	}
	
}

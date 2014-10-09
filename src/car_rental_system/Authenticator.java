package car_rental_system;

import java.sql.*;

public class Authenticator {

	public static boolean authenticate(String email, String password) {

		// Generate hash
		String hash = hash(password);
		
		// Clean input
		email = Helper.cleanString(email);
		password = Helper.cleanString(password);
		
		// Connect to DB
		Connection conn = Database.connect();

		// Make SQL query
		String query = "SELECT COUNT(*) AS rowCount FROM `customer` WHERE `cust_email`='"+email+"' AND `hash`='"+hash+"';";

		Statement stmt = null;
		try {
			// Execute query
			stmt = conn.createStatement();
			
			// Return result
			ResultSet result = stmt.executeQuery(query);
			result.next();
			
			// Return TRUE if we found a user with given credentials, else return FALSE
			return result.getInt("rowCount") > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}
	

	public static String hash(String value) {
		return value; // TODO: implement hashing with salt
	}
	
	
}

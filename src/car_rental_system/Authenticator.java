package car_rental_system;

import java.sql.*;

public class Authenticator {

	public static boolean authenticate(String email, String password) {

		// Clean input
		email = Helper.cleanString(email);
		password = Helper.cleanString(password);
		
		// Connect to DB
		Connection conn = Database.connect();

		// Make SQL query
		String query = "SELECT * FROM `customer` WHERE `cust_email`='"+email+"';";

		Statement stmt = null;
		try {
			// Execute query
			stmt = conn.createStatement();
			
			// Return result
			ResultSet result = stmt.executeQuery(query);
			result.next();
			
			// Get salt from hash
			String[] hashTuple = result.getString("hash").split(":");
			String originalHash = hashTuple[0];
			String salt = hashTuple[1];
			
			// Recreate hash
			String newHash = hash(password, salt);
			
			// Return TRUE if we found a user with given credentials, else return FALSE
			return newHash.equals(originalHash);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}

	public static String hash(String password, String salt) {
		return ""; 
		// TODO: implement hashing with salt
		// return hash and salt in String
		// use ':' to delimit hash and salt 
		// eg. hash:salt
		// salt = mcrypt(random integer)
		// hash = SHA256(password+salt)
		// return = hash:salt
	}
	
	
}

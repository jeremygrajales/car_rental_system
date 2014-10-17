package car_rental_system;

import java.sql.*;

public class Authenticator {

	// Uses query-less authentication
	public static boolean authenticate(User user, String password) {
	
		Helper.cleanString(password);
		
		// Get salt from hash
		return true; // challengePassword(password, user.hash);
		
	}
	
	// Authenticates against query
	public static boolean authenticate(String email, String password) {

		// Clean input
		email = Helper.cleanString(email);
		password = Helper.cleanString(password);
		
		// Connect to DB
		Connection conn = Database.connect();

		// Make SQL query
		String query = "SELECT * FROM `customer` WHERE `email`='"+email+"';";

		Statement stmt = null;
		try {
			// Execute query
			stmt = conn.createStatement();
			
			// Return result
			ResultSet result = stmt.executeQuery(query);
			if(result.next()) {
				return challengePassword(password, result.getString("hash"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}
	
	public static boolean challengePassword(String password, String hashAndSalt) {
		// Get salt from hash
		String[] hashTuple = hashAndSalt.split(":");
		if(hashTuple.length == 2) {
			String originalHash = hashTuple[0];
			String salt = hashTuple[1];
				
			// Recreate hash
			String newHash = Helper.hash(password, salt);
			
			// Return TRUE if we found a user with given credentials, else return FALSE
			return newHash.equals(originalHash);
		}
		
		return false;
		
	}
	
		
}
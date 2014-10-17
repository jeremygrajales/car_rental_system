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
		String query = "SELECT * FROM `customer` WHERE `email`='"+email+"';";

		Statement stmt = null;
		try {
			// Execute query
			stmt = conn.createStatement();
			
			// Return result
			ResultSet result = stmt.executeQuery(query);
			if(result.next()) {
			
				// Get salt from hash
				String[] hashTuple = result.getString("hash").split(":");
				if(hashTuple.length == 2) {
					String originalHash = hashTuple[0];
					String salt = hashTuple[1];
					
					// Recreate hash
					String newHash = Helper.hash(password, salt);
					
					// Return TRUE if we found a user with given credentials, else return FALSE
					return newHash.equals(originalHash);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;

	}
		
}
package car_rental_system;

import java.sql.*;

public class User {
	
	private String nameFirst;
	private String nameLast;
	private Date dob;
	private String email;
	private String addressStreet;
	private String addressState;
	private String addressCity;
	private int addressZip;
	public String hashTuple;
	private boolean authenticated = false;
	
	// Create new User in the DB and return a new User instance
	public static User create(String nameFirst, String nameLast, Date dob, String email, String addressStreet, String addressCity, String addressState, int addressZip, String hashTuple) {
		
		User user = null;
		Connection conn = Database.connect();
		try {
			Statement stmt = conn.createStatement();
			String query = String.format("INSERT INTO `customer` VALUES (null, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', null, null) ", nameFirst, nameLast, dob.toString(), email, addressStreet, addressCity, addressState, addressZip, hashTuple);
			stmt.executeUpdate(query);
			// Generate a User instance
			user = new User();
			user.nameFirst = nameFirst;
			user.nameLast = nameLast;
			user.dob = dob;
			user.email = email;
			user.addressStreet = addressStreet;
			user.addressCity = addressCity;
			user.addressState = addressState;
			user.addressZip = addressZip;
			user.hashTuple = hashTuple;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		// return User instance
		return user;		
	}
	
	public static User getByEmail(String email) {
		
		// Clean the email
		Helper.cleanString(email);
		
		// Create null User
		User user = null;
		
		// Query the database for a matching User
		Connection conn = Database.connect();
		try {
			Statement stmt = conn.createStatement();
			String query = String.format("SELECT * FROM `customer` WHERE `email`='%s';", email);
			ResultSet result = stmt.executeQuery(query);
			if(result.next()) {
				// Generate a User instance
				user = new User();
				user.nameFirst = result.getString("nameFirst");
				user.nameLast = result.getString("nameLast");
				user.dob = result.getDate("dob");
				user.email = result.getString("email");
				user.addressStreet = result.getString("address_street");
				user.addressCity = result.getString("address_city");
				user.addressState = result.getString("address_state");
				user.addressZip = result.getInt("address_zip");
				user.hashTuple = result.getString("hash");
			}
		}
		catch(SQLException e) {
			// TODO: implement exception handling.
		}
		// return User instance
		return user;	
	}
	
	// Uses query-less authentication // since we already have the hash in this instance
	public boolean authenticate(String password) {
		authenticated = Authenticator.authenticate(this, password);
		return authenticated;
	}	
	
}

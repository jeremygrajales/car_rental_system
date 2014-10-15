package car_rental_system;

import java.sql.*;

import java.security.SecureRandom; //get salt
import java.security.NoSuchAlgorithmException;// req'd exception error
import java.security.MessageDigest;//convert to sha1-256

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
					String newHash = hash(password, salt);
					
					// Return TRUE if we found a user with given credentials, else return FALSE
					return newHash.equals(originalHash);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true; // true until hash() implemented

	}
	// salt = mcrypt(random integer)
	public static String getNewSalt() throws NoSuchAlgorithmException {
		
		//generate a new salt and return as a string
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[24];
		random.nextBytes(salt);
		
	  return salt.toString();
	}
	// hash = SHA256(password+salt)
	public static String hash(String password, String salt) {
		 
		// TODO: implement hashing with salt
		String hashedPassword = null;
		try 
		{
          	//hash(password,salt) as sha-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");
          	md.update(salt.getBytes());
         	byte[] bytes = md.digest(password.getBytes());
          	StringBuilder sb = new StringBuilder();
         
			  	 for(int i=0; i< bytes.length ;i++)
         		 {
			  		 sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
         		 }
			  	 hashedPassword = sb.toString();
      	} 
      	catch (NoSuchAlgorithmException e) 
        {
           e.printStackTrace();
        }
		// return hash and salt in String
		// use ':' to delimit hash and salt 
		// eg. hash:salt
	  // return = hash:salt
      return hashedPassword + ":" + salt;//hash(password+salt):salt
	}
	
	
}

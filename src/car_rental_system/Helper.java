package car_rental_system;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Helper {
	
	public static String cleanString(String value) {
		return value; // TODO: implement cleaning to protect against SQL injection
	}
	
	public static String hash(String password, String salt) {
		
		String hashedPassword = null;
		try 
		{
			// Get instance of SHA-256
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			// Add salt to digest
          	md.update(salt.getBytes());
          	
          	// Digest with password
         	byte[] bytes = md.digest(password.getBytes());
         	
         	// Build hash string
          	StringBuilder sb = new StringBuilder();
          	for(int i=0; i< bytes.length ;i++) {
          		 // builds string with padding
			  	 sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
         	}
			hashedPassword = sb.toString();
      	} 
      	catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
        }
		
		// return hash
		return hashedPassword;
	}
	
	public static String getNewSalt() throws NoSuchAlgorithmException {
		
		// Generate a new salt and return as a string
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[24];
		random.nextBytes(salt);
		return salt.toString();
		
	}
}

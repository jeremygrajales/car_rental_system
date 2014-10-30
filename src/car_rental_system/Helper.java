package car_rental_system;

import java.security.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.*;
import java.util.*;
import java.text.ParseException;

public class Helper {
	
	public static Object firstNonNull(Object obj1, Object obj2) {
		if(obj1 == null && obj2 == null)
			throw new NullPointerException();
		return obj1 != null ? obj1 : obj2; 
	}
	
	public static LinkedHashMap<String, String> getStates() {
		LinkedHashMap<String, String> states = new LinkedHashMap<String, String>();
		states.put("AL", "Alabama");
		states.put("AK", "Alaska");
		states.put("AZ", "Arizona");
		states.put("AR", "Arkansas");
		states.put("CA", "California");
		states.put("CO", "Colorado");
		states.put("CT", "Connecticut");
		states.put("DE", "Delaware");
		states.put("FL", "Florida");
		states.put("GA", "Georgia");
		states.put("HI", "Hawaii");
		states.put("ID", "Idaho");
		states.put("IL", "Illinois");
		states.put("IN", "Indiana");
		states.put("IA", "Iowa");
		states.put("KS", "Kansas");
		states.put("KY", "Kentucky");
		states.put("LA", "Louisiana");
		states.put("ME", "Maine");
		states.put("MD", "Maryland");
		states.put("MA", "Massachusetts");
		states.put("MI", "Michigan");
		states.put("MN", "Minnesota");
		states.put("MS", "Mississippi");
		states.put("MO", "Missouri");
		states.put("MT", "Montana");
		states.put("NE", "Nebraska");
		states.put("NV", "Nevada");
		states.put("NH", "New Hampshire");
		states.put("NJ", "New Jersey");
		states.put("NM", "New Mexico");
		states.put("NY", "New York");
		states.put("NC", "North Carolina");
		states.put("ND", "North Dakota");
		states.put("OH", "Ohio");
		states.put("OK", "Oklahoma");
		states.put("OR", "Oregon");
		states.put("PA", "Pennsylvania");
		states.put("RI", "Rhode Island");
		states.put("SC", "South Carolina");
		states.put("SD", "South Dakota");
		states.put("TN", "Tennessee");
		states.put("TX", "Texas");
		states.put("UT", "Utah");
		states.put("VT", "Vermont");
		states.put("VA", "Virginia");
		states.put("WA", "Washington");
		states.put("WV", "West Virginia");
		states.put("WI", "Wisconsin");
		states.put("WY", "Wyoming");
		return states;
	}
	
	public static LinkedHashMap<Integer, String> getMonths() {
		LinkedHashMap<Integer, String> months = new LinkedHashMap<Integer, String>();
		months.put(1, "January");
		months.put(2, "Febuary");
		months.put(3, "March");
		months.put(4, "April");
		months.put(5, "May");
		months.put(6, "June");
		months.put(7, "July");
		months.put(8, "August");
		months.put(9, "September");
		months.put(10, "October");
		months.put(11, "November");
		months.put(12, "December");
		return months;
	}

	public static LinkedHashMap<Integer, String> getVehicleTypes() {
		
		LinkedHashMap<Integer, String> vehicleTypes = new LinkedHashMap<Integer, String>();
		
		// Fetch vehicle classes from DB
		Connection conn = Database.connect();
		try {
				Statement stmt = conn.createStatement();
				String sql = "SELECT * FROM `vehicle_type`";			
				ResultSet result = stmt.executeQuery(sql);
				
				while (result.next())
					vehicleTypes.put(result.getInt("id"), result.getString("type"));
				return vehicleTypes;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

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

	public static String timestampToDate(Timestamp timestamp, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(timestamp);
	}
		
	public static long dateToUnixTimestamp(String date) {
		return Helper.dateToUnixTimestamp(date, "00:00 AM");
	}
	
	public static long dateToUnixTimestamp(String date, String time) {
		// Generate timestamp from date and time
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
		try {
			return df.parse(date + " " + time).getTime()/1000;	
		}
		catch(ParseException e) {
			System.out.println("Could not parse date/time string: " + e.getMessage()); // TODO: implement logging
		}
		return -1;
	}
	
	public static int getNewReservationId() {
		SecureRandom random = new SecureRandom();
		int min = 100000000;
		int max = 999999999;
		return random.nextInt(max-min+1)+min;
	}
	
	public static String getNewSalt() {
		// Generate a new salt and return as a string
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[24];
		random.nextBytes(salt);
		return salt.toString();
	}
}

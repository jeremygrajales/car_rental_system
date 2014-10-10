package car_rental_system;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	
	public static Connection connect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		    throw new RuntimeException("JDBC Driver not loaded. Cannot find the driver in the classpath!", e);
		}

		 Connection conn = null;
		 Properties props = new Properties();
		 props.put("user", "root"); // TODO: implement configuration file
		 props.put("password", "password"); // TODO: implement configuration file
		 
		 try {
		 	conn = DriverManager.getConnection("jdbc:mysql://vps.neiucss.org/car_rental_system", props);
		 }
		 catch(SQLException e) {
			 // TODO: implement
			 System.out.println(e.getMessage());
		 }
		 return conn;
	}
	
	
	
}

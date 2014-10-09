package car_rental_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	
	public static Connection connect() {
		 Connection conn = null;
		 Properties props = new Properties();
		 props.put("user", "root"); // TODO: implement configuration file
		 props.put("password", "password"); // TODO: implement configuration file 
		 try {
		 	conn = DriverManager.getConnection("jdbc:mysql://vps.neiucss.org:3306/car_rental_system", props);
		 }
		 catch(SQLException e) {
			 // TODO: implement
		 }
		 return conn;
	}
	
	
	
}

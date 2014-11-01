package car_rental_system;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


public class Response {
	
	private static HttpServletResponse _response;
	
	public static void initialize(HttpServletResponse response) {
		_response = response;
	}
	
	public static void redirect(String path) {
		try {
			_response.sendRedirect(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static HttpServletResponse get() {
		return _response;
	}
}

package car_rental_system;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BadAuthController extends Controller {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
   		try {
			response.sendRedirect(request.getContextPath() + "/user/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath() + "/user/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	}
}

package car_rental_system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DashboardController extends Controller {
	public DashboardController() {
		super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		if(isPath("/dashboard", request)) {
			makeView("/dashboard/index");
		}
	}
}

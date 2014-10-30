package car_rental_system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController extends Controller {
    
    public BaseController() {
    	super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
    	if(isPath("login", request)) {
			makeView("login");
    	}
	}
}

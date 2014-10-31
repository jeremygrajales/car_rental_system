package car_rental_system;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet({"/home/*", "/user/*", "/dashboard/*", "/dashboard/reservation/*"})
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
        initRoutes();
    }
    
    private void initRoutes() {
    	// Index
    	Route.create("/home", "BaseController", Route.METHOD_GET); // Create user form
    	// User
    	Route.create("/user/create", "UserController", Route.METHOD_GET); // Create user form
    	Route.create("/user/create", "UserController", Route.METHOD_POST); // Create user form submission
    	Route.create("/user/create/thankyou", "UserController", Route.METHOD_GET); // Create user form thank you page
    	Route.create("/user/login", "UserController", Route.METHOD_GET); // Login form
    	Route.create("/user/login", "UserController", Route.METHOD_POST); // Login form submission
    	Route.create("/user/logout", "UserController", Route.METHOD_GET); // Logout screen
    	// Dashboard
    	Route.create("/dashboard", "DashboardController", Route.METHOD_GET, true);
    	Route.create("/dashboard/reservation/create", "ReservationController", Route.METHOD_GET, true);
    	Route.create("/dashboard/reservation/create", "ReservationController", Route.METHOD_POST, true);
    	Route.create("/dashboard/reservation/cancel", "ReservationController", Route.METHOD_POST, true);
    	Route.create("/dashboard/reservation/show", "ReservationController", Route.METHOD_GET, true);
    	Route.create("/dashboard/reservation/list", "ReservationController", Route.METHOD_GET, true);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// Initialize the session for this request
		//Session.initialize(request.getSession(true));
		
		Controller controller = Route.getController(request);
		String url = request.getRequestURL().toString();
		if(controller != null) // If we have a controller
			controller.process(request, response); // call the controller
		else // Else, send 404
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		//response.getWriter()
		
	}
}
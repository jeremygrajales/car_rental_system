package car_rental_system;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Route {
	
	public String path;
	private String controllerName;
	private String methodName;
	public String method;
	public Controller controller;
	public static ArrayList<Route> routes;
	public static final String METHOD_POST = "POST";
	public static final String METHOD_GET = "GET";
	public boolean secure;
	
	public static Route create(String path, String controller) {
		return new Route(path, controller, Route.METHOD_GET, false);
	}
	
	public static Route create(String path, String controller, String method) {
		return new Route(path, controller, method, false);
	}
	
	public static Route create(String path, String controller, boolean secure) {
		return new Route(path, controller, Route.METHOD_GET, secure);
	}
	
	public static Route create(String path, String controller, String method, boolean secure) {
		return new Route(path, controller, method, secure);
	}
	
	public Route(String path, String controller, String method, boolean secure) {
		
		// Initialize routes if null
		if(routes == null)
			routes = new ArrayList<Route>();
					
		this.secure = secure;
		this.path = path;
		this.method = method;
		
		// Get controller and method names
		String[] target = controller.split("@");
		this.controllerName = target[0];
		if(target.length == 2)
			this.methodName = target[1];
		
		init();
	}
	
	private void init() {
		try {
			// Create a new instance of the controller
			controller = ((Controller)Class.forName("car_rental_system." + controllerName).newInstance());
			// Add this Route list of routes
			routes.add(this);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Controller getController(HttpServletRequest request) {
		
		// Get assets from request
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		String requestMethod = request.getMethod();
		String requestPath = request.getServletPath() + (request.getPathInfo()==null?"":request.getPathInfo());
		
		for(Route route : Route.routes) {
			if(route.method.equals(requestMethod) && route.path.equals(requestPath)) {
				// Do authentication check if needed
				if(route.secure && (currentUser == null || !currentUser.authenticated)) {
					return new BadAuthController();
				}
				else { // Otherwise, just return the controller
					return route.controller;
				}
			}
		}
		return new BadAuthController();
	}
}

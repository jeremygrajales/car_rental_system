package car_rental_system;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String base = "";
 
	public Controller() {
		String name = this.getClass().getName().replaceFirst(".*\\.", "");
		name = (name.replaceAll("(?i)controller$", "")).toLowerCase();
		name = name.equals("base") ? "" : "/" + name;
		base = name;
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.request = request;
		this.response = response;
		if(request.getMethod().equals(Route.METHOD_GET))
			doGet(request, response);
		else if(request.getMethod().equals(Route.METHOD_POST))
			doPost(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {}
	
	public void makeView(String view) {
		if(view != null) {
			try {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views" + view + ".jsp");
				dispatcher.forward((ServletRequest)request, (ServletResponse)response);
			}
			catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public boolean isPath(String path, HttpServletRequest request) {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String requestedPath = "";
		path = path.startsWith("/") ? path : "/"+path; // prepend forward slash
		path = path.equals("/") ? "" : path; // remove lonely forward slashes
		
		if (requestURI.startsWith(contextPath)) {
		  	requestedPath = requestURI.substring(contextPath.length());
		}
		
		return  requestedPath.equals(path);
	}
}

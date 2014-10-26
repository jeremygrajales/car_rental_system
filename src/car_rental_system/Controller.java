package car_rental_system;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller implements Filter {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String base = "";
 
	public Controller() {
		String name = this.getClass().getName().replaceFirst(".*\\.", "");
		name = (name.replaceAll("(?i)controller$", "")).toLowerCase();
		name = name.equals("base") ? "" : "/" + name;
		base = name;
	}
	
	public void destroy() {
		
	}
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)  throws IOException, ServletException {}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest _request = (HttpServletRequest)request;
		this.request = _request;
		HttpServletResponse _response = (HttpServletResponse)response;
		this.response = _response;
		
		// Process request
		if(_request.getMethod().equals("GET"))
			doGet(_request, _response);
		else if(_request.getMethod().equals("POST")) 
			doPost(_request, _response);
		
		// If that controller didn't give us a response
		if(!response.isCommitted()) {
			// Move on to the next Controller
			chain.doFilter(request, response);
		}
	}
	
	public void makeView(String view) {
		System.out.println("here");
		if(view != null) {
			try {
				request.getRequestDispatcher("/WEB-INF/views/" + view + ".jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	} 
	
	public boolean isPath(String path) {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getServletContext().getContextPath();
		String requestedPath = "";

		if (requestURI.startsWith(contextPath)) {
		  	requestedPath = requestURI.substring(contextPath.length());
		}
		System.out.println(requestedPath + ":" + (base + "/" + path));
		return  requestedPath.equals(base + "/" + path);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}

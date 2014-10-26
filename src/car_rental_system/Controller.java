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
	
	
	public void destroy() {
		
	}
		
	protected abstract void doGet(HttpServletRequest request, HttpServletResponse response, FilterChain chain);
	protected abstract void doPost(HttpServletRequest request, HttpServletResponse response, FilterChain chain);
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest _request = (HttpServletRequest)request;
		HttpServletResponse _response = (HttpServletResponse)response;
		
		if(request.getProtocol().equals("GET"))
			doGet(_request, _response, chain);
		else if(request.getProtocol().equals("POST"))
			doPost(_request, _response, chain);
			
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}
}

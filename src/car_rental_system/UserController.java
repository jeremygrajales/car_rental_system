package car_rental_system;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserController extends Controller {
	
    public UserController() {
    	super();
    }
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
		if(isPath("/user/create", request)) {
			makeView("/user/create");
		}
		else if(isPath("/user/login", request)) {
			makeView("/user/login");
		}
		else if(isPath("/user/logout", request)) {
			HttpSession session = request.getSession();
			session.removeAttribute("currentUser");
			try {
				response.sendRedirect(request.getContextPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(isPath("/user/create/ thank", request)) {
			makeView("/user/create-thankyou");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		if(isPath("/user/login", request)) {
			// Retreive form input
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String error = "";
			
			// If the user is attempting to log in
			if(email != null && password != null) {
			
				// Find a matching User in the DB by e-mail
				User user = User.getByEmail(email);
				
				// If the user authenticates
				if(user != null && user.authenticate(password)) {
					// Store the User in the session  for later retrieval
					session.setAttribute("currentUser", user);

					// Redirect the user to the dashboard
					try {
						response.sendRedirect(request.getContextPath() + "/dashboard");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else { // Tell them they did something wrong
					error = "The email and/or password you provided is incorrect.";
					makeView("/user/login");
				}
			}
		}
		
		else if(isPath("/user/create", request)) {
			boolean errorsExist = false;
			ArrayList<String> errors = new ArrayList<String>();
			String nameFirst = request.getParameter("namefirst");
			String nameLast = request.getParameter("namelast");
			String dobYear = request.getParameter("dob_year");
			String dobMonth = request.getParameter("dob_month");
			String dobDay = request.getParameter("dob_day");
			String dob = String.format("%s-%s-%s", dobYear, dobMonth, dobDay);
			String street = request.getParameter("street");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repassword = request.getParameter("repassword");
			
			// If user is submitting form
			if(request.getParameter("submit") != null) {
				// Check for errors
				
				try {FormValidator.validateNotBlank(nameFirst, true);} 
				catch (InvalidFieldException e) { errors.add("namefirst_blank"); errors.add("namefirst"); }
				try {FormValidator.validateLength(nameFirst, 50);} 
				catch (InvalidFieldException e) { errors.add("namefirst_length"); errors.add("namefirst"); }
				
				try {FormValidator.validateNotBlank(nameLast, true);} 
				catch (InvalidFieldException e) { errors.add("namelast_blank"); errors.add("namelast"); }
				try {FormValidator.validateLength(nameLast, 50);} 
				catch (InvalidFieldException e) { errors.add("namelast_length"); errors.add("namelast"); }
				 
				try {
					int year = Integer.parseInt(dobYear);
					int month = Integer.parseInt(dobMonth);
					int day = Integer.parseInt(dobDay);
					FormValidator.validateDate(year, month, day);
				} 
				catch(NumberFormatException | InvalidFieldException e) { errors.add("dob"); }
				
				try { FormValidator.validateNotBlank(street, true); } 
				catch (InvalidFieldException e) { errors.add("street_blank"); errors.add("street"); }
				try { FormValidator.validateLength(street, 255); } 
				catch (InvalidFieldException e) { errors.add("street_length"); errors.add("street"); }
					
				
				try { FormValidator.validateNotBlank(city, true); } 
				catch (InvalidFieldException e) { errors.add("city_blank"); errors.add("city"); }
				try { FormValidator.validateLength(city, 50); } 
				catch (InvalidFieldException e) { errors.add("city_length"); errors.add("city"); }
					
				try { FormValidator.validateState(state); }
				catch (InvalidFieldException e) { errors.add("state"); }
				
				try { FormValidator.validateNotBlank(zip, true); } 
				catch (InvalidFieldException e) { errors.add("zip_blank"); errors.add("zip"); }
				try { FormValidator.validateInteger(zip); } 
				catch (InvalidFieldException e) { errors.add("zip_integer"); errors.add("zip"); }
				try { FormValidator.validateLength(zip, 5); } 
				catch (InvalidFieldException e) { errors.add("zip_length"); errors.add("zip"); }
					
				try { FormValidator.validateNotBlank(email, true); } 
				catch (InvalidFieldException e) { errors.add("email_blank"); errors.add("email"); }
				try { FormValidator.validateLength(email, 50); } 
				catch (InvalidFieldException e) { errors.add("email_length"); errors.add("email"); }
				try { FormValidator.validateEmail(email); } 
				catch (InvalidFieldException e) { errors.add("email_valid"); errors.add("email"); }

				/*if(!FormValidator.validatePasswordStrength(password))
					errors.add("password");
				if(!FormValidator.validatePasswordMatch(password, repassword))
					errors.add("repassword");*/
				// True if errors exist
				errorsExist = !errors.isEmpty();
				
				// If no errors exist
				if(!errorsExist) {
					// Generate hashed password
					Password pwd = new Password(password);
					String[] hashTuple = pwd.getHashTuple();
					// Create a new user // Adds user to DB
					Date date = Date.valueOf(dob);
					User user = User.create(nameFirst, nameLast, date, email, street, city, state, Integer.parseInt(zip), hashTuple[0]+":"+hashTuple[1]);
				}
				else { // Store errors in session
					Session.flashAll(request.getParameterMap());
					session.setAttribute("errors", errors);
				}
				
				try {
					response.sendRedirect(request.getContextPath() + "/user/create");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		
	}
}
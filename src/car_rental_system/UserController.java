package car_rental_system;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map.Entry;

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
			Session.removeAttribute("currentUser");
			Response.redirect(request.getContextPath() + "/home");
		}
		else if(isPath("/user/create/thankyou", request)) {
			makeView("/user/create-thankyou");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		if(isPath("/user/login", request)) {
			// Retreive form input
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// If the user is attempting to log in
			if(email != null && password != null) {
			
				// Find a matching User in the DB by e-mail
				User user = User.getByEmail(email);
				
				// If the user authenticates
				if(user != null && user.authenticate(password)) {
					// Store the User in the session  for later retrieval
					Session.setAttribute("currentUser", user);

					// Redirect the user to the dashboard
					Response.redirect(request.getContextPath() + "/dashboard");
				}
				else { // Tell them they did something wrong
					Session.flash("error", "The email and/or password you provided is incorrect.");
					Response.redirect(request.getContextPath() + "/user/login");
				}
			}
		}
		
		else if(isPath("/user/create", request)) {
			
			// Fetch inputs
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

			// Input validation
			Errors errors = new Errors();
			// Check if user already exists before anything
			if(User.getByEmail(email) != null)  {
				errors.add("user_exists");
			}
			else {
				// first name
				errors.add(FormValidator.validateNotBlank(nameFirst, true) ? new String[]{"namefirst_blank", "namefirst"} : null);
				errors.add(FormValidator.validateLength(nameFirst, 0, 50) ? new String[]{"namefirst_length", "namefirst"} : null);
				// last name
				errors.add(FormValidator.validateNotBlank(nameLast, true) ? new String[]{"namelast_blank", "namelast"} : null);
				errors.add(FormValidator.validateLength(nameLast, 0, 50) ? new String[]{"namelast_length", "namelast"} : null);
				// date of birth
				int year = 0, month = 0, day = 0;
				try {
					year = Integer.parseInt(dobYear);
					month = Integer.parseInt(dobMonth);
					day = Integer.parseInt(dobDay);
				}
				catch(NumberFormatException e) { errors.add("dob"); }
				errors.add(FormValidator.validateDate(year, month, day) ? "dob" : null); 
				// street
				errors.add(FormValidator.validateNotBlank(street, true) ? new String[]{"street_blank", "street"} : null);
				errors.add(FormValidator.validateLength(street, 0, 255) ? new String[]{"street_length", "street"} : null);
				// city
				errors.add(FormValidator.validateNotBlank(city, true) ? new String[]{"city_blank", "city"} : null);
				errors.add(FormValidator.validateLength(city, 0, 50) ? new String[]{"city_length", "city"} : null);
				// state
				errors.add(FormValidator.validateState(state) ? "state" : null);
				// zip
				errors.add(FormValidator.validateNotBlank(zip, true) ? new String[]{"zip_blank", "zip"} : null);
				errors.add(FormValidator.validateInteger(zip) ? new String[]{"zip_integer", "zip"} : null);
				errors.add(FormValidator.validateLength(zip, 5, 5) ? new String[]{"zip_length", "zip"} : null);
				// email
				errors.add(FormValidator.validateNotBlank(email, true) ? new String[]{"email_blank", "email"} : null);
				errors.add(FormValidator.validateLength(email, 0, 50) ? new String[]{"email_length", "email"} : null);
				errors.add(FormValidator.validateEmail(email) ? new String[]{"email_valid", "email"} : null);
				// password
				errors.add(FormValidator.validateNotBlank(password, true) ? new String[]{"password_blank", "password"} : null);
				errors.add(FormValidator.validateLength(password, 8, 20) ? new String[]{"password_length", "password"} : null);
				errors.add(FormValidator.validatePasswordStrength(password) ? new String[]{"password_valid", "password"} : null);
				errors.add(FormValidator.validatePasswordMatch(password, repassword) ? new String[]{"repassword_valid", "repassword"} : null);
				 
			}
			// If no errors exist
			if(errors.isEmpty()) {
				// Generate hashed password
				Password pwd = new Password(password);
				String[] hashTuple = pwd.getHashTuple();
				
				// Create a new user // Adds user to DB
				Date date = Date.valueOf(dob);
				User user = User.create(nameFirst, nameLast, date, email, street, city, state, Integer.parseInt(zip), hashTuple[0]+":"+hashTuple[1]);
				
				// Redirect to thank you page
				Response.redirect(request.getContextPath() + "/user/create/thankyou");
			}
			else { 
				// Flash errors and parameters into session
				Session.flash("errors", errors);
				for(Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
					Session.flash(entry.getKey(), entry.getValue()[0]);
				}
				
				// Redirect to user create page
				Response.redirect(request.getContextPath() + "/user/create");
			}
		}
	}
}
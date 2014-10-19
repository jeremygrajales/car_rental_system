<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@ include file="layouts/header.jsp" %>
<%

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
		if(!FormValidator.validateLength(nameFirst, 255))
			errors.add("namefirst");
		if(!FormValidator.validateLength(nameLast, 255))
			errors.add("namelast"); 
		int year = Integer.parseInt(dobYear);
		int month = Integer.parseInt(dobMonth);
		int day = Integer.parseInt(dobDay);
		if(!FormValidator.validateDate(year, month, day))
			errors.add("dob");
		if(!FormValidator.validateLength(street, 255))
			errors.add("street");
		if(!FormValidator.validateLength(city, 255))
			errors.add("city");
		if(!FormValidator.validateState(state))
			errors.add("state");
		if(!FormValidator.validateLength(zip, 5))
			errors.add("zip");
		if(!FormValidator.validateInteger(zip))
			errors.add("zip");
		if(!FormValidator.validateEmail(email))
			errors.add("email");
		if(!FormValidator.validatePasswordStrength(password))
			errors.add("password");
		if(!FormValidator.validatePasswordMatch(password, repassword))
			errors.add("repassword");
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
	}
%>
<h1>Sign Up</h1>
<% if(!errors.isEmpty()) { %><div class="error">There were one or more errors in your submission.</div><% } %>
<form action="" method="post">
		<p>
			<label>First Name:</label> <input class="error" type="text" name="namefirst" placeholder='Enter First Name'>
			<span class="error">First Name must not be blank.</span>
			<span class="error">First Name must be less than 50 characters.</span>
		</p>
		<p><label>Last Name:</label> <input <% if(errors.contains("namelast")) out.print("class=\"error\" "); %>type="text" name="namelast" placeholder='Enter Last Name'>
			<span class="error">Last Name must not be blank.</span>
			<span class="error">Last Name must be less than 50 characters.</span>
		</p>
		<p><label>Date of birth:</label>
			<!--  TODO: need to implement dob field error --> 
			<select id="form_dob_month" name="dob_month" class="error">
				<option value="-">-</option>
				<% 
					LinkedHashMap<Integer, String> months = Helper.getMonths();
					for(Map.Entry<Integer, String> month : months.entrySet()) {
						out.println(String.format("<option value=\"%02d\">%s</option>", month.getKey(), month.getValue()));		
					}
				%>
			</select>
			<select id="form_dob_day" name="dob_day" class="error">
				<option value="-">-</option>
				<% 
					for(int i = 1; i <= 31; i++) {
						out.println(String.format("<option value=\"%02d\">%d</option>", i, i));		
					}
				%>
			</select>
	 		<select id="form_dob_year" name="dob_year" class="error">
				<option value="-">-</option>
				<% 
					for(int i = 2011; i >= 1959; i--) {
						out.println(String.format("<option value=\"%d\">%d</option>", i, i));		
					}
				%>
			</select>
			<span class="error">Date is invalid.</span>
			<span class="error">Must select a month</span>
			<span class="error">Must select a day</span>
			<span class="error">Must select a year</span>
		</p>
		<p><label>Street Address:</label> <input <% if(errors.contains("street")) out.print("class=\"error\" "); %>type="text" name="street" placeholder='Enter Last Name'>
			<span class="error">Street address is required</span>
			<span class="error">Street address must be less than 50 characters</span>
		</p>
		<p><label>City:</label> <input <% if(errors.contains("city")) out.print("class=\"error\" "); %>type="text" name="city" placeholder='Enter Last Name'>
			<span class="error">City must not be blank.</span>
			<span class="error">City must be less than 50 characters.</span>
		</p>
		<p><label>State:</label>
		<!--  TODO: need to implement state field error -->
		<select name="state" size="1">
			<option selected value="">-- Select State --</option>
			<% 
				LinkedHashMap<String, String> states = Helper.getStates();
				for(Map.Entry<String, String> _state : states.entrySet()) {
					out.println(String.format("<option value=\"%s\">%s</option>", _state.getKey(), _state.getValue()));		
				}
			%>
		</select>
		<span class="error">State must be selected.</span>
		</p>
		<p><label>Zip Code:</label> <input <% if(errors.contains("zip")) out.print("class=\"error\" "); %>type="text" name="zip" placeholder='Enter Zip Code'>
			<span class="error">Zip code must not be blank.</span>
			<span class="error">Zip code must be exactly 5 numbers.</span>
		</p>
		<p><label>Email:</label> <input <% if(errors.contains("email")) out.print("class=\"error\" "); %>type="text" name="email" placeholder='example@email.com'>
			<span class="error">Email address must not be blank.</span>
			<span class="error">Email address must be less than 50 characters.</span>
			<span class="error">Email address must be in correct format <i>example: email@address.com</i></span>
		</p>
		<p><label>Password:</label> <input <% if(errors.contains("password")) out.print("class=\"error\" "); %>type="password" name="password" placeholder='**********'>
			<span class="error">Password must be at least 8 characters and no more than 20 characters.</span>
			<span class="error">Password must contain at least one number and one capital letter.</span>
		</p>
		<p><label>Re-enter Password:</label> <input <% if(errors.contains("repassword")) out.print("class=\"error\" "); %>type="password" name="repassword" placeholder='**********'>
			<span class="error">Must re-enter password. Cannot be blank.</span>
			<span class="error">Passwords do not match.</span>
		</p>
		<input type="submit" name="submit" value="Submit" />
</form>
<%@ include file="layouts/footer.jsp" %>
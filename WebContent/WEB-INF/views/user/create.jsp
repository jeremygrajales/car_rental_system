<%@page import="java.text.DateFormat"%>
<%@page import="java.sql.Date"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@ include file="../layouts/header.jsp" %>
<%

	Errors errors = (Errors)Session.getObject("errors", new Errors());
	String namefirst = Session.getString("namefirst", "");
	String namelast = Session.getString("namelast", "");
	Integer dob_month = Session.getInteger("dob_month", -1);
	Integer dob_day = Session.getInteger("dob_day", -1);
	Integer dob_year = Session.getInteger("dob_year", -1);
	String street = Session.getString("street", "");
	String city = Session.getString("city", "");
	String state = Session.getString("state", "");
	String zip = Session.getString("zip", "");
	String email = Session.getString("email", "");
			
%>
<h1>Sign Up</h1>
<% if(!errors.isEmpty()) { %><div class="error">There were one or more errors in your submission.</div><% } %>
<% if(errors.contains("repassword_valid")) { %><span class="error">Passwords did not match.</span><% } %>
	<form action="" method="post">
		<p>
			<label>First Name:</label>
			<input <% if(errors.contains("namefirst")) out.print("class=\"error\" "); %>type="text" name="namefirst" placeholder="Enter First Name" value="<% out.print(namefirst); %>">
			<div class="errors">
				<% if(errors.contains("namefirst_blank")) { %><span class="error">First Name must not be blank.</span><% } %>
				<% if(errors.contains("namefirst_length")) { %><span class="error">First Name must be less than 50 characters.</span><% } %>
			</div>
		</p>
		<p>
			<label>Last Name:</label>
			<input <% if(errors.contains("namelast")) out.print("class=\"error\" "); %>type="text" name="namelast" placeholder="Enter Last Name" value="<% out.print(namelast); %>">
			<div class="errors">
				<% if(errors.contains("namelast_blank")) { %><span class="error">Last Name must not be blank.</span><% } %>
				<% if(errors.contains("namelast_length")) { %><span class="error">Last Name must be less than 50 characters.</span><% } %>
			</div>
		</p>
		<p>
			<label>Date of birth:</label>
			<!--  TODO: need to implement dob field error --> 
			<select name="dob_month"<% if(errors.contains("dob_valid")) out.print(" class=\"error\""); %>>
				<option value="">-</option>
				<% 
					LinkedHashMap<Integer, String> months = Helper.getMonths();
					for(Map.Entry<Integer, String> month : months.entrySet()) {
						out.println(String.format("<option value=\"%02d\""+((dob_month == month.getKey())?" selected=\"selected\"":"")+">%s</option>", month.getKey(), month.getValue()));
						
					}
				%>
			</select>
			<select name="dob_day"<% if(errors.contains("dob_valid")) out.print(" class=\"error\""); %>>
				<option value="">-</option>
				<% 
					for(int i = 1; i <= 31; i++) {
						out.println(String.format("<option value=\"%02d\""+(dob_day != null && dob_day == i?" selected=\"selected\"":"")+">%d</option>", i, i));		
					}
				%>
			</select>
	 		<select name="dob_year"<% if(errors.contains("dob_valid")) out.print(" class=\"error\""); %>>
				<option value="">-</option>
				<% 
					for(int i = 2011; i >= 1959; i--) {
						out.println(String.format("<option value=\"%d\""+(dob_year != null && dob_year == i?" selected=\"selected\"":"")+">%d</option>", i, i));		
					}
				%>
			</select>
			<div class="errors">
				<% if(errors.contains("dob")) { %><span class="error">Date is invalid.</span><% } %>
			</div>
		</p>
		<p>
			<label>Street Address:</label> <input <% if(errors.contains("street")) out.print("class=\"error\" "); %>type="text" name="street" placeholder="Enter Last Name" value="<% out.print(street); %>">
			<div class="errors">
				<% if(errors.contains("street_blank")) { %><span class="error">Street address must not be blank.</span><% } %>
				<% if(errors.contains("street_length")) { %><span class="error">Street address must be less than 255 characters.</span><% } %>
			</div>
		</p>
		<p>
			<label>City:</label> <input <% if(errors.contains("city")) out.print("class=\"error\" "); %>type="text" name="city" placeholder="Enter Last Name" value="<% out.print(city); %>">
			<div class="errors">
				<% if(errors.contains("city_blank")) { %><span class="error">City must not be blank.</span><% } %>
				<% if(errors.contains("city_length")) { %><span class="error">City must be less 50 characters.</span><% } %>
			</div>
		</p>
		<p>
			<label>State:</label>
			<!--  TODO: need to implement state field error -->
			<select <% if(errors.contains("state")) out.print("class=\"error\" "); %>name="state" size="1">
				<option selected value="">-- Select State --</option>
				<% 
					LinkedHashMap<String, String> states = Helper.getStates();
					for(Map.Entry<String, String> _state : states.entrySet()) {
						out.println(String.format("<option value=\"%s\""+((state.equals(_state.getKey()))?" selected=\"selected\"":"")+">%s</option>", _state.getKey(), _state.getValue()));		
					}
				%>
			</select>
			<div class="errors">
				<% if(errors.contains("state")) { %><span class="error">State must be selected.</span><% } %>
			</div>
		</p>
		<p><label>Zip Code:</label> <input <% if(errors.contains("zip")) out.print("class=\"error\" "); %>type="text" name="zip" placeholder="Enter Zip Code" value="<% out.print(zip); %>">
			<div class="errors">
				<% if(errors.contains("zip_blank")) { %><span class="error">Zip code must not be blank.</span><% } %>
				<% if(errors.contains("zip_integer")||errors.contains("zip_length")) { %><span class="error">Zip code must be exactly 5 numbers.</span><% } %>
			</div>
		</p>
		<p><label>Email:</label> <input <% if(errors.contains("email")) out.print("class=\"error\" "); %>type="text" name="email" placeholder="example@email.com" value="<% out.print(email); %>">
			<div class="errors"> 
				<% if(errors.contains("user_exists")) { %><span class="error">A user with the e-mail address you provided already exists. Please register with another e-mail address.</span><% } %>
				<% if(errors.contains("email_blank")) { %><span class="error">Email address must not be blank.</span><% } %>
				<% if(errors.contains("email_length")) { %><span class="error">Email address must be less than 50 characters.</span><% } %>
				<% if(errors.contains("email_valid")) { %><span class="error">Email address must be in correct format <i>example: email@address.com</i></span><% } %>
			</div>
		</p>
		<p><label>Password:</label> <input <% if(errors.contains("password")) out.print("class=\"error\" "); %>type="password" name="password" placeholder="**********">
			<div class="errors">
				<% if(errors.contains("password_blank")) { %><span class="error">Password cannot be blank.</span><% } %>
				<% if(errors.contains("password_length")) { %><span class="error">Password must be at least 8 characters and no more than 20 characters.</span><% } %>
				<% if(errors.contains("password_valid")) { %><span class="error">Password must contain at least one number and one capital letter.</span><% } %>
			</div>
		</p>
		<p><label>Re-enter Password:</label> <input type="password" name="repassword" placeholder="**********">
			<div class="errors">
				
			</div>
		</p>
		<input type="submit" name="submit" value="Submit" />
	</form>
<%@ include file="../layouts/footer.jsp" %>
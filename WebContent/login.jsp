<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ include file="layouts/header.jsp" %>
<%

	// Retreive form input
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	String error = "";
	
	// If the user is attempting to log in
	if(email != null && password != null) {
		// Redirect the user if they're authenticated
		if(Authenticator.authenticate(email, password)) {
			response.sendRedirect("dashboard.jsp");		
		}
		// Tell them they did something wrong
		else {
			error = "The email and/or password you provided is incorrect.";
		}
	}
	// Otherwise show them the login form
%>

<h1>Login</h1>
<div class="error"><% out.print(error); %></div>
<form action="" method="post">
		<p><label>Email:</label> <input type="text" name="email" placeholder='example@email.com'></p>
		<p><label>Password:</label> <input type="text" name="password" placeholder='**********'></p>
		<button>Submit</button>
</form>

<%@ include file="layouts/footer.jsp" %>
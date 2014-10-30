<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@ include file="../layouts/header.jsp" %>
<%
	
	// Otherwise show them the login form
%>
			<h1>Login</h1>
			<div class="error"><% //out.print(error); %></div>
			<form action="" method="post">
				<p><label>Email:</label> <input type="text" name="email" placeholder='example@email.com'>
					<span class="error">Email address must not be blank.</span>
			<span class="error">Email address must be less than 50 characters.</span>
			<span class="error">Email address must be in correct format <i>example: email@address.com</i></span>
				</p>
				<p><label>Password:</label> <input type="password" name="password" placeholder='**********'>
					<span class="error">Password must be at least 8 characters and no more than 20 characters.</span>
					<span class="error">Password must not be blank.</span>
				</p>
				<input type="submit" name="submit" value="Submit"/>
			</form>
			
<%@ include file="../layouts/footer.jsp" %>
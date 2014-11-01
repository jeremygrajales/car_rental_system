<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@ include file="../layouts/header.jsp" %>
<%
	String error = Session.getString("error", "");
%>
			<h1>Login</h1>
			<div class="error"><% if(!error.isEmpty()) out.print(error); %></div>
			<form action="" method="post">
				<p>
					<label>Email:</label>
					<input type="text" name="email" placeholder='example@email.com'>
				</p>
				<p>
					<label>Password:</label>
					<input type="password" name="password" placeholder='**********'>
				</p>
				<input type="submit" name="submit" value="Submit"/>
			</form>
			
<%@ include file="../layouts/footer.jsp" %>
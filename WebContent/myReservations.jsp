<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@include file="layouts/header2.jsp" %>

	<form>
		<fieldset>
			<h1 style="text-align: center;">My Reservations</h1>
			<p>Current Reservations for "Customer Name"</p>
			<table>
				<tr>
					<th>Name</th>
					<th>Pick Up</th>
					<th>Return</th>
					<th>Vehicle</th>
					<th>Cancel</th>
				</tr>
			
			<%
			
			if(session.getAttribute("currentUser") != null) {
			
				// Fetch currentUser from session
				User currentUser = (User)(session.getAttribute("currentUser"));		
		
				// Fetch currentUser's reservations
				ArrayList<Reservation> reservations = currentUser.getReservations();
				
				// Output all reservations
				for(Reservation reservation : reservations) {
					
				}
			}
			
			%>
				
				<tr>
					<td>customer1</td>
					<td>pick-up date</td>
					<td>return date</td>
					<td>vehicle type</td>
					<td>
						<button type="button">Cancel</button>
					</td>
				</tr>
				<tr>
					<td>customer2</td>
					<td>pick-up date</td>
					<td>return date</td>
					<td>vehicle type</td>
					<td>
						<button type="button">Cancel</button>
					</td>
				</tr>
				<tr>
					<td>customer3</td>
					<td>pick-up date</td>
					<td>return date</td>
					<td>vehicle type</td>
					<td>
						<button type="button">Cancel</button>
					</td>
				</tr>

			</table>
			<button type="button">Submit</button>
		</fieldset>
	</form>

<%@include file="layouts/footer.jsp" %>
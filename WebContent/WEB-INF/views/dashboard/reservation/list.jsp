<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@ include file="../../layouts/header2.jsp"%>
<%
	// Get the Reservations from the controller
	ArrayList<Reservation> reservations = (ArrayList<Reservation>)session.getAttribute("reservations");
	if(reservations == null) reservations = new ArrayList<Reservation>();
	User currentUser = (User)session.getAttribute("currentUser");
				
%>
	<form action="cancel" method="post">
		<fieldset>
			<h1 style="text-align: center;">My Reservations</h1>
			<p>Current Reservations for <% out.print(currentUser.nameFirst + currentUser.nameLast);  %></p>
			<%
				if(reservations.size() == 0)
					out.print("<span class=\"error\">No reservations found.</span></br>");
				else {
			%>
			<table>
				<tr>
					<th>Name</th>
					<th>Pick Up</th>
					<th>Return</th>
					<th>Vehicle</th>
					<th>Cancel</th>
				</tr>
			<%
				// Output all reservations
				LinkedHashMap<Integer, String> vehicleTypes = Helper.getVehicleTypes();
				for(Reservation reservation : reservations) {
					out.println("<tr>");
					out.println("<td>" + currentUser.nameFirst + " " + currentUser.nameLast + "</td>");
					out.println("<td>" + Helper.timestampToDate(reservation.pickupTimestamp, "MM/dd/yyyy hh:mm aa") + "</td>");
					out.println("<td>" + Helper.timestampToDate(reservation.returnTimestamp, "MM/dd/yyyy hh:mm aa") + "</td>");
					out.println("<td>" + vehicleTypes.get(reservation.vehicleTypeId) + "</td>");
					out.println("<td><button name=\"id\" value=\"" + reservation.id + "\"/>Cancel</button></td>");
					out.println("</tr>");
				}
			%>
			</table>
			<%
				}
			%>		
		</fieldset>
	</form>

<%@include file="../../layouts/footer.jsp" %>
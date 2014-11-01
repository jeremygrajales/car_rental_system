<%@page import="java.text.DateFormat"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@include file="../../layouts/header2.jsp" %>
<% 
	Errors errors = (Errors)Session.getObject("errors", new Errors());
	// 
	String location = Session.getString("location", "");
	// pickup
	int p_month = Session.getInteger("p_month", -1);
	int p_day = Session.getInteger("p_day", -1);
	int p_year = Session.getInteger("p_year", -1);
	int p_time = Session.getInteger("p_time", -1);
	String p_Am = Session.getString("p_am_pm", "");
	// return
	int r_month = Session.getInteger("r_month", -1);
	int r_day = Session.getInteger("r_day", -1);
	int r_year = Session.getInteger("r_year", -1);
	int r_time = Session.getInteger("r_time", -1);
	String r_Am = Session.getString("r_am_pm", "");
	// vehicle type
	int vehicle = Session.getInteger("vehicle", -1);
	// renter's age
	int rentersAge = Session.getInteger("renters_age", -1);
	
	
	
%>
<!--Since I have drop down list, no need to check if option selected??-->
  <form method="post" action="">
  <% if(!errors.isEmpty()) { %><div class="error">There were one or more errors in your submission.</div><% } %>
  <% if(errors.contains("date_conflict")) { %><span class="error">The pickup date/time must be before the return date/time.</span><% } %>
    <fieldset>
		<h1 style="text-align: center;">Make Reservation</h1>
		<p>
			<label>Pick up Location:</label>
			<input name="location" type="text" size="50" maxlength="50" placeholder='City, State, Zip Code' required value="<% out.print(location); %>">
			<div class="errors">
				<% if(errors.contains("location")) { %><span class="error">You must enter the city, state and Zip Code</span><% } %>
			</div>
		</p>
		<p>
			<label>Pick up Date and Time:</label>
			<select name="p_month">
				<option value="-">--Month--</option>
				<% 
					LinkedHashMap<Integer, String> months = Helper.getMonths();
					for(Map.Entry<Integer, String> month : months.entrySet()) {
						out.println(String.format("<option value=\"%02d\""+((p_month == month.getKey())?" selected=\"selected\"":"")+">%s</option>", month.getKey(), month.getValue()));		
					}
				%>
			</select>
			<select name="p_day">
				<option value="-">--Day--</option>
				<% 
					for(int i = 1; i <= 31; i++) {
						out.println(String.format("<option value=\"%02d\""+((p_day == i)?" selected=\"selected\"":"")+">%d</option>", i, i));		
					}
				%>
			</select>
			<select name="p_year">
				<option value="-">--Year--</option>
				<% 
					for(int i = 2014; i <= 2017; i++) {
						out.println(String.format("<option value=\"%d\""+((p_year == i)?" selected=\"selected\"":"")+">%d</option>", i, i));		
					}
				%>
			</select>
			<select name="p_time">
			<option value="-">--Time--</option>
				<% 
					for(int i = 1; i <= 12; i++) {
						out.println(String.format("<option value=\"%02d\""+((p_time == i)?" selected=\"selected\"":"")+">%02d:00</option>", i, i));		
					}
				%>
			</select>

			<select name="p_am_pm">
				<option value="AM"<% out.print((p_Am.equals("AM"))?" selected=\"selected\"":""); %>>a.m.</option>
				<option value="PM"<% out.print((p_Am.equals("PM"))?" selected=\"selected\"":""); %>>p.m.</option>
			</select>
			<div class="errors">
				<% if(errors.contains("pickup")) { %><span class="error"> You must select a Month, day, year and a valid time</span><% } %>
			</div>
		</p>
		<p>
			<label>Return Date & Time:</label>
			<select name="r_month">
				<option value="-">--Month--</option>
				<% 
					for(Map.Entry<Integer, String> month : months.entrySet()) {
						out.println(String.format("<option value=\"%02d\""+((r_month == month.getKey())?" selected=\"selected\"":"")+">%s</option>", month.getKey(), month.getValue()));		
					}
				%>
			</select>

			<select name="r_day">
				<option value="-">--Day--</option>
				<% 
					for(int i = 1; i <= 31; i++) {
						out.println(String.format("<option value=\"%02d\""+((r_day == i)?" selected=\"selected\"":"")+">%d</option>", i, i));		
					}
				%>
			</select>

			<select name="r_year">
				<option value="-">--Year--</option>
				<% 
					for(int i = 2014; i <= 2017; i++) {
						out.println(String.format("<option value=\"%d\""+((r_year == i)?" selected=\"selected\"":"")+">%d</option>", i, i));		
					}
				%>

			</select>
			<select name="r_time">
				<option value="-">--Time--</option>
				<% 
					for(int i = 1; i <= 12; i++) {
						out.println(String.format("<option value=\"%02d\""+((r_time == i)?" selected=\"selected\"":"")+">%02d:00</option>", i, i));		
					}
				%>
			</select>

			<select name="r_am_pm">
				<option value="AM"<% out.print((r_Am.equals("AM"))?" selected=\"selected\"":""); %>>a.m.</option>
				<option value="PM"<% out.print((r_Am.equals("PM"))?" selected=\"selected\"":""); %>>p.m.</option>
			</select>
			<div class="errors">
				<% if(errors.contains("return")) { %><span class="error">You must select a Month, day, year and a valid time</span><% } %>
			</div>
		</p>

		<p>
			<label>Vehicle Type:</label>
			<select name="vehicle">
				<option value="-1">--Vehicle Type--</option>
					<% 
						LinkedHashMap<Integer, String> vehicleTypes = Helper.getVehicleTypes();
						if(vehicleTypes != null)
							for(Map.Entry<Integer, String> vehicleType : vehicleTypes.entrySet()) {
								out.println(String.format("<option value=\"%d\""+((vehicle == vehicleType.getKey())?" selected=\"selected\"":"")+">%s</option>", vehicleType.getKey(), vehicleType.getValue()));		
							}
					%>
			</select>
			<div class="errors">
				<% if(errors.contains("vehicle")) { %><span class="error">You must select a vehicle type</span><% } %>
			</div>
		</p>

		<p>
			<label>Renter's Age:</label>
			<select name="renters_age">
				<option value="">--Age--</option>
				<option value="25"<% out.print((rentersAge == 25)?" selected=\"selected\"":""); %>>25+</option>
				<% 
					for(int i = 24; i >= 18; i--) {
						out.println(String.format("<option value=\"%02d\""+((rentersAge == i)?" selected=\"selected\"":"")+">%02d</option>", i, i));		
					}
				%>

			</select>
			<div class="errors">
				<% if(errors.contains("renters_age")) { %><span class="error">You must select an age</span><% } %>
			</div>
		</p>
		<input type="submit" name="submit" value="Submit" />
	</fieldset>
</form>


<%@include file="../../layouts/footer.jsp" %>
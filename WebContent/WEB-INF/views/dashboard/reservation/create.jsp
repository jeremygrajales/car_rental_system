<%@page import="java.text.DateFormat"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@include file="../../layouts/header2.jsp" %>
<% 
	
%>
<!--Since I have drop down list, no need to check if option selected??-->
  <form method="post" action="">
	<fieldset>
		<h1 style="text-align: center;">Make Reservation</h1>
		<p>
			<label>Pick up Location:</label>
			<input name="location" type="text" size="50" maxlength="50" placeholder='City, State, Zip Code' required>
			<span class="error">You must enter the city, state and Zip Code</span>
		</p>

		<p>
			<label>Pick up Date and Time:</label>
			<select name="p_month">
				<option value="-">--Month--</option>
				<% 
					LinkedHashMap<Integer, String> months = Helper.getMonths();
					for(Map.Entry<Integer, String> month : months.entrySet()) {
						out.println(String.format("<option value=\"%02d\">%s</option>", month.getKey(), month.getValue()));		
					}
				%>
			</select>

			<select name="p_day">
				<option value="-">--Day--</option>
				<% 
					for(int i = 1; i <= 31; i++) {
						out.println(String.format("<option value=\"%02d\">%02d</option>", i, i));		
					}
				%>
			</select>

			<select name="p_year">
				<option value="-">--Year--</option>
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>
			</select>
			<select name="p_time">
			<option value="-">--Time--</option>
				<% 
					for(int i = 1; i <= 12; i++) {
						out.println(String.format("<option value=\"%02d:00\">%02d:00</option>", i, i));		
					}
				%>
			</select>

			<select name="p_am_pm">
				<option value="AM">a.m.</option>
				<option value="PM">p.m.</option>
			</select>
			<span class="error"> You must select a Month, day, year and a valid time</span> &nbsp; &nbsp; &nbsp; &nbsp;
		</p>

		<p>
			<label>Return Date & Time:</label>
			<select name="r_month">
				<option value="-">--Month--</option>
				<% 
					for(Map.Entry<Integer, String> month : months.entrySet()) {
						out.println(String.format("<option value=\"%02d\">%s</option>", month.getKey(), month.getValue()));		
					}
				%>
			</select>

			<select name="r_day">
				<option value="-">--Day--</option>
				<% 
					for(int i = 1; i <= 31; i++) {
						out.println(String.format("<option value=\"%02d\">%d</option>", i, i));		
					}
				%>
			</select>

			<select name="r_year">
				<option value="-">--Year--</option>
				<option value="2014">2014</option>
				<option value="2015">2015</option>
				<option value="2016">2016</option>
				<option value="2017">2017</option>

			</select>
			<select name="r_time">
				<option value="-">--Time--</option>
				<% 
					for(int i = 1; i <= 12; i++) {
						out.println(String.format("<option value=\"%02d:00\">%02d:00</option>", i, i));		
					}
				%>
			</select>

			<select name="r_am_pm">
				<option value="AM">a.m.</option>
				<option value="PM">p.m.</option>
			</select>
			<span class="error">You must select a Month, day, year and a valid time</span>
		</p>

		<p>
			<label>Vehicle Type:</label>
			<select name="vehicle">
				<option value="-1">--Vehicle Type--</option>
					<% 
						LinkedHashMap<Integer, String> vehicleTypes = Helper.getVehicleTypes();
						if(vehicleTypes != null)
							for(Map.Entry<Integer, String> vehicleType : vehicleTypes.entrySet()) {
								out.println(String.format("<option value=\"%02d\">%s</option>", vehicleType.getKey(), vehicleType.getValue()));		
							}
					%>
			</select>
			<% %><span class="error">You must select a vehicle type</span>
		</p>

		<p>
			<label>Renter's Age:</label>
			<select name="renter_age">
				<option value="-">--Age--</option>
				<option value="25">25 +</option>
				<option value="24">24</option>
				<option value="23">23</option>
				<option value="22">22</option>
				<option value="21">21</option>
				<option value="20">20</option>
				<option value="19">19</option>
				<option value="18">18</option>

			</select>
			<span style="float:right" class="error">You must select an age</span>
		</p>

		<input type="submit" name="submit" value="Submit" />
	</fieldset>
</form>


<%@include file="../../layouts/footer.jsp" %>
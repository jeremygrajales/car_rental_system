<%@page import="java.text.DateFormat"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ page import="car_rental_system.*" %>
<%@include file="layouts/header2.jsp" %>

<% 
   
	String location = request.getParameter("location");
   //pick up
	String puMonth = request.getParameter("pu_month");
	String puDay = request.getParameter("pu_day");
	String puYear = request.getParameter("pu_year");
	String puDate = String.format("%s-%s-%s", puYear, puMonth, puDay);
	String puTime = request.getParameter("pu_time");
	String puAm = request.getParameter("pu_am_pm");
	String pickTime = String.format("%s %s", puTime, puAm);
	
	//return
	String rMonth = request.getParameter("r_month");
	String rDay = request.getParameter("r_day");
	String rYear = request.getParameter("r_year");
	String rDate = String.format("%s-%s-%s", rYear, rMonth, rDay);
	String rTime = request.getParameter("r_time");
	String rAm = request.getParameter("r_am_pm");
	String retTime = String.format("%s %s", rTime, rAm);
	
	String vehicle = request.getParameter("vehicle");
	String age = request.getParameter("renter_age");
	
	ArrayList<String> errors = new ArrayList<String>();
	
	if(request.getParameter("submit") != null)
	{
		//and no errors found //store reservation in database
	}
	
	if(!errors)
	{
		Date pudate = Date.valueOf(puDate);
		Date rdate = Date.valueOf(rDate);
		Reservation reservation = Reservation.create(location, pudate, pickTime, rdate, retTime, vehicle);
	}

%>


  <form method="post" action="">
      <fieldset>
		<h1 style="text-align: center;">Make Reservation</h1>
		<p><label>Pick up Location:</label> <input name="location" type="text" size ="50" maxlength="50" placeholder='City, State, Zip Code'>
		<span class="error">You must enter the city, state and Zip Code</span></p>
		
		<p><label>Pick up Date and Time:</label> <select name="pu_month"><option value="null">Month</option>
														<option value="January">January</option>
														<option value="Februrary">February</option>
														<option value="March">March</option>
														<option value="April">April</option>
														<option value="May">May</option>
														<option value="June">June</option>
														<option value="July">July</option>
														<option value="August">August</option>
														<option value="September">September</option>
														<option value="October">October</option>
														<option value="November">November</option>
														<option value="December">December</option>
													</select>
												
												
												
												<select name="pu_day"><option>Day</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
														<option value="24">24</option>
														<option value="25">25</option>
														<option value="26">26</option>
														<option value="27">27</option>
														<option value="28">28</option>
														<option value="29">29</option>
														<option value="30">30</option>
														<option value="31">31</option>
												</select>
												
												
												<select name="pu_year"><option>Year</option>
													    <option value="2014">2014</option>
														<option value="2015">2015</option>
														<option value="2016">2016</option>
														<option value="2017">2017</option>
												</select>
												<select name="pu_time"><option>Time</option>
																											<option value="1">1:00</option>
														<option value="2">2:00</option>
														<option value="3">3:00</option>
														<option value="4">4:00</option>
														<option value="5">5:00</option>
														<option value="6">6:00</option>
														<option value="7">7:00</option>
														<option value="8">8:00</option>
														<option value="9">9:00</option>
														<option value="10">10:00</option>
														<option value="11">11:00</option>
														<option value="12">12:00</option>
														
												</select>
												
												<select name="pu_am_pm"><option value="am">a.m.</option>
														<option value="pm">p.m.</option></select>
														<span class="error"> You must select a Month, day, year and a valid time</span>
														
														&nbsp;
														&nbsp;
														&nbsp;
														&nbsp;

														</p>
													
														
														
		<p><label>Return Date & Time:</label>
											 <select name="r_month"><option value="null">Month</option>
														<option value="January">January</option>
														<option value="Februrary">February</option>
														<option value="March">March</option>
														<option value="April">April</option>
														<option value="May">May</option>
														<option value="June">June</option>
														<option value="July">July</option>
														<option value="August">August</option>
														<option value="September">September</option>
														<option value="October">October</option>
														<option value="November">November</option>
														<option value="December">December</option>
													</select>
												
												
												
												<select name="r_day"><option>Day</option>
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
														<option value="17">17</option>
														<option value="18">18</option>
														<option value="19">19</option>
														<option value="20">20</option>
														<option value="21">21</option>
														<option value="22">22</option>
														<option value="23">23</option>
														<option value="24">24</option>
														<option value="25">25</option>
														<option value="26">26</option>
														<option value="27">27</option>
														<option value="28">28</option>
														<option value="29">29</option>
														<option value="30">30</option>
														<option value="31">31</option>
												</select>
												
												
												<select name="r_year"><option>Year</option>
														<option value="2014">2014</option>
														<option value="2015">2015</option>
														<option value="2016">2016</option>
														<option value="2017">2017</option>
												
												</select>
												<select name ="r_time"><option value="null">Time</option>
														<option value="1">1:00</option>
														<option value="2">2:00</option>
														<option value="3">3:00</option>
														<option value="4">4:00</option>
														<option value="5">5:00</option>
														<option value="6">6:00</option>
														<option value="7">7:00</option>
														<option value="8">8:00</option>
														<option value="9">9:00</option>
														<option value="10">10:00</option>
														<option value="11">11:00</option>
														<option value="12">12:00</option>
														
												</select>
												
												<select name="r_am_pm"><option value="am">a.m.</option>
														<option value="pm">p.m.</option></select>
														<span class="error">You must select a Month, day, year and a valid time</span>
														</p>
		
		
		
		
		
		
		
		<p><label>Vehicle Type:</label> <select name="vehicle"><option value="null">Choose Type</option>
												<option value="Compact">Compact</option>
												<option value="Full Size">Full size</option>
												<option value="SUV">SUV</option>
												<option value="Van">Van</option>
												
												</select>
												<span class="error">You must select a vehicle type</span>
												</p>
												
												
		<p><label>Renter's Age:</label> <select name="renter_age"><option value="null">Age</option>
												<option value="25">25 +</option>
												<option value="24">24</option>
												<option value="23">23</option>
												<option value="22">22</option>
												<option value="21">21</option>
												<option value="20">20</option>
												<option value="19">19</option>
												<option value="18">18</option>
		
		
		</select>
		<span style="float:right"class="error">You must select an age</span>
		</p>
		
		<!--<p><label>Phone:</label> <input type="text" name="phone" style="width: 30px;"> - <input type="text" name="phone" style="width: 30px;"> - <input type="text" name="phone" style="width: 50px;"></p>
		<p><label>Select Doctor:</label>
			<select name="doctor">
				<option>Dr. A</option>
				<option>Dr. B</option>
				<option>Dr. C</option>
			</select></p>
		<p><label>Select Date:</label> <input type="text" name="phone" style="width: 30px;"> / <input type="text" name="phone" style="width: 30px;"> / <input type="text" name="phone" style="width: 50px;"></p>
		<p><label>Select Time:</label>
			<select name="time">
				<option>8:00 AM</option>
				<option>9:00 AM</option>
				<option>10:00 AM</option>
				<option>11:00 AM</option>
				<option>12:00 PM</option>
				<option>1:00 PM</option>
				<option>2:00 PM</option>
				<option>3:00 PM</option>
				<option>4:00 PM</option>				
			</select></p>-->
			<input type="submit" name="submit" value="Submit"/>
          </fieldset>
  </form> 


<%@include file="layouts/footer.jsp" %>
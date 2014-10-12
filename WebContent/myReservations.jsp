<%@include file="layouts/header.jsp" %>

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
		  <tr>
			<td>customer1</td>
			<td>pick-up date</td>
			<td>return date</td>
			<td>vehicle type</td>
			<td><button type="button">Cancel</button></td>
		  </tr>
		  <tr>
			<td>customer2</td>
			<td>pick-up date</td>
			<td>return date</td>
			<td>vehicle type</td>
			<td><button type="button">Cancel</button></td>
		  </tr>
		  <tr>
			<td>customer3</td>
			<td>pick-up date</td>
			<td>return date</td>
			<td>vehicle type</td>
			<td><button type="button">Cancel</button></td>
		  </tr>
		  
		</table> 
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
			<button type="button">Submit</button>
          </fieldset>
  </form> 



<%@include file="layouts/footer.jsp" %>
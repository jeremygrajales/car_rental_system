package car_rental_system;

import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReservationController extends Controller {

    public ReservationController() {
    	super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		if(isPath("/dashboard/reservation/create", request)) {
			makeView("/dashboard/reservation/create");
		}
		else if(isPath("/dashboard/reservation/show", request)) {
			makeView("/dashboard/reservation/show");
		}
		
		else if(isPath("/dashboard/reservation/list", request)) {
			// Fetch currentUser's reservations
			User currentUser = (User)request.getSession().getAttribute("currentUser");
			if(currentUser != null) {
				request.getSession().setAttribute("reservations", currentUser.getReservations());
			}
			makeView("/dashboard/reservation/list");
		}
		
		else if(isPath("/dashboard/reservation/create/thankyou", request)) {
			makeView("/dashboard/reservation/create-thankyou");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Get current User
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		
		if(isPath("/dashboard/reservation/create", request)) {

			// Fetch inputs
			String location = request.getParameter("location");
		   	// pick up
			String _pMonth = request.getParameter("p_month");
			String _pDay = request.getParameter("p_day");
			String _pYear = request.getParameter("p_year");
			String pDate = String.format("%s-%s-%s", _pYear, _pMonth, _pDay);
			String _pTime = request.getParameter("p_time");
			String _pAm = request.getParameter("p_am_pm");
			String pConcatTime = String.format("%s %s", _pTime, _pAm);
			// return
			String _rMonth = request.getParameter("r_month");
			String _rDay = request.getParameter("r_day");
			String _rYear = request.getParameter("r_year");
			String rDate = String.format("%s-%s-%s", _rYear, _rMonth, _rDay);
			String _rTime = request.getParameter("r_time");
			String _rAm = request.getParameter("r_am_pm");
			String rConcatTime = String.format("%s %s", _rTime, _rAm);
			// vehicle and driver info
			String vehicleTypeId = request.getParameter("vehicle");
			String rentersAge = request.getParameter("renters_age");
			
			// Input validation
			Errors errors = new Errors();
			// location
			errors.add(!FormValidator.validateNotBlank(location, true) ? new String[]{"location_blank", "location"} : null);
			
			// pickup
			int year = 0, month = 0, day = 0;
			try {
				year = Integer.parseInt(_pYear);
				month = Integer.parseInt(_pMonth);
				day = Integer.parseInt(_pDay);
			}
			catch(NumberFormatException e) { errors.add("pickup"); }
			errors.add(!FormValidator.validateDate(year, month, day) ? "pickup" : null);
			if(FormValidator.validateInteger(_pTime)) {
				int pTime = Integer.parseInt(_pTime);
				if(pTime > 12 || pTime < 1)
					errors.add("pickup");
			}
			// return
			try {
				year = Integer.parseInt(_rYear);
				month = Integer.parseInt(_rMonth);
				day = Integer.parseInt(_rDay);
			}
			catch(NumberFormatException e) { errors.add("return"); }
			errors.add(!FormValidator.validateDate(year, month, day) ? "return" : null);
			if(FormValidator.validateInteger(_rTime)) {
				int rTime = Integer.parseInt(_rTime);
				if(rTime > 12 || rTime < 1)
					errors.add("return");
			}
			// date conflict check
			SimpleDateFormat df = new SimpleDateFormat();
			df.applyPattern("yyyy-MM-dd");
			try {
				Date _pickup = df.parse(pDate); 
				Date _return = df.parse(rDate);
				errors.add(!FormValidator.validateDateBefore(_pickup, _return) ? "date_conflict" : null);
			} catch (ParseException e) {
				
			} 
			// vehicle type
			int vehicle = -1;
			try {
				vehicle = Integer.parseInt(vehicleTypeId);
			}
			catch(Exception e) {
				errors.add("vehicle");	
			}
			errors.add(!Helper.getVehicleTypes().containsKey(vehicle) ? "vehicle": null);
			// renter's age 
			errors.add(!FormValidator.validateInteger(rentersAge) ? "renters_age" : null);
			
			if(errors.isEmpty()) {
				//store reservation in database
				Reservation reservation = Reservation.create(location, pDate, pConcatTime, rDate, rConcatTime, currentUser.id, vehicle);
				
				// Redirect to thank you page
				Response.redirect(request.getContextPath() + "/dashboard/reservation/create/thankyou");
			}
			else {
				// Flash errors and parameters into session
				Session.flash("errors", errors);
				for(Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
					Session.flash(entry.getKey(), entry.getValue()[0]);
				}
				
				// Redirect to reservation create page
				Response.redirect(request.getContextPath() + "/dashboard/reservation/create");
			}
		}
		else if(isPath("/dashboard/reservation/cancel", request)) {
			int id;
			try {
				id = Integer.parseInt(request.getParameter("id"));
				// If this Reservation belongs to the current user
				System.out.println("Reservation is being cancelled.");
				if((Reservation.getById(id).customerId == currentUser.id)) {
					// Cancel the reservation
					Reservation.cancel(id);
					// Redirect to the Reservations list
					try {
						response.sendRedirect(request.getContextPath() + "/dashboard/reservation/list");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			catch(NumberFormatException e) {
				session.setAttribute("error", "Could not cancel reservation: " + request.getParameter("id"));
			}
		}
			
		else {
			try {
				response.sendRedirect(request.getContextPath() + "/user/login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

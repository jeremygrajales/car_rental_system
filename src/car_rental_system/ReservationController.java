package car_rental_system;

import java.io.IOException;
import java.text.DateFormat;

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
		
		else if(isPath("/dashboard/reservation/thankyou", request)) {
			makeView("/dashboard/reservation/create-thankyou");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		// Get current User
		HttpSession session = request.getSession();
		User currentUser = (User)session.getAttribute("currentUser");
		
		if(isPath("/dashboard/reservation/create", request)) {

			String location = request.getParameter("location");
		   	//pick up
			String _pMonth = request.getParameter("p_month");
			String _pDay = request.getParameter("p_day");
			String _pYear = request.getParameter("p_year");
			String pDate = String.format("%s-%s-%s", _pYear, _pMonth, _pDay);
			String _pTime = request.getParameter("p_time");
			String _pAm = request.getParameter("p_am_pm");
			String pConcatTime = String.format("%s %s", _pTime, _pAm);
			 
			//return
			String _rMonth = request.getParameter("r_month");
			String _rDay = request.getParameter("r_day");
			String _rYear = request.getParameter("r_year");
			String rDate = String.format("%s-%s-%s", _rYear, _rMonth, _rDay);
			String _rTime = request.getParameter("r_time");
			String _rAm = request.getParameter("r_am_pm");
			String rConcatTime = String.format("%s %s", _rTime, _rAm);
			int vehicleTypeId = Integer.parseInt(request.getParameter("vehicle"));

			String age = request.getParameter("renter_age");
			
			//ArrayList<String> errors = new ArrayList<String>();
			if(request.getParameter("submit") != null)
			{
				//and no errors found //store reservation in database
				DateFormat df = DateFormat.getDateInstance();
				Reservation reservation = Reservation.create(location, pDate, pConcatTime, rDate, rConcatTime, currentUser.id, vehicleTypeId);
			}
			try {
				response.sendRedirect(request.getContextPath() + "/dashboard/reservation/create");
			} catch (IOException e) {
				e.printStackTrace();
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

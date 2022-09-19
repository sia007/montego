package montego_bay_resort.dispatcher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import montego_bay_resort.business.ResForm;
import montego_bay_resort.business.RoomBean;
import montego_bay_resort.database.DBUtil;
import montego_bay_resort.util.Util;

/**
 * Servlet implementation class ResultsServlet
 */
@WebServlet("/ResultsServlet")
public class ResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResultsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ResForm currentForm;
		List<RoomBean> availableRooms;
		final Object lock = request.getSession().getId().intern();
		synchronized (lock) {
			currentForm = (ResForm) session.getAttribute("myReservation");
		}
		String errorMessage = "";
		if (currentForm == null) {
			currentForm = new ResForm();
			session.setAttribute("myReservation", currentForm);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else {
			if (request.getParameter("checkInDate") != null
					&& request.getParameter("checkOutDate") != null) {
				if (Util.validateDate(request.getParameter("checkInDate"))
						&& Util.validateDate(
								request.getParameter("checkOutDate"))) {
					try {
						Date tempCheckIn = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("checkInDate"));
						Date tempCheckOut = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("checkOutDate"));
						if(tempCheckOut.after(tempCheckIn)) {
							currentForm.setCheckin(request.getParameter("checkInDate"));
							currentForm
									.setCheckout(request.getParameter("checkOutDate"));
						} else {
							errorMessage = "Your checkout date is before or equal to the checkin date. Try Again";
						}
					} catch (ParseException e) {
						errorMessage = "Unable to Parse Dates: " + e.toString();
					}
				} else {
					errorMessage = "One or both of the dates provided is invalid";
				}
			} else {
				errorMessage = "One or both of the dates provided is missing";
			}

//			if (request.getParameter("numOfGuest") != null) {
//				int guestsFound = Util.validateNumOfGuest(request.getParameter("numOfGuest"));
//				if (guestsFound > 0 || guestsFound > 10) {
//					currentForm.setNumOfGuest(guestsFound);
//				} else {
//					errorMessage = "The number of guests is invalid";
//				}
//			} else {
//				errorMessage = "Number of guest was found to be null. Please try again.";
//			}

			if (!errorMessage.trim().isEmpty()) {
				request.setAttribute("errorMessage", errorMessage);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			} else {
				availableRooms = new ArrayList<RoomBean>();
				try {
					// get a connection
					try {
						Class.forName("com.mysql.jdbc.Driver");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					String dbURL = "jdbc:mysql://dpeng7-dev-mysql.mysql.database.azure.com:3306/montego?autoReconnect=true";
					String username = "mod7user605682";
					String password = "Devtestforclass$";

					Connection connection = DriverManager.getConnection(dbURL,
							username, password);

					ResultSet searchReservations = DBUtil.getRoomsForSearch(
							connection, currentForm.getCheckin(),
							currentForm.getCheckout());
					while (searchReservations.next()) {
						RoomBean temp = new RoomBean(currentForm.getCheckin(),
								currentForm.getCheckout(),
								searchReservations.getString(1),
								searchReservations.getString(2),
								searchReservations.getString(3),
								searchReservations.getString(4),
								searchReservations.getString(5),
								searchReservations.getString(6),
								searchReservations.getString(7));
						availableRooms.add(temp);
//						System.out.println("Found: " + searchReservations.getString(1));
					}
					connection.close();
				} catch (SQLException e) {
					errorMessage = e.toString();
					for (Throwable t : e)
						t.printStackTrace();
				}
				currentForm.setSearchResults(availableRooms);
				RequestDispatcher dispatcher = getServletContext()
						.getRequestDispatcher("/results.jsp");
				dispatcher.forward(request, response);
			}

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

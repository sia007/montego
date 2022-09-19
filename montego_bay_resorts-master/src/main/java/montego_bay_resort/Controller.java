package montego_bay_resort;

import java.io.*;

import java.net.URL;

import java.util.UUID;

import montego_bay_resort.business.ResForm;
import montego_bay_resort.business.RoomBean;
import montego_bay_resort.business.room;
import montego_bay_resort.business.room.*;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.sql.DriverManager;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 * 
	 */
	// getting response from the HTML form
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ResForm currentForm;
		List<RoomBean> availableRooms;
		final Object lock = request.getSession().getId().intern();
		synchronized (lock) {
			currentForm = (ResForm) session.getAttribute("myReservation");

		}
		String action_room = request.getParameter("roomId");

		ResForm form = (ResForm) session.getAttribute("myReservation");

		room r1 = (room) session.getAttribute("room");

		if (r1 == null) {
			room r4 = new room(request);
			r1 = r4;

			ArrayList<room> list = new ArrayList<room>();
			r1.setRoomList(list);

		}
		if (r1 != null) {
			String checkIn = form.getCheckin();
			String checkOut = form.getCheckout();

			if ((checkIn != r1.getCheckin()) || (checkOut != r1.getCheckout())) {
				room r4 = new room(request);
				r1 = r4;
				ArrayList<room> list = new ArrayList<room>();
				r1.setRoomList(list);
			}
		}

		String checkIn = form.getCheckin();
		String checkOut = form.getCheckout();
		int numGuest = form.getNumOfGuest();

		r1.setCheckin(checkIn);
		r1.setCheckout(checkOut);
		r1.setNumOfGuest(numGuest);
		if (action_room.length() >= 1) {
			room r2 = new room(request);

			r2.setRoomId(action_room);
			try {
				System.out.println("Loading driver...");
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Driver loaded!");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Cannot find the driver in the classpath!", e);
			}

			try {

				String dbURL = "jdbc:mysql://dpeng7-dev-mysql.mysql.database.azure.com:3306/montego?autoReconnect=true";
				String username = "mod7user605682";
				String password = "Devtestforclass$";

				Connection connection = DriverManager.getConnection(dbURL, username, password);

				Statement statement2 = connection.createStatement();
				String q_usr = "select * from rooms where roomId=" + "'" + action_room + "'";
				ResultSet result_usr = statement2.executeQuery(q_usr);

				while (result_usr.next()) {
					String roomNumber = result_usr.getString("roomNumber");
					String roomSize = result_usr.getString("roomSize");
					String roomFacing = result_usr.getString("roomFacing");
					String roomBasePrice = result_usr.getString("roomBasePrice");
					String roomOccupancy = result_usr.getString("roomOccupancy");
					String roomType = result_usr.getString("roomType");

					r2.setRoomBasePrice(Integer.valueOf(roomBasePrice));
					r2.setRoomOccupancy(Integer.valueOf(roomOccupancy));
					r2.setRoomFacing(roomFacing);
					r2.setRoomType(roomType);
					r2.setRoomNumber(Integer.valueOf(roomNumber));
					r2.setRoomSize(Integer.valueOf(roomSize));

				}
				r1.addRoom(r2);
				int totalcost = r1.getTotalCost();
				r1.setTotalCost(totalcost);

				connection.close();

			} catch (SQLException sq) {
				System.out.println("error: " + sq.getMessage());
				;
			}

		}

		availableRooms = currentForm.getSearchResults();

		for (int i = 0; i < availableRooms.size(); i++) {
			String s = availableRooms.get(i).getRoomId();
			if (s.equals(action_room)) {

				availableRooms.remove(i);

			}

		}

		currentForm.setSearchResults(availableRooms);
		session.setAttribute("myReservation", currentForm);

		session.setAttribute("room", (Object) r1);
		RequestDispatcher dispatcher = this.getServletConfig().getServletContext().getRequestDispatcher("/cart.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		ServletContext servletContext = getServletContext();
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");

		final HttpSession session = request.getSession();
		if (action.equals("checkout")) {
			room r1 = (room) session.getAttribute("room");
			session.setAttribute("room", (Object) r1);

			RequestDispatcher dispatcher = this.getServletConfig().getServletContext()
					.getRequestDispatcher("/checkout.jsp");
			dispatcher.forward(request, response);
		}
		if (action.equals("confirm")) {
			UUID uuid = UUID.randomUUID();
			String uuidAsString = uuid.toString();
			room r1 = (room) session.getAttribute("room");

			r1.setReservation(uuidAsString);
			String name = request.getParameter("name");
			String email = request.getParameter("email");

			r1.setEmail(email);
			r1.setName(name);

			session.setAttribute("room", (Object) r1);

			String s = "<!DOCTYPE html><html><link href=\"css/confirmation.css\" rel=\"stylesheet\"><body class=\"container-fluid\" style=\"height:30px;\"><nav class=\"navbar navbar-expand-lg navbar-light brand_blue\"></nav></body><head><meta charset=\"ISO-8859-1\"><title>Booking Confirmation</title></head><body><div class=\"container mt-5 mb-5\"><div class=\"row d-flex justify-content-center\"><div class=\"col-md-8\"><div class=\"card\"><div class=\"text-left logo p-2 px-5\"> <img src=https://i.ibb.co/d4t45S6/image-2022-05-08-155055984.png width=\"220\"> </div><div class=\"invoice p-5\"><h5>Your booking has been Confirmed!</h5> <span class=\"font-weight-bold d-block mt-4\">Hello,";

			s += " " + name + "!";
			s += "<br></span> <br> <span>You booking has been confirmed! These are the details about your reservation: </span><div class=\"payment border-top mt-3 mb-3 border-bottom table-responsive\"><br><br><table class=\"table table-borderless\" align=\"left\"<tbody><tr><td style=\"text-align:center; padding-right:30px; \"><div class=\"py-2\" > <span class=\"d-block text-muted\">Check-In Date</span> <span><br>";

			s += "<br>" + r1.getCheckin();
			s += "</span> </div></td><td style=\"text-align:center; padding-right:30px; \"><div class=\"py-2\"> <span class=\"d-block text-muted\">Check-Out Date</span> <span><br>";
			s += "<br>" + r1.getCheckout();
			s += "</span> </div></td><td style=\"text-align:center; padding-right:30px; \"><div class=\"py-2\"> <span class=\"d-block text-muted\">Confirmation No</span> <span><br>";
			s += "<br>" + r1.getReservation();
			s += "</span> </div></td></tr></tbody></table></div><br><br><div class=\"product border-bottom table-responsive\"><br><br><br><table class=\"table table-borderless\" align=\"left\">";

			int length = r1.getRoomList().size();
			for (int i = 0; i < length; i++) {
				s += "<tr><td width=\"20%\"> <img src=https://i.ibb.co/12jMsNw/hotel2.jpg width=\"90\"> </td><td width=\"60%\"> <span class=\"font-weight-bold\">1 x";
				s += " " + r1.getRoomList().get(i).getRoomType();
				s += "</span><div class=\"product-qty\"> <span class=\"d-block\">Occupancy: ";
				s += r1.getRoomList().get(i).getRoomOccupancy();
				s += "</td><td width=\"40%\"><div class=\"text-right\"> <span class=\"font-weight-bold\">";
				int j = r1.getRoomList().get(i).getRoomBasePrice();
				s += "$" + String.valueOf(j);
				s += ".00</span> </div></td></tr>";
				s += "<br>";

			}
			s += "</table></div><br><br><div class=\"row d-flex justify-content-end\"><div class=\"col-md-5\">";

			if (length > 1) {
				for (int i = 1; i < length; i++) {
					s += "<br><br><br><br><br>";

				}
			}

			s += "<br><br><br><table class=\"table table-borderless\" align=\"left\"><tbody class=\"totals\"><tr><td 	style= \"font-weight: bold;\"><div class=\"text-left\"> <span class=\"text-muted\">Subtotal: </span> </div></td><td 	style= \"font-weight: bold;\"><div class=\"text-right\"> <span>$";
			int k = r1.getTotalCost();
			s += String.valueOf(k);
			s += ".00</span> </div></td></tr><tr><td 	style= \"font-weight: bold;\"><div class=\"text-left\"> <span class=\"text-muted\"></span> </div></td><td 	style= \"font-weight: bold;\"><div class=\"text-right\"> <span class=\"text-success\"></span> </div></td></tr><tr class=\"border-top border-bottom\"><td 	style= \"font-weight: bold;\"><div class=\"text-left\"> <span class=\"font-weight-bold\">Total: </span> </div></td><td 	style= \"font-weight: bold;\"><div class=\"text-right\"> <span class=\"font-weight-bold\">$";
			s += String.valueOf(k);
			s += ".00</span> </div></td></tr></tbody></table></div></div><br><br><br><p>We hope you will have an enjoyable and memorable stay at Montego Bay!</p><p class=\"font-weight-bold mb-0\">Thanks for booking with us!</p> <span>Montego Bay Resort</span></div><div class=\"d-flex justify-content-between footer p-3\"> <span></span> <span></span> </div></div></div></div></div></body></html>";

			String from = "sahmedassignment9@gmail.com";
			String subject = "Reservation Confirmation - Montego Bay Resort";

			Mail mail = new Mail();

			try {
				mail.sendEmail(from, email, subject, s);

				try {

					String dbURL = "jdbc:mysql://dpeng7-dev-mysql.mysql.database.azure.com:3306/montego?autoReconnect=true";
					String username = "mod7user605682";
					String password = "Devtestforclass$";

					Connection connection = DriverManager.getConnection(dbURL, username, password);

					Statement statement = connection.createStatement();
					String query = "INSERT INTO user_info (id, name,email,checkIn,checkOut) VALUES (" + "'"
							+ uuidAsString + "'" + "," + "'" + name + "'" + "," + "'" + email + "'" + "," + "'"
							+ r1.getCheckin() + "'" + "," + "'" + r1.getCheckout() + "'" + ");";
					statement.executeUpdate(query);

					ArrayList<room> list2 = r1.getRoomList();

					for (int i = 0; i < list2.size(); i++) {

						String query3 = "INSERT INTO reservation (id, roomId) VALUES (" + "'" + uuidAsString + "'" + ","
								+ "'" + list2.get(i).getRoomId() + "'" + ");";

						Statement statement2 = connection.createStatement();
						statement2.executeUpdate(query3);
					}

					connection.close();

				} catch (SQLException sq) {
					System.out.println("error: " + sq.getMessage());
					;
				}

				RequestDispatcher dispatcher = this.getServletConfig().getServletContext()
						.getRequestDispatcher("/confirmation.jsp");
				dispatcher.forward(request, response);
				session.removeAttribute("room");

			} catch (MessagingException e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "The email provided was incorrect");

				RequestDispatcher dispatcher = this.getServletConfig().getServletContext()
						.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
			}

		}
		if (action.equals("remove")) {
			ResForm currentForm;
			List<RoomBean> availableRooms;
			final Object lock = request.getSession().getId().intern();
			synchronized (lock) {
				currentForm = (ResForm) session.getAttribute("myReservation");
			}
			final room r3 = (room) session.getAttribute("room");
			String remove = request.getParameter("remove");
			try {

				int i1 = Integer.parseInt(remove);
				availableRooms = currentForm.getSearchResults();
				RoomBean rb = new RoomBean();
				rb.setRoomBasePrice(String.valueOf(r3.getRoomList().get(i1).getRoomBasePrice()));
				rb.setRoomFacing(r3.getRoomList().get(i1).getRoomFacing());
				rb.setRoomId(r3.getRoomList().get(i1).getRoomId());
				rb.setRoomNumber(String.valueOf(r3.getRoomList().get(i1).getRoomNumber()));
				rb.setRoomOccupancy(String.valueOf(r3.getRoomList().get(i1).getRoomOccupancy()));
				rb.setRoomSize(String.valueOf(r3.getRoomList().get(i1).getRoomSize()));
				rb.setRoomType(String.valueOf(r3.getRoomList().get(i1).getRoomType()));

				availableRooms.add(0, rb);

				currentForm.setSearchResults(availableRooms);

				r3.removeRoom(i1);

			} catch (NumberFormatException e) {

			}

			ArrayList<room> room_l = r3.getRoomList();
			r3.setRoomList(room_l);
			int totalcost = r3.getTotalCost();
			r3.setTotalCost(totalcost);
			session.setAttribute("myReservation", (Object) currentForm);

			session.setAttribute("room", (Object) r3);

			RequestDispatcher dispatcher = this.getServletConfig().getServletContext()
					.getRequestDispatcher("/cart.jsp");
			dispatcher.forward(request, response);

		}

	}
}

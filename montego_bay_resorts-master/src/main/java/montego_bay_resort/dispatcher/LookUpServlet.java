package montego_bay_resort.dispatcher;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import montego_bay_resort.business.ResForm;
import montego_bay_resort.business.RoomBean;
import montego_bay_resort.database.DBUtil;
import montego_bay_resort.util.Util;

/**
 * Servlet implementation class LookUpServlet
 */
@WebServlet("/LookUpServlet")
public class LookUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LookUpServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String confirmNumber = request.getParameter("confirmNumber");
		String email = request.getParameter("email");
		String errorMessage = "";
		String nameFromDB = "";
		String emailFromDB = "";
		List<RoomBean> allRes = new ArrayList<RoomBean>();
		if (confirmNumber != null && email != null) {
			if (Util.validateUUID(confirmNumber) && Util.validateEmail(email)) {
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

					ResultSet details = DBUtil.getLookUpDetails(connection,
							confirmNumber, email);

					while (details.next()) {
						nameFromDB = details.getString(3);
						emailFromDB = details.getString(2);
						allRes.add(new RoomBean(details.getString(4),
								details.getString(5), details.getString(6),
								details.getString(7), details.getString(8),
								details.getString(9), details.getString(10),
								details.getString(11), details.getString(12)));
//						System.out.println(details.getString(4) + " "
//								+ details.getString(5) + " "
//								+ details.getString(6) + " "
//								+ details.getString(7) + " "
//								+ details.getString(8) + " "
//								+ details.getString(9) + " "
//								+ details.getString(10) + " "
//								+ details.getString(11) + " "
//								+ details.getString(12));
					}

					connection.close();
				} catch (SQLException e) {
					errorMessage = e.toString();
					for (Throwable t : e)
						t.printStackTrace();
				}
			} else {
				errorMessage = "Confirmation number or email could not be validated. Please Try Again";
			}
		} else {
			errorMessage = "Could not parse confirmation number or email. Please Try Again";
		}

		if (!errorMessage.trim().isEmpty()) {
			request.setAttribute("errorMessage", errorMessage);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("lookupResults", allRes);
			request.setAttribute("name", nameFromDB);
			request.setAttribute("email", emailFromDB);
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/lookup.jsp");
			dispatcher.forward(request, response);
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

package montego_bay_resort.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

	public static ResultSet getRoomsForSearch(Connection connection, String checkInDate, String checkOutDate)
			throws SQLException {
		List<String> bookedRooms = new ArrayList<String>();
		ResultSet searchReservations = getReservations(connection, checkInDate, checkOutDate);
		while (searchReservations.next()) {
			bookedRooms.add(searchReservations.getString(3));
		}
		return getOpenRooms(connection, bookedRooms);
	}

	public static ResultSet getReservations(Connection connection,
			String checkInDate, String checkOutDate)
			throws SQLException {
		Statement statement = connection.createStatement();
		String searchReservationQuery = "SELECT checkIn, checkOut, roomId FROM montego.user_info_reservation_view where ('"
				+ checkInDate + "' <= checkIn and '" + checkOutDate
				+ "' >= checkOut) or ('" + checkInDate
				+ "' between checkIn and checkOut) or ('" + checkOutDate
				+ "' between checkIn and checkOut)";
		ResultSet result = statement.executeQuery(searchReservationQuery);
		return result;
	}


	public static ResultSet getOpenRooms(Connection connection, List<String> bookedRooms) throws SQLException {
		Statement statement = connection.createStatement();
		String bookedRoomsQuery = "SELECT * FROM montego.rooms ";
		if (bookedRooms.size() >= 1) {
			bookedRoomsQuery += "where roomId != '" + bookedRooms.get(0) + "'";
		}
		if (bookedRooms.size() > 1) {
			for (int i = 1; i < bookedRooms.size(); i++) {
				bookedRoomsQuery += "and roomId != '" + bookedRooms.get(i) + "'";
			}
		}
		ResultSet result = statement.executeQuery(bookedRoomsQuery);
		return result;
	}

	public static ResultSet getLookUpDetails(Connection connection, String uuid,
			String email) throws SQLException {
		Statement statement = connection.createStatement();
		String lookupQuery = "SELECT * FROM montego.user_info_room_info where id = '"
				+ uuid + "' and email = '" + email + "';";
		ResultSet result = statement.executeQuery(lookupQuery);
		return result;
	}
}

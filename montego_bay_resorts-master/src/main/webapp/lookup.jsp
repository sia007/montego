<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page
	import="montego_bay_resort.business.RoomBean, java.util.Collections,java.util.ArrayList, java.util.Collections,java.util.List"%>
<%
List<RoomBean> reservationResult = (List<RoomBean>) request
		.getAttribute("lookupResults");
String nameFromDB = (String) request.getAttribute("name");
String emailFromDB = (String) request.getAttribute("email");
%>
<c:import url="includes/header.jsp" />
<br>
<br>
<div class="jumbotron jumbotron-fluid">
	<div class="container" style="text-align: center;">
		<h1 class="display-5">Lookup Reservation</h1>
		<p class="lead">In order to lookup your reservation details,
			please provide your email and your confirmation number</p>
		<hr class="my-4">
	</div>
	<form action="LookUpServlet" method="get" class="booking-form">
		<div class="row justify-content-md-center">
			<label for="confirmation-number" class="col-auto col-form-label">Confirmation
				Number </label>
			<div class="col-auto confirmation-number">
				<input type="text" id="confirmation-number" class="form-control"
					name="confirmNumber" placeholder="Confirmation Number" required>
			</div>
			<label for="email" class="col-auto col-form-label">To </label>
			<div class="col-auto email">
				<input type="email" id="email" class="form-control" name="email"
					placeholder="Email" required>
			</div>
			<div class="col-auto">
				<input class="btn btn-primary" type="submit" value="Search">
			</div>
		</div>
	</form>
</div>
<%
if (reservationResult != null && nameFromDB != null && emailFromDB != null) {
	if (reservationResult.size() != 0) {
%>
<br>
<br>
<br>
<div class="container">
	<div class="jumbotron" style="text-align: center;">
		<br>
		<h1 class="display-5">
			Reservation Found For
			<%=nameFromDB%>
		</h1>
		<p class="lead">
			An email was sent to:
			<%=emailFromDB%></p>
		<hr class="my-4">
	</div>
</div>
<div class="container">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">Item #</th>
				<th scope="col">Check In</th>
				<th scope="col">Check Out</th>
				<th scope="col">Room Number</th>
				<th scope="col">Room Size</th>
				<th scope="col">Room Facing</th>
				<th scope="col">Room Occupancy</th>
				<th scope="col">Room Type</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < reservationResult.size(); i++) {
			%>
			<tr>
				<th scope="row"><%=i + 1%></th>
				<td><%=reservationResult.get(i).getCheckIn()%></td>
				<td><%=reservationResult.get(i).getCheckOut()%></td>
				<td><%=reservationResult.get(i).getRoomNumber()%></td>
				<td><%=reservationResult.get(i).getRoomSize()%></td>
				<td><%=reservationResult.get(i).getRoomFacing()%></td>
				<td><%=reservationResult.get(i).getRoomOccupancy()%></td>
				<td><%=reservationResult.get(i).getRoomType()%></td>
			</tr>
		</tbody>
	</table>
</div>
<%
}
} else {
%>
<br>
<br>
<br>
<div class="jumbotron" style="text-align: center;">
	<br>
	<h1 class="display-5">No Reservation Found</h1>
	<p class="lead">Check to ensure you are using the proper email and confirmation number.</p>
</div>

<%
}
}
%>
<c:import url="includes/footer.jsp" />
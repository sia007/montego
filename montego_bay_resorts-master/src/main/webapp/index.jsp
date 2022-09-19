<%@ page import="montego_bay_resort.business.ResForm"%>
<jsp:useBean id="myReservation"
	class="montego_bay_resort.business.ResForm" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:import url="includes/header.jsp" />
<div class="container-fluid bg-image search_bar"
	style="background-image: url('assets/archives.jpeg');">
	<br> <br> <br>
	<div class="jumbotron jumbotron-fluid">
		<div class="container-fluid text_white">
			<h1 class="display-4">Welcome to Montego Bay Resort</h1>
			<p class="lead">We look forward to assisting you in paradise!</p>
		</div>
	</div>
	<div class="container text_white">
		<form action="ResultsServlet" method="get" class="booking-form">
			<div class="row justify-content-md-center">
				<label for="check-in-date" class="col-auto col-form-label">From</label>
				<div class="col-auto check-in-date">
					<input type="text" id="checkInDate" class="form-control"
						name="checkInDate" value="<%=myReservation.getCheckin()%>"
						placeholder="Check In Date" required>
				</div>

				<label for="check-out-date" class="col-auto col-form-label">To
				</label>
				<div class="col-auto check-out-date">
					<input type="text" id="checkOutDate" class="form-control"
						name="checkOutDate" value="<%=myReservation.getCheckout()%>"
						placeholder="Check Out Date" required>
				</div>
				<div class="col-auto">
					<input class="btn btn-primary" type="submit" value="Search">
				</div>
			</div>
		</form>
		<br> <br>
		<%
		if (myReservation.getCheckin() != "" && myReservation.getCheckout() != "") {
		%>

		<div class="alert alert-warning container alert-blocks" role="alert">Warning,
			if you modify you search you will wipe out your cart. If you wish to
			continue making modifications, search with the same date or go to
			cart.</div>
		<%
		}
		%>
		<br>

		<div class="alert alert-primary container alert-blocks" role="alert">
			<a href="https://www.osha.gov/coronavirus/safework">Click here to
				learn more about our COVID-19 Policy and Guide</a>
		</div>
		<br>
	</div>
</div>
<div class="container">
	<div class="row">
		<div class="col-sm-4">
			<div class="card mt-5" style="text-align: center;">
				<img class="card-img-top" src="assets/spa.jpeg" height="250"
					alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Our Amenities</h5>
					<p class="card-text">Learn more about our five star amenities.</p>
					<p class="card-body" style="text-align: center;">
						<a class="btn btn-primary btn-lg" href="ouramenities.jsp"
							role="button">Learn More</a>
					</p>
					<br>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="card mt-5" style="text-align: center;">
				<img class="card-img-top" src="assets/s-hotel-jamaica.jpeg"
					height="250" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Our Rooms</h5>
					<p class="card-text">Learn about what kind of rooms we can
						offer.</p>
					<p class="card-body" style="text-align: center;">
						<a class="btn btn-primary btn-lg" href="ourrooms.jsp"
							role="button">Learn More</a>
					</p>
					<br>
				</div>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="card mt-5" style="text-align: center;">
				<img class="card-img-top" src="assets/montegodowntown.jpeg"
					height="250" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title">Our Montego Bay</h5>
					<p class="card-text">Learn more about Montego Bay, Jamacia.</p>
					<p class="card-body" style="text-align: center;">
						<a class="btn btn-primary btn-lg" href="ourmontego.jsp"
							role="button">Learn More</a>
					</p>
					<br>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<br>
<!-- <a class="btn btn-primary" href="results.jsp">Go to Results</a> -->
<c:import url="includes/footer.jsp" />
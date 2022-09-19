
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<link href="css/confirmation.css" rel="stylesheet">

<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/styles.css" />
<link rel="icon" href="assets/resort-icon.png">
<body class="container-fluid" style="height: 30px;">
	<nav class="navbar navbar-expand-lg navbar-light brand_blue">
		<div class="container">
			<a class="navbar-brand" href="index.jsp" style="color: white;"> <img
				src="assets/resort-icon.png" width="20" height="30"
				class="d-inline-block align-top" alt=""
				style="display: block; width: 23px; height: 30px;"> Montego
				Bay Resorts
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="index.jsp" style="color: white;">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="lookup.jsp"
						style="color: white;">Lookup Reservation</a></li>
					<li class="nav-item"><a class="nav-link" href="cart.jsp"
						style="color: white;">Cart</a></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
<head>
<meta charset="ISO-8859-1">
<title>Booking Confirmation</title>
</head>
<body>

	<div class="container mt-5 mb-5">
		<div class="row d-flex justify-content-center">
			<div class="col-md-8">
				<div class="card">
					<div class="text-left logo p-2 px-5">
						<img src="assets/resort-icon.png" width="50">
					</div>
					<div class="invoice p-5">
						<h5>Your booking has been Confirmed!</h5>
						<span class="font-weight-bold d-block mt-4">Hello,
							${room.getName()}!</span> <br> <span>You booking has been
							confirmed and you will be getting a confirmation email shortly on
							your email address ${room.getEmail()}</span>
						<div
							class="payment border-top mt-3 mb-3 border-bottom table-responsive">
							<br>
							<table class="table table-borderless" align="center">
								<tbody>
									<tr>
										<td>
											<div class="py-2">
												<span class="d-block text-muted">Check-In Date</span> <span>${room.getCheckin()}</span>
											</div>
										</td>
										<td>
											<div class="py-2">
												<span class="d-block text-muted">Check-Out Date</span> <span>${room.getCheckout()}</span>
											</div>
										</td>

										<td>
											<div class="py-2">
												<span class="d-block text-muted">Confirmation No</span> <span>${room.getReservation()}</span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="product border-bottom table-responsive">
							<table class="table table-borderless" align="center">
								<c:forEach var="i" items="${room.getRoomList()}">
									<tr>
										<td width="20%"><img src="assets/hotel2.jpg" width="90">
										</td>
										<td width="60%"><span class="font-weight-bold">${i.getRoomType()}</span>
											<div class="product-qty">
												<span class="d-block">Occupancy:${i.getRoomOccupancy()}</span>
												<span>View:${i.getRoomFacing()}</span>
											</div></td>
										<td width="20%">
											<div class="text-right">
												<span class="font-weight-bold">$${i.getRoomBasePrice()}.00</span>
											</div>
										</td>
									</tr>
								</c:forEach>


							</table>
						</div>
						<div class="row d-flex justify-content-end">
							<div class="col-md-5">
								<table class="table table-borderless" align="center">
									<tbody class="totals">
										<tr>
											<td>
												<div class="text-left">
													<span class="text-muted">Subtotal</span>
												</div>
											</td>



											<td>
												<div class="text-right">
													<span>$${room.getTotalCost()}.00</span>
												</div>
											</td>
										</tr>
										<tr>
											<td>
												<div class="text-left">
													<span class="text-muted"></span>
												</div>
											</td>
											<td>
												<div class="text-right">
													<span class="text-success"></span>
												</div>
											</td>
										</tr>
										<tr class="border-top border-bottom">
											<td>
												<div class="text-left">
													<span class="font-weight-bold">Total</span>
												</div>
											</td>
											<td>
												<div class="text-right">
													<span class="font-weight-bold">$${room.getTotalCost()}.00</span>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<p>We hope you will have an enjoyable and memorable stay at
							Montego Bay!</p>
						<p class="font-weight-bold mb-0">Thanks for booking with us!</p>
						<span>Montego Bay Resort</span>
					</div>
					<div class="d-flex justify-content-between footer p-3">
						<span></span> <span></span>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>

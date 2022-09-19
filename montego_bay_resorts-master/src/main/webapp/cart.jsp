<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/styles.css" />
<link rel="icon" href="assets/resort-icon.png">
<body class="container-fluid">
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
<link href="css/cart-style.css" rel="stylesheet">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cart</title>
</head>

<body>
	<main>
		<div class="basket">

			<div class="basket-labels">
				<ul>
					<li class="item item-heading">Item</li>
					<li class="price">Price</li>
					<li class="quantity">Occupancy</li>
					<li class="subtotal">Subtotal</li>
				</ul>
			</div>

			<c:set var="quantity" scope="session"
				value="${room.getRoomList().size()}" />

			<c:choose>

				<c:when test="${quantity > 0}">


					<body>
						<c:set var="count" value="0" scope="session" />


						<table align="center">

							<c:forEach var="i" items="${room.getRoomList()}">
								<tr>
									<td>
										<div class="basket-product">
											<div class="item">
												<div class="product-image">

													<img src="assets/hotel2.jpg" alt="Placholder Image 2"
														class="product-frame">
												</div>


												<div class="product-details">
													<h1>
														<strong><span class="item-quantity">1</span> x
															${i.getRoomType()}</strong>
													</h1>
													<p>Room Facing - ${i.getRoomFacing()}</p>
												</div>
											</div>
											<div class="price">${i.getRoomBasePrice()}.00</div>
											<div class="quantity">
												<h2>${i.getRoomOccupancy()}xPersons</h2>
											</div>
											<div class="subtotal">${i.getRoomBasePrice()}.00</div>

											<div class="remove">
												<form action="Controller" method="POST">
													<input type="hidden" name="action" value="remove">
													<input type="hidden" name="remove" value="${count}">
													<input class="remove" type="submit" value="Remove"
														style="background-color: transparent; color: #777; float: none; text-decoration: underline; text-transform: uppercase;">
												</form>
												<c:set var="count" value="${count + 1}" scope="session" />

											</div>


										</div>

									</td>
								</tr>

							</c:forEach>


						</table>
				</c:when>
			</c:choose>

			<br> <br> <br> <br>
			<div class="button">
				<button id="PayButton" name="action" value="confirm"
					class="checkout-cta btn-subtotal button" type="submit"
					onclick="window.location='results.jsp'"
					style="width: 30%; height: 100%; margin: 0 auto;">
					<span>Go Back To Results </span>
				</button>
			</div>
		</div>
</body>


<aside>
	<div class="summary">
		<div class="summary-total-items">
			<span class="total-items"></span> Items in your Cart
		</div>
		<div class="summary-subtotal">
			<div class="subtotal-title">Subtotal</div>
			<div class="subtotal-value final-value" id="basket-subtotal">${room.getTotalCost()}.00</div>

		</div>
		<div class="summary-delivery"></div>
		<div class="summary-total">
			<div class="total-title">Total</div>
			<div class="total-value final-value" id="basket-total">${room.getTotalCost()}.00</div>
		</div>
		<div class="summary-checkout">
			<div class="checkout-cta">
				<button class="checkout-cta btn-subtotal button" type="submit"
					onclick="window.location='checkout.jsp'">
					<span>GO TO SECURE CHECKOUT </span>
				</button>

			</div>
		</div>
	</div>
</aside>
</main>
</body>

</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>



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
<title>Secure Checkout</title>
</head>
<link href="css/checkout.css" rel="stylesheet">

<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<body>
	<div class="container2">

		<div id="Checkout" class="inline">
			<h1>Pay Invoice</h1>
			<br>
			<form method="POST" id="form1" action="Controller"
				onsubmit="return validateForm();">


				<script type="text/javascript">
					function validateForm() {
						var name = document.getElementById("name").value;
						var email = document.getElementById("email").value;

						if (name == "") {
							alert("Please enter your full name");
							return false;
						}

						if (email == "") {
							alert("Please enter your email address");
							return false;
						}

					}
				</script>
				<div class="form-group">

					<label or="NameOnCard">Name</label> <input class="form-control"
						maxlength="50" id="name" type="text" name="name" required /> <br>
					<div class="form-group">
						<label for="CreditCardNumber">Email Address</label> <input
							class="null card-image form-control" maxlength="50" id="email"
							type="text" name="email" required />
					</div>
					<br> <br>
					<div class="form-group">

						<label for="PaymentAmount">Payment amount</label> <br>
						<div class="amount-placeholder">
							<span>$</span> <span>${room.getTotalCost()}.00</span>
						</div>
					</div>
					<br> <br>
					<div class="form-group">
						<p>Choose your payment option:</p>
					</div>


					<form>
						<input type="radio" id="html" name="fav_language" value="" checked>
						<label for="html">Pay on Arrival</label><br>
					</form>

				</div>

				<br>




				<button id="PayButton" name="action" value="confirm"
					class="btn btn-block btn-success submit-button" form="form1"
					type="submit" style="width: 100%">
					<span class="submit-button-lock"></span> <span class="align-middle">Pay
						$${room.getTotalCost()}.00</span>
				</button>
			</form>
		</div>
	</div>

</body>
</html>

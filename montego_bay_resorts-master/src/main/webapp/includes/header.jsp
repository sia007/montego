<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/styles.css" />
<!-- <link rel="stylesheet" href="css/cart-style.css"> -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script>
	$(function() {
		$("#checkInDate").datepicker({
			dateFormat : "yy-mm-dd"
		});
		$("#checkOutDate").datepicker({
			dateFormat : "yy-mm-dd"
		});
	});
</script>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Montego Bay Resort</title>
</head>
<body class="container-fluid">
	<nav class="navbar navbar-expand-lg navbar-light brand_blue">
		<div class="container">
			<a class="navbar-brand" href="index.jsp" style="color: white;"> <img
				src="assets/resort-icon.png" width="30" height="30"
				class="d-inline-block align-top" alt=""> Montego Bay Resort
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
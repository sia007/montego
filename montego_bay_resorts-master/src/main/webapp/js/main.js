function redirect() {
	if (window.confirm("Modifying your reservation will remove all items from cart. Please confirm this action or checkout with cart first.")) {
		window.open("index.jsp");
	}
}
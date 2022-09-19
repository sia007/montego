<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="myReservation"
	class="montego_bay_resort.business.ResForm" scope="session" />
<%@ page
	import="montego_bay_resort.business.RoomBean, java.util.Collections,java.util.ArrayList, java.util.Collections,java.util.List"%>
<%
String error = (String) request.getAttribute("errorMessage");
%>
<c:import url="includes/header.jsp" />
<br>
<br>

<div class="jumbotron container" style="text-align:center;">
	<h1 class="display-4">An Error Has Occurred. Try Again.</h1>
	<p class="lead">Please correct the following error(s):</p>
	<hr class="my-4">
	<p><%=error%></p>
	<hr class="my-4">
	<p class="lead">
		<a class="btn btn-primary btn-lg" href="index.jsp" role="button">Try
			Again</a>
	</p>
</div>
<c:import url="includes/footer.jsp" />
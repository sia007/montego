<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:useBean id="myReservation"
	class="montego_bay_resort.business.ResForm" scope="session" />
<%@ page
	import="montego_bay_resort.business.RoomBean, java.util.Collections,java.util.ArrayList, java.util.Collections,java.util.List"%>
<%
List<RoomBean> search = myReservation.getSearchResults();
%>
<c:import url="includes/header.jsp" />
<br>
<br>
<div class="jumbotron jumbotron-fluid">
	<div class="container" style="text-align: center;">
		<h1 class="display-5">Rooms Available</h1>
		<p class="lead">Your request for a reservation on the following
			dates returned these results.</p>
		<hr class="my-4">
		<p class="lead">
			<a class="btn btn-outline-primary btn-md" onclick="redirect()"
				role="button">Modify Search</a>
		</p>
	</div>
</div>
<form action="Controller" method="get">
	<div class="container">
		<div class="row">
			<%
			for (int i = 0; i < search.size(); i++) {
			%>
			<div class="col-sm-3">
				<div class="card mt-5">
					<img class="card-img-top" src="assets/hotel2.jpg"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=search.get(i).getRoomType()%></h5>
						<ul class="list-group list-group-flush">
							<li class="list-group-item"><img
								src="assets/bootstrap-icons-1.8.1/door-open.svg" alt="Hash Icon"
								width="24" height="24">&nbsp; Room <%=search.get(i).getRoomNumber()%></li>
							<li class="list-group-item"><img
								src="assets/bootstrap-icons-1.8.1/rulers.svg" alt="Ruler Icon"
								width="24" height="24">&nbsp; <%=search.get(i).getRoomSize()%>
								sq. ft.</li>
							<li class="list-group-item"><img
								src="assets/bootstrap-icons-1.8.1/binoculars.svg"
								alt="Binoculars Icon" width="24" height="24">&nbsp; <%=search.get(i).getRoomFacing()%></li>
							<li class="list-group-item"><img
								src="assets/bootstrap-icons-1.8.1/people-fill.svg"
								alt="People Icon" width="24" height="24">&nbsp; 1 Room, <%=search.get(i).getRoomOccupancy()%>
								Guest</li>
							<li class="list-group-item"><img
								src="assets/bootstrap-icons-1.8.1/currency-dollar.svg"
								alt="People Icon" width="24" height="24">&nbsp; <%=search.get(i).getRoomBasePrice()%>/night</li>
						</ul>
						<!--<p class="card-text">Lorem ipsum dolor sit amet, consectetur
								adipiscing elit, sed do eiusmod tempor incididunt ut labore et
								dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
								exercitation ullamco laboris nisi ut aliquip ex ea commodo
								consequat. Duis aute irure dolor in reprehenderit in voluptate
								velit esse cillum dolore eu fugiat nulla pariatur. Excepteur
								sint occaecat cupidatat non proident, sunt in culpa qui officia
								deserunt mollit anim id est laborum.</p>-->
						<p class="card-body" style="text-align: center;">
							<button class="btn btn-primary" name="roomId" type="submit"
								value=<%=search.get(i).getRoomId()%>>Add to Cart</button>
						</p>
						<br>
						<!-- <p class="card-text">
							<small class="text-muted">Last updated 3 mins ago</small>
						</p> -->
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</form>
<c:import url="includes/footer.jsp" />
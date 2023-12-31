<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setHeader("Expires", "0"); // Proxies
%>
<%@page import="dto.User"%>
<%@page import="dto.Notification"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<jsp:useBean id="userBean" class="beans.UserBean" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/users.css" rel="stylesheet">
<script src="scripts/notification.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<jsp:include page="notification.jsp" />
	<div class="container">
		<form method="POST" action="?action=showAddUser">
			<button type="submit" id="add-user-button" class="add-button">Add
				user</button>
		</form>
		<table class="table">
			<tr>
				<th>Username</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Email</th>
				<th>Password</th>
				<th>Country</th>
				<th>City</th>
				<th>Street address</th>
				<th>Street number</th>
				<th>Postal code</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>

			<%
			ArrayList<User> users = userBean.getAllUsers();
			if (users != null) {
				Iterator<User> iterator = users.iterator();
				while (iterator.hasNext()) {
					User user = iterator.next();
			%>
			<tr>
				<td><%=user.getUsername()%></td>
				<td><%=user.getFirstName()%></td>
				<td><%=user.getLastName()%></td>
				<td><%=user.getEmail()%></td>
				<td>********</td>
				<td><%=user.getCountry().getName()%></td>
				<td><%=user.getLocation().getCity()%></td>
				<td><%=user.getLocation().getStreetAddress()%></td>
				<td><%=user.getLocation().getStreetNumber()%></td>
				<td><%=user.getLocation().getPostalCode()%></td>
				<td>
					<form method="POST"
						action="?action=openEditUser&userId=<%=user.getId()%>">
						<button type="submit" class="edit-button">Edit</button>
					</form>
				</td>
				<td>
					<form method="POST"
						action="?action=deleteUser&userId=<%=user.getId()%>&countryId=<%=user.getCountryId()%>&locationId=<%=user.getLocationId()%>">
						<button type="submit" class="delete-button">Delete</button>
					</form>
				</td>
			</tr>
			<%
			}
			}
			%>
		</table>
	</div>
	<%
	Notification notification = (Notification) session.getAttribute("notification");
	if (notification != null) {
	%>
	<script>
    showNotification('<%=notification.getMessage()%>', '<%=notification.getType()%>
		');
	</script>
	<%
	}
	%>
</body>
</html>
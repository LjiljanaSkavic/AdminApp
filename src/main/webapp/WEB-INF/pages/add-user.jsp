<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setHeader("Expires", "0"); // Proxies
%>
<%@page import="dto.Notification"%>
<jsp:useBean id="userBean" class="beans.UserBean" scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit user</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/add-user.css" rel="stylesheet">
<script src="scripts/notification.js" type="text/javascript"></script>
</head>
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<jsp:include page="notification.jsp" />
	<div class="container">
		<form method="POST" action="?action=newUser">
			<div class="input-label-wrapper">
				<span>Username</span> <input type="text" name="username"
					id="username" />
			</div>
			<div class="input-label-wrapper">
				<span>First name</span> <input type="text" name="firstName"
					id="firstName" />
			</div>
			<div class="input-label-wrapper">
				<span>Last name</span> <input type="text" name="lastName"
					id="lastName" />
			</div>
			<div class="input-label-wrapper">
				<span>Email</span> <input type="email" name="email" id="email">
			</div>
			<div class="input-label-wrapper">
				<span>Password</span> <input type="password" name="password"
					id="password" />
			</div>
			<div class="input-label-wrapper">
				<span>Country</span> <input type="text" name="country" id="country" />
			</div>
			<div class="input-label-wrapper">
				<span>City</span> <input type="text" name="city" id="city" />
			</div>
			<div class="input-label-wrapper">
				<span>Street address</span> <input type="text" name="streetAddress"
					id="streetAddress" />
			</div>
			<div class="input-label-wrapper">
				<span>Street number</span> <input type="number" name="streetNumber"
					id="streetNumber" />
			</div>
			<div class="input-label-wrapper">
				<span>Postal code</span> <input type="text" name="postalCode"
					id="postalCode" />
			</div>
			<div class="button-wrapper">
				<button type="submit" name="cancel" class="cancel-button">Cancel</button>
				<button type="submit" name="save" class="save-button">Add
					user</button>
			</div>
		</form>
	</div>
	<%
	Notification notification = (Notification) session.getAttribute("notification");
	if (notification != null) {
	%>
	<script>
    showNotification('<%=notification.getMessage()%>', '<%=notification.getType()%>');
	</script>
	<%
	}
	%>
</body>
</html>
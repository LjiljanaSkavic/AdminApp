<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setHeader("Expires", "0"); // Proxies
%>
<%@page import="dto.Notification"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="styles/shared.css" type="text/css" rel="stylesheet">
<link href="styles/login.css" type="text/css" rel="stylesheet">
<script src="scripts/notification.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="notification.jsp" />
	<form action="?action=login" method="post">
	   <div class="form-wrapper">
         <div class="field-wrapper">
            <p class="field-name">Username</p>
            <input type="text" name="username" id="username" class="field-value"/>
         </div>
         <div class="field-wrapper">
            <p class="field-name">Password</p>
            <input type="password" name="password" id="password" class="field-value"/>
         </div>
         <button type="submit" name="submit" class="login-button">Login</button>
      </div>
	</form>
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
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
<title>Edit category</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/edit-category.css" rel="stylesheet">
<script src="scripts/notification.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<jsp:include page="notification.jsp" />
	<div class="container">
		<span>Edit category</span>
		<span>
			Rename
			<%=request.getParameter("categoryName")%></span>
		<span>New category name :</span>
		<form method="POST" action="?action=editCategory">
			<div>
				<input type="hidden" name="categoryId"
					value="<%=request.getParameter("categoryId")%>"> <input
					type="text" placeholder="New category name" name="newName">
				<button type="submit">Save</button>
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
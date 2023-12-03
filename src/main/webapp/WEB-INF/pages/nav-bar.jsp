<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setHeader("Expires", "0"); // Proxies
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="styles/nav-bar.css" rel="stylesheet">
</head>
<body>
	<ul>
		<li><a
			class="<%=request.getParameter("action").toLowerCase().contains("user")
		|| request.getParameter("action").contains("login") ? "active" : ""%>"
			href="?action=users">Users</a></li>
		<li><a
			class="<%=request.getParameter("action").toLowerCase().contains("categor") ? "active" : ""%>"
			href="?action=categories">Categories</a></li>
		<li><a
			class="<%=request.getParameter("action").toLowerCase().contains("logging") ? "active" : ""%>"
			href="?action=logging">Logging</a></li>
		<li class="logout"><a
			class="<%=request.getParameter("action").toLowerCase().contains("logout") ? "active" : ""%>"
			href="?action=logout">Logout</a></li>
	</ul>
</body>
</html>
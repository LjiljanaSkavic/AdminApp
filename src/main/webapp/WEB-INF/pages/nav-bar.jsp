<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="styles/nav-bar.css" rel="stylesheet">
</head>
<body>
	<ul>
        <li><a class="<%=request.getParameter("action").toLowerCase().contains("user") || request.getParameter("action").contains("login") ? "active" : "" %>" href="?action=users">Users</a></li>
        <li><a class="<%=request.getParameter("action").toLowerCase().contains("categor") ? "active" : "" %>" href="?action=categories">Categories</a></li>
        <li><a class="<%=request.getParameter("action").toLowerCase().contains("logout") ? "active" : "" %>" href="?action=logout">Logout</a></li>
    </ul>
</body>
</html>
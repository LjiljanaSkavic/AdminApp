<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="userBean" class="beans.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit user</title>
<link href="styles/shared.css" rel="stylesheet">
</head>
<body>
	<%@include file="nav-bar.jsp" %>
	<div class="container">
	<form method="POST" action="?action=editUser">
		<input type="hidden" name="userId" value="<%=request.getParameter("userId")%>"> 
		<span>Username</span> <input type="text" name="username" id="username" value="<%=userBean.getUserForEdit().getUsername()%>"><br></br>
		<span>First name</span> <input type="text" name="firstName" id="firstName" value="<%=userBean.getUserForEdit().getFirstName()%>"><br></br>
		<span>Last name</span> <input type="text" name="lastName" id="lastName" value="<%=userBean.getUserForEdit().getLastName()%>"> <br></br>
		<span>Email</span> <input type="email" name="email" id="email" value="<%=userBean.getUserForEdit().getEmail()%>"> <br></br>
		<span>Password</span> <input type="password" name="password" id="password" <%=userBean.getUserForEdit().getPassword()%>"> <br></br>
		<span>Country</span> <input type="text" name="country" id="country" value="<%=userBean.getUserForEdit().getCountry().getName()%>"> <br></br>
		<span>City</span> <input type="text" name="city" id="city" value="<%=userBean.getUserForEdit().getLocation().getCity()%>"> <br></br>
		<span>Street address</span> <input type="text" name="streetAddress" id="streetAddress" value="<%=userBean.getUserForEdit().getLocation().getStreetAddress()%>"> <br></br>
		<span>Street number</span> <input type="number" name="streetNumber" id="streetNumber" value="<%=userBean.getUserForEdit().getLocation().getStreetNumber()%>"> <br></br>
		<span>Postal code</span> <input type="text" name="postalCode" id="postalCode" value="<%=userBean.getUserForEdit().getLocation().getPostalCode()%>"> <br></br>
		<button type="submit" name="saveUser">Save</button>
		<h3><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></h3>
	</form>
	</div>
</body>
</html>
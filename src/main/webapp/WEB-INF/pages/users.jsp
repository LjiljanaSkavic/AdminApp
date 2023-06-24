<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dto.User"%>
    <%@page import="java.util.ArrayList"%>  
    <%@page import="java.util.Iterator"%>  
    <jsp:useBean id="userBean" class="beans.UserBean" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/users.css" rel="stylesheet">
</head>
<body>
	<%@include file="nav-bar.jsp" %>
	<div class="container">
		<h4>Add user</h4>
		<form method="POST" action="?action=saveUser">
			Username <input type="text" name="username" id="username" /> <br></br>
			First name <input type="text" name="firstName" id="firstName" /> <br></br>
			Last name <input type="text" name="lastName" id="lastName" /> <br></br>
			Email <input type="email" name="email" id="email" /> <br></br>
			Password <input type="password" name="password" id="password" /> <br></br>
			Country <input type="text" name="country" id="country" /> <br></br>
			City <input type="text" name="city" id="city" /> <br></br>
			Street address <input type="text" name="streetAddress" id="streetAddress" /> <br></br>
			Street number <input type="number" name="streetNumber" id="streetNumber" /> <br></br>
			Postal code <input type="text" name="postalCode" id="postalCode" /> <br></br>
			
			<input type="submit" value="Save" name="saveUser" />
			<h3><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></h3>
		</form>
		<table class="users-table">
			<tr class="myrow">
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
			
			<%  ArrayList<User> users = userBean.getAllUsers(); 
				if(users != null){
					Iterator<User> iterator = users.iterator(); 
					while(iterator.hasNext())  
					{
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
										<form method="POST" action="?action=openEditUser&userId=<%=user.getId()%>">
											<button type="submit" style="width: 100%; background-color: #0ce897; padding: 14px 20px; margin: 8px 0; border: none; cursor: pointer;">
												Edit
											</button>
										</form>
									</td>
									<td>
										<form method="POST" action="?action=deleteUser&userId=<%=user.getId()%>&countryId=<%=user.getCountryId()%>&locationId=<%=user.getLocationId()%>">
											<button type="submit" style="width: 100%; background-color: #0ce897; padding: 14px 20px; margin: 8px 0; border: none; cursor: pointer;">
												Delete
											</button>
										</form>
									</td>
								</tr>
							<%
						}
					}
			%>
		</table>
	</div>
</body>
</html>
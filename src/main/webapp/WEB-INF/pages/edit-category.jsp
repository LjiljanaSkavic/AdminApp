<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit category</title>
<link href="styles/shared.css" rel="stylesheet">
</head>
<body>
	<%@include file="nav-bar.jsp" %>
	<div class="container">
   <h2>Edit category</h2>
   <h4>New category name :</h4>
   <form method="POST" action="?action=editCategory">
   <div>
      <input type="hidden" name="categoryId" value="<%=request.getParameter("categoryId")%>"> 
      <input type="text" placeholder="New category name" name="newName">
      <button type="submit">Save</button>	
      </div>
   </form>
   </div>
</body>
</html>
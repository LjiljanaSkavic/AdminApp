<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setHeader("Expires", "0"); // Proxies
%>
<%@page import="dto.Category"%>
<%@page import="dto.Notification"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<jsp:useBean id="categoryBean" class="beans.CategoryBean"
	scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Categories</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/categories.css" rel="stylesheet">
<script src="scripts/notification.js" type="text/javascript"></script>
<style>
body {
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<jsp:include page="notification.jsp" />
	<div class="container">
		<form method="POST" action="?action=newCategory">
			<h4>ADD CATEGORY</h4>
			<span>Category name</span> <input type="text" name="newCategory"
				id="newCategory" />
			<button type="submit">Save</button>
			<h3><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : ""%></h3>
		</form>

		<form method="POST" action="?action=addSubcategory">
			<h4>ADD SUBCATEGORY</h4>
			<select name="parentCategorySelect">
				<option value="">Select parent category</option>

				<%
				for (Category category : categoryBean.getAllCategories())
					out.print("<option value=" + category.getId() + ">" + category.getName() + "</option>");
				%>
			</select> <span>Subcategory name</span> <input type="text"
				name="newSubcategoryName" id="newSubcategory" />
			<button type="submit">Save</button>
		</form>
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>

			<%
			ArrayList<Category> users = categoryBean.getAllCategories();
			if (users != null) {
				Iterator<Category> iterator = users.iterator();
				while (iterator.hasNext()) {
					Category category = iterator.next();
			%>
			<tr>
				<td><%=category.getName()%></td>
				<td>
					<form method="POST"
						action="?action=openEditCategory&categoryId
										=<%=category.getId()%>&categoryName=<%=category.getName()%>">
						<button class="edit-button" type="submit">Edit</button>
					</form>
				</td>
				<td>
					<form method="POST"
						action="?action=deleteCategory&categoryId=<%=category.getId()%>">
						<button class="delete-button" type="submit">Delete</button>
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
    showNotification('<%=notification.getMessage()%>', '<%=notification.getType()%>');
	</script>
	<%
	}
	%>
</body>
</html>
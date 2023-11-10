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
<title>Add categories</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/add-categories.css" rel="stylesheet">
<script src="scripts/notification.js" type="text/javascript"></script>
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<jsp:include page="notification.jsp" />
	<div class="add-categories-wrapper">
		<form method="POST" action="?action=newCategory">
			<div class="add-category-wrapper">
				<h3>Add category</h3>
				<div class="input-label-wrapper">
					<span>Category name</span> <input type="text" name="newCategory"
						id="newCategory" />
				</div>
				<div class="button-wrapper">
					<button type="submit" name="cancel" class="cancel-button">Cancel</button>
					<button type="submit" name="save" class="save-button">Save</button>
				</div>
			</div>
		</form>

		<form method="POST" action="?action=newSubcategory">
			<div class="add-subcategory-wrapper">
				<h3>Add subcategory</h3>
				<select name="parentCategorySelect">
					<option value="">Select parent category</option>

					<%
					for (Category category : categoryBean.getAllCategories())
						out.print("<option value=" + category.getId() + ">" + category.getName() + "</option>");
					%>
				</select>
				<div class="input-label-wrapper">
					<span>Subcategory name</span> <input type="text"
						name="newSubcategoryName" id="newSubcategory" />
				</div>
				<div class="button-wrapper">
					<button type="submit" name="cancel" class="cancel-button">Cancel</button>
					<button type="submit" name="save" class="save-button">Save</button>
				</div>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dto.Category"%>
    <%@page import="java.util.ArrayList"%>  
    <%@page import="java.util.Iterator"%>
    <jsp:useBean id="categoryBean" class="beans.CategoryBean" scope="session"/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Categories</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/categories.css" rel="stylesheet">
</head>
<body>
	<%@include file="nav-bar.jsp" %>
	<div class="container">
		<form method="POST" action="?action=saveCategory">
			<h4>ADD CATEGORY</h4>
			<span>Category name</span> <input type="text" name="newCategory" id="newCategory" />
			<button type="submit">Save</button>
			<h3><%=session.getAttribute("notification")!=null?session.getAttribute("notification").toString():""%></h3>
		</form>
		
		<form>
			<h4>ADD SUBCATEGORY</h4>
			<select name="parent-category-select">
				<option value="">Select parent category</option>

				<%
				for (Category category : categoryBean.getAllCategories())
					out.print("<option value=" + category.getId() + ">" + category.getName() + "</option>");
				%>
			</select>
			<span>Subcategory name</span> <input type="text" name="newSubcategory" id="newSubcategory" />
			<button type="submit">Save</button>
		</form>
		<table class="category-table">
			<tr>
				<th>Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
	
		 <%  ArrayList<Category> users = categoryBean.getAllCategories(); 
				if(users != null){
					Iterator<Category> iterator = users.iterator(); 
					while(iterator.hasNext())  
					{
						Category category = iterator.next();
							%>
								<tr>
									<td><%=category.getName()%></td>
									<td>
										<form method="POST" action="?action=openEditCategory&categoryId=<%=category.getId()%>">
											<button type="submit">
												Edit
											</button>
										</form>
									</td>
									<td>
										<form method="POST" action="?action=deleteCategory&categoryId=<%=category.getId()%>">
											<button type="submit">
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
response.setHeader("Pragma", "no-cache"); // HTTP 1.0
response.setHeader("Expires", "0"); // Proxies
%>
<%@page import="dto.Log"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<jsp:useBean id="logBean" class="beans.LogBean"
	scope="session" />
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logging</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/logging.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="nav-bar.jsp" />
	<table class="table">
			<tr>
				<th>Id</th>
				<th>Content</th>
				<th>Time</th>
			</tr>

			<%
			ArrayList<Log> logs = logBean.getLogs();
			if (logs != null) {
				Iterator<Log> iterator = logs.iterator();
				while (iterator.hasNext()) {
					Log log = iterator.next();
			%>
			<tr>
				<td><%=log.getId()%></td>
				<td><%=log.getContent()%></td>
				<td><%=log.getDateTime()%></td>
			</tr>	
			<%
			}
			}
			%>
		</table>

</body>
</html>
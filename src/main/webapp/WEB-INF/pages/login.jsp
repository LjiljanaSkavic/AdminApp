<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/login.css" rel="stylesheet">
</head>
<body>
   <form method="POST" action="?action=login">
      <div class="form-wrapper">
         <div class="field-wrapper">
            <p class="field-name" >Username</p>
            <input type="text" name="username" id="username" class="field-value"/>
         </div>
         <div class="field-wrapper">
            <p class="field-name">Password</p>
            <input type="password" name="password" id="password" class="field-value"/>
         </div>
         <button type="submit" name="submit">Login</button>
         <p><%=session.getAttribute("notification") != null ? session.getAttribute("notification").toString() : "" %></p>
      </div>
   </form>
</body>
</html>
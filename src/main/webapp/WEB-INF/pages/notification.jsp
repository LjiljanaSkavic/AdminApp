<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.notification {
    background-color: #4CAF50;
    color: #fff;
    text-align: center;
    padding: 10px;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    z-index: 1000;
    display: none;
}

.notification-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.notification-message {
    flex-grow: 1;
    margin: 0;
}

.notification-close {
    background-color: transparent;
    color: #fff;
    border: none;
    cursor: pointer;
    padding: 5px 10px;
}
</style>
</head>
<body>
<div class="notification" id="notification">
    <div class="notification-content">
        <p class="notification-message">This is a notification message.</p>
        <button class="notification-close" onclick="closeNotification()">Close</button>
    </div>
</div>
</body>
</html>
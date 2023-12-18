<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<%@page isELIgnored="false"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

</head>
<body>

<h1>Welcome, ${user.username }</h1>
<h1>Your password is ${user.password }, try to secure it</h1>
<h1>${msg }</h1>

</body>
</html>
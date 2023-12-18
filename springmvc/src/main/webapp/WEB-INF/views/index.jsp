<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home PAGE </title>
</head>
<body>
<h1>Called by Home Controller /home</h1>


<!-- Receiving objects -->
<% 
	/* String name = (String)request.getAttribute("name");
	Integer id = (Integer) request.getAttribute("id"); */
%>

<%-- <h3>Name is <%=name%> with ID <%=id%></h3> --%>

	 <h3>Name is ${name}</h3>
	 <h3>Number = ${list} </h3>
	 
	 <c:forEach var="item" items="${list}">
	 	<h3>${item}</h3>
	 </c:forEach>

 </body>
</html>
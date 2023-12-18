<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
  <title>Dashboard</title>
  <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    />
  <script src="https://cdnjs.cloudflare.com/ajax/libs/Sortable/1.13.0/Sortable.min.js"></script> 
  <link href="<c:url value="/resources/css/flipper.css" />" rel="stylesheet">
</head>
<body>
  <header>
    <ul class="right-menu">
      <li><a href="#">About</a></li>
      <li><a href="#">Settings</a></li>
      <li><a href="#">Contact</a></li>
      <li>Welcome, ${userName}</li>
      <input type="text" id="searchInput" placeholder="&#x1F50D; Search website">
    </ul>
  </header>

	  <div id="flip-container-list">
	   <script type="text/javascript">
        var websitesString = '<%= request.getAttribute("websites") %>';
        var userEmail = '<%= request.getAttribute("userEmail") %>';
    	</script>
    </div>
	  
	

  <div class="add-container">
    <div title="Add New Website" id="add-button" class="add-button">+</div>
  </div>

  <footer>
    <p>&copy; 2023 PawsWord. All rights reserved.</p>
  </footer>

  <script src="<c:url value="/resources/js/flipper.js" />"></script>
</body>
</html>

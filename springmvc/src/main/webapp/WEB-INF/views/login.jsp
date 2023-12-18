<!-- login.html -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">
  <head>
      <script src="<c:url value="/resources/js/invalid.js" />"></script>
  
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Interactive Panda Form</title>
    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    <!-- Stylesheet -->
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
    
  </head>
  
  <body>
  
  
  <%
  	
  	String error = (String) request.getAttribute("error");
	if(error!=null){ 
	%>
		<script>
			showPopupMessage("${error}"); 
		</script>
	<%
	}
  %>
  
  
    <div class="container">
      <form action="/pawsword/login" method="post">
        <!-- Existing login form fields -->
        <label for="email">Email ID:</label>
        <input type="text" id="email" placeholder="Email here..." name="email" required/>
        
        <label for="password">Password:</label>
        <input type="password" id="password" placeholder="Password here..." name="password" required/>
         
         <span class="toggle-password" id="showPasswordBtn">
      		<i class="fas fa-eye" onclick="myFunction()"></i>
    	</span>
      	
        <button class="button" type="submit">Login</button>
        <!-- Signup button -->
        <button class="button" id="signup-btn" type="button">Sign Up</button>
      </form>
      <div class="ear-l"></div>
      <div class="ear-r"></div>
      <div class="panda-face">
        <div class="blush-l"></div>
        <div class="blush-r"></div>
        <div class="eye-l">
          <div class="eyeball-l"></div>
        </div>
        <div class="eye-r">
          <div class="eyeball-r"></div>
        </div>
        <div class="nose"></div>
        <div class="mouth"></div>
      </div>
      <div class="hand-l"></div>
      <div class="hand-r"></div>
      <div class="paw-l"></div>
      <div class="paw-r"></div>
    </div>
    
    <!-- Script -->
    <script src="<c:url value="/resources/js/script.js" />"></script>
    
    <!-- JavaScript code to handle button click and navigate to the signup page -->
    <script>
      // Get the signup button element
      const signupBtn = document.getElementById("signup-btn");

      // Add event listener to handle signup button click
      signupBtn.addEventListener("click", () => {
        window.location.href = "/pawsword/signup";
      });
    </script>
  </body>
</html>

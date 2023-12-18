<!-- signup.html -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Signup Form</title>
    <!-- Google Font -->
    <link
      href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    
    <!-- Stylesheet -->
    <link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/strength.css" />" rel="stylesheet">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/zxcvbn/4.4.2/zxcvbn.js"></script>
    <script src="<c:url value="/resources/js/strength.js" />"></script>
    
    </head>
  
  <body>
    <div class="container">
      <form action="/pawsword/register" method="post">
        <!-- Additional signup form field -->
        <label for="username">Username:</label>
        <input type="text" id="username" placeholder="Username here..." name="username" required/>
        
        <!-- Existing signup form fields (email and password) -->
        <label for="email">Email ID:</label>
        <input type="text" id="email" placeholder="Email here..." name="email" required/>
        
        <label for="password">Password:</label>
        <input type="password" id="password" placeholder="Password here..." name="password" required/>
        
 		<p id="password-strength-text">Panda awaits a secret pawshake. Choose wisely, protect fiercely.</p>
        
        <span class="toggle-password" id="showPasswordBtn">
      		<i class="fas fa-eye" onclick="myFunction()"></i>
    	</span>
        
               
        <button class="button" type="submit">Sign Up</button>
        <!-- Login button to go back to the login page -->
        <button class="button" id="login-btn" type="button">Login</button>
      </form>
             
      <br>
      <br>
      
      
      <!-- Panda face elements -->
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
      <!-- <div class="paw-l"></div>
      <div class="paw-r"></div> -->
    </div>
    
    <!-- Script -->
     
     <script src="<c:url value="/resources/js/script.js" />"></script>
    
    
    <!-- JavaScript code to handle button click and navigate to the login page -->
    <script>
      // Get the login button element
      const loginBtn = document.getElementById("login-btn");

      // Add event listener to handle login button click
      loginBtn.addEventListener("click", () => {
        window.location.href = "/pawsword/login";
      });
    </script>
  </body>
</html>

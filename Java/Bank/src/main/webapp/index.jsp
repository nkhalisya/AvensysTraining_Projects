<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <!DOCTYPE html>
  <html>

  <head>
    <meta charset="ISO-8859-1">
    <title>DUIT | Home</title>
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  	
  	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=IM+Fell+Great+Primer+SC&display=swap" rel="stylesheet">
  	
  	<link type="text/css" rel="stylesheet" href="style.css">
  	<style>
  		
  	</style>
  
  </head>

  <body>
		<!-- Navigation -->
		<nav class="navbar navbar-expand-lg navbar-light">
		  <div class="container-fluid">
		    <a class="navbar-brand" href="#">DUIT</a>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
		      data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
		      aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarSupportedContent">
		      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
		        <li class="nav-item">
		          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
		        </li>
		        <%
		        	// check if logged in
					if(session.getAttribute("id") != null){ // id in session
		        %>
		        <li class="nav-item">
		          <a class="nav-link" href="profile.jsp">Profile</a>
		        </li>
		        <li class="nav-item">
		        	<a class="nav-link" href="logout">Logout</a>
		        </li>
		        <%
					}
					else{ // no id in session
		        %>
		        <li class="nav-item">
		        	<!-- trigger modal -->
		          	<a class="nav-link" href="#" data-bs-toggle="modal" data-bs-target="#exampleModal">Login | Register</a>
		        </li>
		        <%
		        	}
		        %>
		      </ul>
		    </div>
		  </div>
		</nav>
		
		<%
        	// check if logged in
			if(session.getAttribute("id") != null){ // id in session
				String name = session.getAttribute("name").toString();
        %>
		        <h1 class="text-center mt-5"> Hello <% out.println(name); %></h1>
        <%
			}
        %>

		<div id="buttons" class="d-flex flex-row justify-content-around">
			<!-- Check Balance -->
			<a href="checkBalance">
				<span>Balance</span><br>
				<i class="bi bi-wallet-fill"></i>
			</a>
			
			<!-- Add Money -->
			<a href="deposit.jsp">
				<span>Deposit</span><br>
				<i class="bi bi-cash-coin"></i>
			</a>
			
			<!-- Transfer Money -->
			<a href="getAccounts">
				<span>Transfer</span><br>
				<i class="bi bi-arrow-left-right"></i>				
			</a>
		</div>
		
		<img src="images/banner.jpg" class="banner">
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Select</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body d-flex flex-row justify-content-around text-center">
		      
		      	<br>
		      	<div class="indexModal">
		      		<h6>Don't have an account?</h6>
		      		<a href="register.jsp"><button type="button" class="btn btn-primary" id="button">Register</button></a><br>
		      	</div>
		      	<div class="indexModal">
		      		<h6>Already have an account?</h6>
		      		<a href="login.jsp"><button type="button" class="btn btn-primary" id="button">Login</button></a><br>
		      	</div>
		      	<br>
		      	
		      </div>
		    </div>
		  </div>
		</div>

  </body>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

  </html>
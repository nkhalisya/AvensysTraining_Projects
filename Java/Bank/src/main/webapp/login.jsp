<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DUIT | Login</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=IM+Fell+Great+Primer+SC&display=swap" rel="stylesheet">

<link type="text/css" rel="stylesheet" href="style.css">
</head>
<body>
	<%
		if(session.getAttribute("type") != null){ // type in session	
	
		 	// if status = fail
		 	String type = session.getAttribute("type").toString();
			String note = session.getAttribute("note").toString();
			if( type.equals("fail") ){
	%>
	<div class="alert alert-danger alert-dismissible fade show position-fixed" role="alert">
	  <strong>ERROR</strong> <% out.println(note); %>
	  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	<%
			}
			else{ // if status = pass [from register]
	%>
	<div class="alert alert-success alert-dismissible fade show position-fixed" role="alert">
	  <strong>SUCCESS</strong> <% out.println(note); %>
	  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	</div>
	<%
			}
			session.removeAttribute("type");
		}
	%>
	
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-light">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="index.jsp">DUIT</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
	      data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
	      aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link" aria-current="page" href="index.jsp">Home</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link" href="register.jsp">Register</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<div class="logReg">
		<form action="login">
		    <table>
		    	<tr>
		            <td colspan="2" class="text-center"><h1>Login</h1></td>
		        </tr>
		        <tr>
		            <td>Username</td>
		            <td><input type="text" name="username" required></td>
		        </tr>
		        <tr>
		            <td>Password</td>
		            <td><input type="password" name="password" required></td>
		        </tr>
		        <tr>
		            <td colspan="2" class="text-center"><input type="submit" value="LOGIN" id="button" class="btn btn-primary"></td>
		        </tr>
		        <tr>
		            <td colspan="2" class="text-center"><a href="register.jsp">I do not have an account</a></td>
		        </tr>
		        <tr>
		            <td colspan="2" class="text-center"><a href="#" data-bs-toggle="modal" data-bs-target="#staticBackdrop">Reset Password</a></td>
		        </tr>
		    </table>
		</form>
		
		<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="staticBackdropLabel">Enter Email Address</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      
			      <form action="mailReset">
				      <div class="modal-body text-center">
				      	<input type="email" name="email" required>
				      </div>
				      <div class="modal-footer">
				        <button type="button" id="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
				        <button type="submit" id="button" class="btn btn-primary">Confirm</button>
				      </div>
			      </form>
			      
			    </div>
			  </div>
			</div>
			
	</div>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</html>
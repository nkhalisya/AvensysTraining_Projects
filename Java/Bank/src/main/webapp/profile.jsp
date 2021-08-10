<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DUIT | Registration</title>

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
			else{// if status = pass
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
	          <a class="nav-link" href="index.jsp">Home</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link active" aria-current="page" href="profile.jsp">Profile</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link" href="logout">Logout</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<%
		// get values
		// check if user clicked on "retrieve details"
		String input = "disabled";
		String name = "", email = "", account = "", username = "";
		if(session.getAttribute("email") != null){ // details in session	
			input = "";
			name = session.getAttribute("name").toString();
			email = session.getAttribute("email").toString();
			account = session.getAttribute("account").toString();
			username = session.getAttribute("username").toString();
		}
	%>
	
	<form action="changeDetails">
	    <table>
	    	<tr>
	            <td colspan="2" class="text-center"><h1>Edit Particulars</h1></td>
	        </tr>
	        <tr>
	            <td colspan="2" class="text-center"><a href="getDetails">Retrieve Particulars</a></td>
	        </tr>
	        <tr>
	            <td>Name</td>
	            <td><input type="text" name="name"  value="<% out.println(name); %>" required <% out.println(input); %>></td>
	        </tr>
	        <tr>
	            <td>Email</td>
	            <td><input type="email" name="email" value="<% out.println(email); %>" required <% out.println(input); %>></td>
	        </tr>
	        <tr>
	            <td>Account Number</td>
	            <td><input type="text" name="account" value="<% out.println(account); %>" required <% out.println(input); %>></td>
	        </tr>
	        <tr>
	            <td>Username</td>
	            <td><input type="text" name="username" value="<% out.println(username); %>" required <% out.println(input); %>></td>
	        </tr>
	        <tr>
	            <td colspan="2" class="text-center">
	            	<input type="submit" value="UPDATE" id="button" <% out.println(input); %> class="btn btn-primary">
	            </td>
	        </tr>
	        <tr>
	            <td colspan="2" class="text-center">
	            	<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" id="button">
					  Change Password
					</button>
	            </td>
	        </tr>
	    </table>
	</form>
	
	<%
		// name, account set during login
		session.removeAttribute("email");
		session.removeAttribute("username");
	%>
	
	<!-- Modal -->
	<form action="changePassword">
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Change Password</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
		      	
		      	
					<table id="changePassword">
						<tr>
				            <td>Old Password</td>
				            <td><input type="password" name="oldpw" required></td>
				        </tr>
				        <tr>
				            <td>New Password</td>
				            <td><input type="password" name="newpw" required></td>
				        </tr>
				        <tr>
				            <td>Confirm New Password</td>
				            <td><input type="password" name="conpw" required></td>
				        </tr>
				    </table>
				
		      	
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="button">CANCEL</button>
		        <button type="submit" class="btn btn-primary" id="button">UPDATE</button>
		      </div>
		    </div>
		  </div>
		</div>
	</form>
</body>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  	
</html>
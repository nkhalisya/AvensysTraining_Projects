<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DUIT | Reset Password</title>

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
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<div class="logReg">
		<form action="resetPassword">
			<table>
				<tr>
		            <td colspan="2" class="text-center"><h1>Reset Password</h1></td>
		        </tr>
				<tr>
		            <td>Enter OTP from Email</td>
		            <td><input type="text" name="otp" required></td>
		        </tr>
		        <tr>
		            <td>Enter New Password</td>
		            <td><input type="password" name="npw" required></td>
		        </tr>
		        <tr>
		            <td>Confirm New Password</td>
		            <td><input type="password" name="cpw" required></td>
		        </tr>
		        <tr>
		            <td colspan="2" class="text-center">
		            	<input type="submit" class="btn btn-primary" value="UPDATE" id="button">
		            </td>
		        </tr>
		    </table>
		</form>
	</div>
</body>




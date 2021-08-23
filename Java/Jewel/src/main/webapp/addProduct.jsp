<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Jaeda's Jewelry | Admin</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  	
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
	<nav class="navbar sticky-top navbar-expand-lg navbar-light">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Jaeda's Jewel</a>
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
	          <a class="nav-link" href="loadProducts">Products</a>
	        </li>
	        <li class="nav-item">
	          	<a class="nav-link" href="profile.jsp">Profile</a>
	        </li>
	        <li class="nav-item">
	          	<a class="nav-link active" aria-current="page" href="admin.jsp">Admin</a>
	        </li>
	        <li class="nav-item">
	          	<a class="nav-link" href="logout">Logout</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<div>
		<form action="addProduct">
		    <table>
		    	<tr>
		            <td colspan="2" class="text-center"><h1>Add Product</h1></td>
		        </tr>
		        <tr class="text-center">
		        	<td>Image</td>
		        	<td><input type="file" name="img" required></td>
		        </tr>
		        <tr>
		            <td>Name</td>
		            <td><input type="text" name="name" required></td>
		        </tr>
		        <tr>
		            <td>Type</td>
		            <td>
						<select name="type" required>
							<option value="stone">Stone</option>
							<option value="ring">Ring</option>
							<option value="necklace">Necklace</option>
						</select>
					</td>
		        </tr>
		        <tr>
		            <td>Price</td>
		            <td><input type="text" name="price" required></td>
		        </tr>
		        <tr>
		            <td colspan="2" class="text-center">
		            <a href="admin.jsp"><input type="button" value="BACK" id="button" class="btn btn-primary"></a>
		            <input type="submit" value="ADD" id="button" class="btn btn-primary">
		            </td>
		        </tr>
		    </table>
		</form>
			
	</div>
</body>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  
</html>
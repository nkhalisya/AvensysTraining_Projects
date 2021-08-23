<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Jaeda's Jewelry | Cart</title>

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
	          	<a class="nav-link active" aria-current="page" href="getCart">Cart</a>
	        </li>
	        <li class="nav-item">
	          	<a class="nav-link" href="logout">Logout</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<div class="content">
	 	<%@ page import="java.util.ArrayList, com.Model.Cart" %>
	 	
	 	<div class="row">
		 	<!-- CHECKOUT -->
		 	<div class="col-12">
		 	<%
		 		String input = "disabled";
		 		if(session.getAttribute("cart") != null){ // cart in session	
			 		ArrayList<Cart> items = (ArrayList<Cart>)session.getAttribute("cart");
					
			%>
				 	
				<table class="m-auto mt-5">
			 		<tr>
						<th colspan="4" class="text-center pb-4"><h1>Checkout</h1></th>
					</tr>
			 		<tr class="text-center"  style="border-bottom: 1px solid #754847;">
			 			<th>S/N</th>
			 			<th>Name</th>
			 			<th>Quantity</th>
			 			<th>Price</th>
			 		</tr>
			 		<%
			 			int i = 0;
			 			double total = 0;
			 			for( Cart item: items){
			 		%>	 		
			 		<tr>
			 			<td><% out.print(i+1); %>.</td>
			 			<td>
			 				<% out.print(item.name); %>
			 			</td>
			 			<td>
			 				<% out.print(item.qty); %>
			 			</td>
			 			<td>
			 				$<% out.print(item.price); %>
			 			</td>
			 		</tr>
			 		<%
			 				i++;
			 				total += item.price;
						}
			 		%>
			 		<tr style="border-top: 1px solid #754847;">
			 			<td colspan="3" class="text-end">Total Price:</td>
			 			<td>$<% out.print(total); %></td>
			 		</tr>
			 		<tr>
			 			<td colspan="4" class="text-center">
			 				<a href="cancelCheckout"><button type="button" id="button" class="btn btn-primary">Cancel</button></a>
			 				<a href="confirmCheckout"><button type="button" id="button" class="btn btn-primary">Confirm Checkout</button></a>
			 			</td>
			 		</tr>
				</table>
				 	
		 	<%
		 		}
		 	%>
		 	</div>
	 	</div>
	</div>
	
</body>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</html>
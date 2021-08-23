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
	 	<%@ page import="java.util.ArrayList, com.Model.Detail" %>
	 	
	 	<div class="row">
	 		
	 	<%
	 		if(session.getAttribute("details") != null){ // cart in session	
		 		ArrayList<Detail> items = (ArrayList<Detail>)session.getAttribute("details");
	 			String date = session.getAttribute("date").toString();
	 			double price = Double.parseDouble(session.getAttribute("price").toString());
		%>
	 	
	 	<!-- DETAILS -->
	 	<div class="col-9">
	 	<a href="getCheckouts"><button type="button" id="button" class="btn btn-primary">Back</button></a>
	 		<table class="me-5 ms-5 mt-4" style="margin:0;">
		 		<tr>
					<th colspan="5" class="text-start pb-4"><h1>Checkout Details</h1></th>
				</tr>
				<tr>
					<th>TIMESTAMP:</th>
					<td><% out.print(date); %></td>
				</tr>
				<tr>
					<th>TOTAL:</th>
					<td>$<% out.print(price); %></td>
				</tr>
		 		<tr class="text-center"  style="border-bottom: 1px solid #754847;">
		 			<th>S/N</th>
		 			<th>Image</th>
		 			<th>Name</th>
		 			<th>Quantity</th>
		 			<th>Price</th>
		 		</tr>
		 		<%
		 			int i = 0;
		 			for( Detail item: items){
		 		%>	 		
		 		<tr>
		 			<td class="text-center"><% out.print(i+1); %>.</td>
		 			<td class="images">
		 				<img src="images/<% out.print(item.type); %>s/<% out.print(item.img); %>">
		 			</td>
		 			<td>
		 				<% out.print(item.name); %>
		 			</td>
		 			<td class="text-center">
		 				<% out.print(item.qty); %>
		 			</td>
		 			<td>
		 				$<% out.print(item.price); %>
		 			</td>
		 		</tr>
		 		<%
		 				i++;
					}
		 		%>
			</table>
		</div>
	 	<%
	 		}
	 		session.removeAttribute("date");
	 		session.removeAttribute("price");
	 	%>
	 	</div> <!-- end row -->
	</div>
	
</body>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script>	
	
		function editState( number, total ){
			// show button
			var updateBtn = document.getElementById("update"+number);
			updateBtn.style.display = 'table-cell';
			
			// show cancel
			var closeIcon = document.getElementById("close"+number);
			closeIcon.style.display = 'table-cell';
			
			// activate input
			var qty = document.getElementById("qty"+number);
			qty.disabled = false;
			
			// hide editIcon and binIcon
			var editIcon = document.getElementById("edit"+number);
			editIcon.style.display = 'none';
			var binIcon = document.getElementById("bin"+number);
			binIcon.style.display = 'none';
			
			// disable other edit buttons
			rest(total, number, 'none');
			
		}
		
		function cancelState( number, total ){
			// hide button
			var updateBtn = document.getElementById("update"+number);
			updateBtn.style.display = 'none';
			
			// hide cancel
			var closeIcon = document.getElementById("close"+number);
			closeIcon.style.display = 'none';
			
			// disable input
			var qty = document.getElementById("qty"+number);
			qty.disabled = true;
			
			// show editIcon and binIcon
			var editIcon = document.getElementById("edit"+number);
			editIcon.style.display = 'table-cell';
			var binIcon = document.getElementById("bin"+number);
			binIcon.style.display = 'table-cell';
			
			// able other edit buttons
			rest(total, number, 'table-cell');
		}

		function rest( total, current, displaytype ){
			for( var i = 1 ; i <= total ; i++ ){
				if( i != current ){
					var edit = document.getElementById("edit"+i);
					edit.style.display = displaytype;
					var binIcon = document.getElementById("bin"+i);
					binIcon.style.display = displaytype;
				}
			}
		}
			
	</script>

</html>
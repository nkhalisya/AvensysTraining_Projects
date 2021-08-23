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
	          	<a class="nav-link active" aria-current="page" href="admin.jsp">Admin</a>
	        </li>
	        <li class="nav-item">
	          	<a class="nav-link" href="logout">Logout</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<div class="content">
	 	<%@ page import="java.util.ArrayList, com.Model.User, com.Model.Product" %>
	 	
	 	<!-- USERS -->
	 	<div>
	 	<%
	 		String input = "disabled";
	 		if(session.getAttribute("users") != null){ // users in session	
		 		ArrayList<User> users = (ArrayList<User>)session.getAttribute("users");
				
		%>
			 	
			<table class="m-5" style="margin:0;">
		 		<tr>
					<th colspan="5" class="text-start pb-4"><h1>Users</h1></th>
				</tr>
		 		<tr class="text-center" style="border-bottom: 1px solid #754847;">
		 			<th>S/N</th>
		 			<th>Name</th>
		 			<th>Email</th>
		 			<th>Username</th>
		 			<th>Role</th>
		 		</tr>
		 		<%
		 			int i = 0;
		 			for( User user: users){
		 		%>	 		
		 	<form action="manageUsers">
		 		<tr>
		 			<td><% out.print(i+1); %>.</td>
		 			<td>
		 				<input type="text" name="name<% out.print(user.id); %>" id="name<% out.print(i+1); %>" value="<% out.print(user.name); %>" required <% out.println(input); %>>
		 			</td>
		 			<td>
		 				<input type="email" name="email<% out.print(user.id); %>" id="email<% out.print(i+1); %>" value="<% out.print(user.email); %>" required <% out.println(input); %>>
		 			</td>
		 			<td>
		 				<input type="text" name="username<% out.print(user.id); %>" id="uname<% out.print(i+1); %>" value="<% out.print(user.username); %>" required <% out.println(input); %>>
		 			</td>
		 			<td>
						<select name="role<% out.print(user.id); %>" id="role<% out.print(i+1); %>" required <% out.println(input); %>>
							<option value="<% out.print(user.role); %>" selected disabled><% out.print(user.role); %></option>
							<option value="admin">Admin</option>
							<option value="member">Member</option>
						</select>
						<input type="hidden" name="roleHidden<% out.print(user.id); %>" value="<% out.print(user.role); %>">
					</td>
		 			<td id="edit<% out.print(i+1); %>">
		 				<a href="#" onclick="editState('<% out.print(i+1); %>', '<% out.print(users.size()); %>', 'users')">
		 					<i class="bi bi-pencil-square"></i>
		 				</a>
		 			</td>
		 			<td id="bin<% out.print(i+1); %>">
		 				<a href="deleteUser?id=<% out.print(user.id); %>">
		 					<i class="bi bi-trash-fill"></i>
		 				</a>
		 			</td>
		 			<td id="update<% out.print(i+1); %>" style="display:none;">
		 				<input type="hidden" name="idHidden" value="<% out.print(user.id); %>">
		 				<button type="submit" id="button" class="btn btn-primary">Update</button>
		 			</td>
		 			<td id="close<% out.print(i+1); %>" style="display:none;">
		 				<a href="#" onclick="cancelState('<% out.print(i+1); %>', '<% out.print(users.size()); %>', 'users')">
		 					<i class="bi bi-x-circle-fill"></i>
		 				</a>
		 			</td>
		 		</tr>
		 	</form>
		 		<%
		 				i++;
					}
		 		%>
			</table>
			 	
	 	<%
	 		}
	 		else{
	 	%>
	 			<a href="getUsers"><button type="button" id="button" class="btn btn-primary">View Users</button></a>
	 	<%
	 		}
	 		session.removeAttribute("users");
	 	%>
	 	</div>
	 	
	 	<a href="addProduct.jsp"><button type="button" id="button" class="btn btn-primary">Add Product</button></a>
	 	
	 	<!-- PRODUCTS -->
	 	<div>
	 	<%
	 		if(session.getAttribute("products") != null){ // products in session	
		 		ArrayList<Product> products = (ArrayList<Product>)session.getAttribute("products");
				
		%>
			 	
			<table class="m-5" style="margin:0;">
		 		<tr>
					<th colspan="5" class="text-start pb-4"><h1>Products</h1></th>
				</tr>
		 		<tr class="text-center" style="border-bottom: 1px solid #754847;">
		 			<th>S/N</th>
		 			<th>Image</th>
		 			<th>Name</th>
		 			<th>Type</th>
		 			<th>Price</th>
		 		</tr>
		 		<%
		 			int i = 0;
		 			for( Product product: products){
		 		%>	 		
		 	<form action="manageProducts">
		 		<tr>
		 			<td><% out.print(i+1); %>.</td>
		 			<td class="images">
		 				<img src="images/<% out.print(product.type); %>s/<% out.print(product.img); %>">
		 				<input type="hidden" name="imgHidden<% out.print(product.id); %>" value="<% out.print(product.img); %>">
		 			</td>
		 			<td>
		 				<input type="text" name="name<% out.print(product.id); %>" id="name<% out.print(i+1); %>" value="<% out.print(product.name); %>" required <% out.println(input); %>>
		 			</td>
		 			<td>
						<select name="type<% out.print(product.id); %>" id="type<% out.print(i+1); %>" required <% out.println(input); %>>
							<option value="<% out.print(product.type); %>" selected disabled><% out.print(product.type); %></option>
							<option value="stone">Stone</option>
							<option value="ring">Ring</option>
							<option value="necklace">Necklace</option>
						</select>
						<input type="hidden" name="typeHidden<% out.print(product.id); %>" value="<% out.print(product.type); %>">
					</td>
		 			<td>
		 				<input type="text" name="price<% out.print(product.id); %>" id="price<% out.print(i+1); %>" value="<% out.print(product.price); %>" required <% out.println(input); %>>
		 			</td>
		 			
		 			<td id="edit<% out.print(i+1); %>">
		 				<a href="#" onclick="editState('<% out.print(i+1); %>', '<% out.print(products.size()); %>','products')">
		 					<i class="bi bi-pencil-square"></i>
		 				</a>
		 			</td>
		 			<td id="bin<% out.print(i+1); %>">
		 				<a href="deleteProduct?id=<% out.print(product.id); %>">
		 					<i class="bi bi-trash-fill"></i>
		 				</a>
		 			</td>
		 			<td id="update<% out.print(i+1); %>" style="display:none;">
		 				<input type="hidden" name="idHidden" value="<% out.print(product.id); %>">
		 				<button type="submit" id="button" class="btn btn-primary">Update</button>
		 			</td>
		 			<td id="close<% out.print(i+1); %>" style="display:none;">
		 				<a href="#" onclick="cancelState('<% out.print(i+1); %>', '<% out.print(products.size()); %>', 'products')">
		 					<i class="bi bi-x-circle-fill"></i>
		 				</a>
		 			</td>
		 		</tr>
		 	</form>
		 		<%
		 				i++;
					}
		 		%>
			</table>
			 	
	 	<%
	 		}
	 		else{
	 	%>
	 			<a href="getProducts"><button type="button" id="button" class="btn btn-primary">View Products</button></a>
	 	<%
	 		}
	 		session.removeAttribute("products");
	 	%>
	 	</div>
		
	</div>
	
</body>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script>	
	
		function editState( number, total, section ){
			// show button
			var updateBtn = document.getElementById("update"+number);
			updateBtn.style.display = 'table-cell';
			
			// show cancel
			var closeIcon = document.getElementById("close"+number);
			closeIcon.style.display = 'table-cell';
			
			// activate input
			var name = document.getElementById("name"+number);
			name.disabled = false;
			if( section == "users" ){
				var email = document.getElementById("email"+number);
				email.disabled = false;
				var uname = document.getElementById("uname"+number);
				uname.disabled = false;
				var role = document.getElementById("role"+number);
				role.disabled = false;
			}
			else if( section == "products" ){
				var type = document.getElementById("type"+number);
				type.disabled = false;
				var price = document.getElementById("price"+number);
				price.disabled = false;
			}
			
			// hide editIcon and binIcon
			var editIcon = document.getElementById("edit"+number);
			editIcon.style.display = 'none';
			var binIcon = document.getElementById("bin"+number);
			binIcon.style.display = 'none';
			
			// disable other edit buttons
			rest(total, number, 'none');
			
		}
		
		function cancelState( number, total, section ){
			// hide button
			var updateBtn = document.getElementById("update"+number);
			updateBtn.style.display = 'none';
			
			// hide cancel
			var closeIcon = document.getElementById("close"+number);
			closeIcon.style.display = 'none';
			
			// disable input
			var name = document.getElementById("name"+number);
			name.disabled = true;
			if( section == "users" ){
				var email = document.getElementById("email"+number);
				email.disabled = true;
				var uname = document.getElementById("uname"+number);
				uname.disabled = true;
				var role = document.getElementById("role"+number);
				role.disabled = true;
			}
			else if( section == "products" ){
				var type = document.getElementById("type"+number);
				type.disabled = true;
				var price = document.getElementById("price"+number);
				price.disabled = true;
			}
			
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
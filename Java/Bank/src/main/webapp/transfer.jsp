<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DUIT | Transfer</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=IM+Fell+Great+Primer+SC&display=swap" rel="stylesheet">
	
	<link type="text/css" rel="stylesheet" href="style.css">
</head>
<body>

	<%
		if(session.getAttribute("id") == null){ // id not in session	
			// redirect to login.html
			response.sendRedirect("login.jsp");
		}
		
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
	          <a class="nav-link" href="index.jsp">Home</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link" href="checkBalance">Balance</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link" href="deposit.jsp">Deposit</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link active" aria-current="page" href="getAccounts">Transfer</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link" href="profile.jsp">Profile</a>
	        </li>
	        <li class="nav-item">
	        	<a class="nav-link" href="logout">Logout</a>
	        </li>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<div class="content">
	 	<%@ page import="java.util.ArrayList" %>
	 	
	 	<%
	 		if(session.getAttribute("accounts") != null){ // accounts in session	
		 		ArrayList<String> names = (ArrayList<String>)session.getAttribute("names");
		 		ArrayList<String> accounts = (ArrayList<String>)session.getAttribute("accounts");
				
		%>
			 	<form action="transfer">
				 	<table>
				 		<tr>
							<th colspan="4" class="text-start"><h1>Transfer Money</h1></th>
						</tr>
						<tr>
							<td colspan="4" class="center">
				            	<a href="#" onclick="toggle('list', 'input')">View Accounts</a> | 
				            	<a href="#" onclick="toggle('input', 'list')">Enter Account Number</a>
				            </td>
				 		</tr>
				 		<tr class="list">
				 			<th>S/N</th>
				 			<th>Name</th>
				 			<th>Account Number</th>
				 			<th>Select</th>
				 		</tr>
				 		<%
				 			for( int i = 0 ; i < accounts.size() ; i++ ){
				 		%>	 		
				 		<tr class="list">
				 			<td><% out.print(i+1); %>.</td>
				 			<td><% out.print(names.get(i)); %></td>
				 			<td><% out.print(accounts.get(i)); %></td>
				 			<td class="text-center"><input type="radio" name="accountId" value="<% out.print(accounts.get(i));%>"></td>
				 		</tr>
				 		<%
							}
				 		%>
				 		<tr class="input">
							<td colspan="2">Enter Account Number: </td>
							<td colspan="2">&nbsp;&nbsp;&nbsp;<input type="text" name="account" min="1"></td>
						</tr>
				 		<tr>
							<td colspan="2">Insert amount to transfer: </td>
							<td colspan="2">$ <input id="account" type="number" name="amount" min="1" disabled></td>
						</tr>
				 		<tr>
				 			<!-- Button trigger modal -->
							<td colspan="4"><input value="TRANSFER" id="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop" disabled></td>
				 		</tr>
				 	</table>
				 	
				 	<!-- Modal -->
					<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					  <div class="modal-dialog">
					    <div class="modal-content">
					      <div class="modal-header">
					        <h5 class="modal-title" id="staticBackdropLabel">Enter Password</h5>
					        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      </div>
					      <div class="modal-body text-center">
					      	<input type="password" name="password" required>
					      </div>
					      <div class="modal-footer">
					        <button type="button" id="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
					        <button type="submit" id="button" class="btn btn-primary">Confirm</button>
					      </div>
					    </div>
					  </div>
					</div>
			 	</form>
	 	<%
	 		}
	 		else{
	 	%>
	 			<p>No Accounts</p>
	 	<%
	 		}
	 	%>
		
	</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>	
	function toggle( showName, HideName ){
		// change header
		
		// view form
		var s = document.getElementsByClassName(showName);
		for (i = 0; i < s.length; i++) {
			s[i].style.display = 'table-row';
		}
		
		// Hide
		var h = document.getElementsByClassName(HideName);
		for (i = 0; i < h.length; i++) {
			h[i].style.display = 'none';
		}
		
		// activat buttons
		var acc = document.getElementById("account");
		acc.disabled = false;
		var btn = document.getElementById("button");
		btn.disabled = false;
		
		
		return true;
	}
</script>
</html>
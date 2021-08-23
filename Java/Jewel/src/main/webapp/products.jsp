<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Jaeda's Jewelry | Products</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  	
  	<link type="text/css" rel="stylesheet" href="style.css">
	
</head>
<body>

	<%
		String role = "";
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

	<header>
		<!-- marquee -->
	    <marquee direction="left" scrollamount="12" class="center">
	        Sale <b>20%</b> Off! | <b>FREE</b> standard shipping over $100 | Deliver <b>Everywhere</b> | <b>EASY</b> RETURNS
	    </marquee>
	
	    <!-- Logo -->
	    <div id="logo" class="d-flex flex-row">
	        <img src="images/logo.jpg" title="Jaeda Jewelry Logo" alt="Jaeda Jewelry Logo unavailable">
	        <h1 class="align-self-center ps-4">Jaeda's Jewel</h1>
	    </div>
	</header>
	
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
	          <a class="nav-link active" aria-current="page" href="loadProducts">Products</a>
	        </li>
	        <%
	        	// check if logged in
				if(session.getAttribute("id") != null){ // id in session
					role = session.getAttribute("role").toString(); // admin
	        %>
	        <li class="nav-item">
	          <a class="nav-link" href="profile.jsp">Profile</a>
	        </li>
	        <%
		        
	    		if(role.equals("admin")){
	        %>
	        <li class="nav-item">
   	          <a class="nav-link" href="admin.jsp">Admin</a>
   	        </li>
	        <%
	    		}
	    		else{
	        %>
	        <li class="nav-item">
   	          <a class="nav-link" href="getCart">Cart</a>
   	        </li>
	        <%
	    		}
	        %>
	        <li class="nav-item">
	        	<a class="nav-link" href="logout">Logout</a>
	        </li>
	        <%
				}
				else{ // no id in session
	        %>
	        <li class="nav-item">
	          	<a class="nav-link" href="login.jsp">Login</a>
	        </li>
	        <li class="nav-item">
	          	<a class="nav-link" href="register.jsp">Register</a>
	        </li>
	        <%
	        	}
	        %>
	      </ul>
	    </div>
	  </div>
	</nav>
	
	<!-- Product Information 1 -->
    <div class="content">
        <div class="d-flex flex-row justify-content-around text-center">
            <div class="catImg">
            	<a href="loadProducts?type=stone">
                	<img src="images/stones/stoneHome.jpg" title="stone" alt="stone image unavailable" class="zoom"><br>
                	Stones
                </a>
            </div>
            <div class="catImg">
            	<a href="loadProducts?type=ring">
                	<img src="images/rings/ringHome.jpg" title="ring" alt="ring image unavailable" class="zoom"><br>
                	Rings
                </a>
            </div>
            <div class="catImg">
            	<a href="loadProducts?type=necklace">
                	<img src="images/necklaces/necklaceHome.jpg" title="necklace" alt="necklace image unavailable" class="zoom"><br>
                	Necklaces
                </a>
            </div>
        </div>
    </div>
    
    <%@ page import="java.util.ArrayList, com.Model.User, com.Model.Product" %>
	
	<!-- LOOP HERE FOR EACH CATEGORY -->
											
	<%
		ArrayList<Product> products = null;
		String category = "";
		if(session.getAttribute("stones") != null){
			products = (ArrayList<Product>)session.getAttribute("stones");
			category = "stone";
		}
		else if(session.getAttribute("rings") != null){
			products = (ArrayList<Product>)session.getAttribute("rings");
			category = "ring";
		}
		else if(session.getAttribute("necklaces") != null){
			products = (ArrayList<Product>)session.getAttribute("necklaces");
			category = "necklace";
		}
		else if(session.getAttribute("products") != null){
			products = (ArrayList<Product>)session.getAttribute("products");
			category = "Product";
		}
		
	 		if(products != null){ // products in sessio
	 			boolean rowOpen = false;
	 			int count = 0; 	
	%>
	
	<%
		// dont show banner if All Products is selected
		if( !category.equals("Product") ){
	%>
	<!-- Stones Header + Banner -->
    <img id="banner" src="images/<% out.print(category); %>s/<% out.print(category); %>Banner.jpg" title="banner" alt="banner unavailable">
    <% 
    	}
    %>
    
    
    <!-- Images -->
    <div class="content" id="stones">
        <h1 class="text-center pt-5">Our <% out.print(category); %>s</h1>
        
        <% 
        	for( Product product: products){
        		count++; 
        		int id = product.id;
	 			String name = product.name;
	 			String img = product.img;
	 			String type = product.type;
	 			double price = product.price;
        		
        		if( count%4 == 1 ){ // first image of the row
        			%>
        			<!-- 4 images per row -->
        	        <div class="d-flex flex-row justify-content-around text-center m-5">
        	        <%
        	        rowOpen = true;
        		}
        			
       			// images
       			%>
       			<div class="images">
	                <a href="">
	                    <img src="images/<% out.print(type); %>s/<% out.print(img); %>" title="<% out.print(name); %>" class="zoom">
	                </a>
	                <div class="d-flex flex-row justify-content-around">
		                <div class="ms-3 me-3">
		                	<% out.print(name); %><br>$<% out.print(price); %>
		                </div>
		                <%
		                // display +- when role = "member" [admin and anons cannot +-]
	                	if(role.equals("member")){
	                	%>
		                <a href="cart?id=<% out.print(id); %>"><i class="bi bi-plus-circle-fill me-4"></i></a>
		                <%
	                	}
	                	%>
	                </div>
	                
	                
	            </div>
       			<%
       			
       			if( count%4 == 0 ){ // last image of the row
       				%>
        			</div>
        			<% 
        			rowOpen = false;
       			}
       			
        	} // end of for loop
        	if(rowOpen){
        		%>
    			</div>
    			<% 
        	}
	 	}// end of if
	 		
	 		session.removeAttribute("stones");
	 		session.removeAttribute("rings");
	 		session.removeAttribute("necklaces");
	 		//session.removeAttribute("products");
	%>
	
    
    <!-- Footer -->
    <footer class="d-flex flex-row justify-content-around text-center">
        <!-- Column 1 -->
        <div>
            <ul>
                <li><b>Membership</b></li>
                <ul>
                    <li>Jaeda Club</li>
                    <li><abbr title="Jaeda Crystal Club">JCC</abbr></li>
                </ul>
            </ul>
        </div>

        <!-- Column 2 -->
        <div>
            <ul>
                <li><b>About Us</b></li>
                <ul>
                    <li><a href="jewelry_about.html" title="about us page">About Us</a></li>
                    <li>Jobs & Career</li>
                    <li>Jaeda Mobility</li>
                    <li>Corporate Gifts</li>
                    <li>For Professionals</li>
                    <li>Sustainability</li>
                    <li>Sustainability Commitments</li>
                    <li>Sitemap</li>
                    <li>Jaeda Created Diamonds</li>
                </ul>
            </ul>
        </div>

        <!-- Column 3 -->
        <div>
            <ul>
                <li><b>Customer Care</b></li>
                <ul>
                    <li><a href="jewelry_contact.html" title="contact page">Contact Us</a></li>
                    <li><abbr title="Frequently Asked Questions">FAQ</abbr></li>
                    <li>Shipping Returns</li>
                    <li>Product Care & Repair</li>
                    <li>COVID-19</li>
                </ul>
            </ul>
        </div>

        <!-- Column 4 -->
        <div>
            <ul>
                <li><b>Legal Use</b></li>
                <ul>
                    <li>Term Of Use</li>
                    <li>Terms & Conditions</li>
                    <li>Privacy Policy</li>
                    <li>Imprint</li>
                </ul>
            </ul>
        </div>
    </footer>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  
</html>
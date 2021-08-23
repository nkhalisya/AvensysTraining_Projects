<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Jaeda's Jewelry | Home</title>

	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  	
  	<link type="text/css" rel="stylesheet" href="style.css">
  	
</head>
<body>
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
	          <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" href="loadProducts">Products</a>
	        </li>
	        <%
	        	// check if logged in
				if(session.getAttribute("id") != null){ // id in session
	        %>
	        <li class="nav-item">
	          <a class="nav-link" href="profile.jsp">Profile</a>
	        </li>
	        <%
		        String role = session.getAttribute("role").toString(); // admin
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
	
	<!-- Greeting -->
	<%
	    // check if logged in
		if(session.getAttribute("id") != null){ // id in session
			String name = session.getAttribute("name").toString();
	%>
		<h1 class="text-center mt-5"> Hello <% out.print(name); %></h1>
	<%
		}
	%>
	
	<!-- Banner -->
    <img id="banner" src="images/banner.jpg" title="banner" alt="banner unavailable">
    <hr>

    <!-- Information -->
    <div class="content">
        <h1>Precious Stones</h1>
        <p>
            Discover a world of wonder in our Swarovski Summer Sale. Shop our online store and choose from a selection of exquisitely crafted pieces, from Swiss made watches to brilliant earrings and elegant rings. Celebrate your style with new jewelry pieces or add crystal ornaments to your home decor. Our products embody over 125 years of meticulous crystal craftsmanship, for an incredible dose of style infused with artistry. 
        </p>
    </div>
    <hr>

    <!-- Home Information 1 -->
    <div class="content">
        <h1>Shop by Category</h1>
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

    <!-- Home Information 2 -->
    <blockquote class="text-center m-5 p-5">
        <q>Jewelery has the power to be the one little thing that makes you feel <i><u>unique</u></i></q>
        <br>
        <i>-Elizabeth Taylor</i>
    </blockquote>

    <!-- Home Information 3 -->
    <div class="content">
        <h2 class="text-center pt-3">Trending Now</h2>
        <div class="d-flex flex-row justify-content-around">
            <div class="images">
                <a href="loadProducts?type=stone">
                    <img src="images/stones/stone1.jpg" title="red gem" alt="red gem" class="zoom">
                </a>
            </div>
            <div class="images">
                <a href="loadProducts?type=ring">
                    <img src="images/rings/ring1.jpg" title="gold ring" alt="gold ring" class="zoom">
                </a>
            </div>
            <div class="images">
                <a href="loadProducts?type=stone">
                    <img src="images/stones/stone2.jpg" title="gold necklace" alt="gold necklace" class="zoom">
                </a>
            </div>
            <div class="images">
                <a href="loadProducts?type=necklace">
                    <img src="images/necklaces/necklace1.jpg" title="gold necklace" alt="gold necklace" class="zoom">
                </a>
            </div>
        </div>
        <div class="d-flex flex-row justify-content-around">
            <div class="images">
                <a href="loadProducts?type=ring">
                    <img src="images/rings/ring7.jpg" title="red gem" alt="red gem" class="zoom">
                </a>
            </div>
            <div class="images">
                <a href="loadProducts?type=necklace">
                    <img src="images/necklaces/necklace3.jpg" title="gold ring" alt="gold ring" class="zoom">
                </a>
            </div>
            <div class="images">
                <a href="loadProducts?type=stone">
                    <img src="images/stones/stone5.jpg" title="gold necklace" alt="gold necklace" class="zoom">
                </a>
            </div>
            <div class="images">
                <a href="loadProducts?type=necklace">
                    <img src="images/necklaces/necklace7.jpg" title="gold necklace" alt="gold necklace" class="zoom">
                </a>
            </div>
        </div>
    </div>
	
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
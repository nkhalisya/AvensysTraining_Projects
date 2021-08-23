<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Page</title>
</head>
<body>
		<!-- TO TEST PAGE -->
		<%
			String display = session.getAttribute("display").toString();
			String type = session.getAttribute("type").toString();
		%>
			<p><% out.println(type + ": " + display); %></p>
		<%
			if( type.equals("register")){
				// register pass -- login
		%>
				<a href="login.html">Login</a>
		<%
			}
			else if( type.equals("login")){
				// login pass -- logout
				String name = session.getAttribute("name").toString();
		%>
				<p><% out.println("Hello " + name); %></p>
				<form action="logout">
					<input type="submit" value="LOGOUT">
				</form>
		<%
			}
			else if( type.equals("logout")){
		%>
				<a href="index.html">Logged out, back to start</a>
		<%
			}
		%>
</body>
</html>
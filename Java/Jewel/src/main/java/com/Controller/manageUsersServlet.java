package com.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Model;
import com.Model.User;

/**
 * Servlet implementation class manageUsersServlet
 */
@WebServlet("/manageUsers")
public class manageUsersServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		int adminId = Integer.parseInt(session.getAttribute("id").toString());
		
		int id = Integer.parseInt(req.getParameter("idHidden").toString());
				
		String name = req.getParameter("name"+id);
		String email = req.getParameter("email"+id);
		String username = req.getParameter("username"+id);
		String	role = req.getParameter("roleHidden"+id);
		if(req.getParameter("role"+id) != null) {
			role = req.getParameter("role"+id);
		}

		User u = new User();
		u.setId(id);
		
		Model m = new Model();
		m.setUser(u);
		
		int updated = 0;
		// get db variables
		boolean user = m.getUser("id");
		User dbUser = m.getUser();
		String dbName = dbUser.getName(); //m.getName();
		String dbEmail = dbUser.getEmail();
		String dbUsername = dbUser.getUsername();
		String dbRole = dbUser.getRole();
		
		// if name not same -> update
		if( !name.equals(dbName) ) {
			u.setName(name); 
			m.setUser(u);
			updated += m.edit("name");
		}
		
		// if email not same -> update
		if( !email.equals(dbEmail) ) {
			u.setEmail(email);
			m.setUser(u);
			updated += m.edit("email");
		}
		
		// if username not same -> update
		if( !username.equals(dbUsername) ) {
			u.setUsername(username);
			m.setUser(u);
			updated += m.edit("username");
		}
		
		// if role not same -> update
		if( !role.equals(dbRole) ) {
			u.setRole(role);
			m.setUser(u);
			updated += m.edit("role");
		}
		
		if( updated == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to update user");
		}
		else {			
			// update session of users
			u.setId(adminId);
			ArrayList<User> users = m.getUsers();
			session.setAttribute("users", users);
			
			session.setAttribute("type", "pass");
			session.setAttribute("note", "User succesfully updated");
		}
		resp.sendRedirect("admin.jsp");
		
	}
}

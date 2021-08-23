package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Model;
import com.Model.User;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Model m = new Model();
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		
		m.setUser(u);
		
		boolean user = m.login();
		if(user) {
			u = m.getUser();
			int id = u.getId();
			String name = u.getName();
			String role = u.getRole();
			
			session.setAttribute("id", id); // for user
			session.setAttribute("name", name); // for index
			session.setAttribute("role", role); // for admin
			
			resp.sendRedirect("index.jsp");
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to login");
			resp.sendRedirect("login.jsp");
		}
		
	}

}

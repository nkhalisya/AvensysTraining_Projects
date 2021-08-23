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
 * Servlet implementation class getUsersServlet
 */
@WebServlet("/getUsers")
public class getUsersServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register

		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		User u = new User();
		u.setId(id);
		
		Model m = new Model();
		m.setUser(u);
		
		ArrayList<User> users = m.getUsers();
		
		if(users.size() > 0) {
			session.setAttribute("users", users);
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "There are no users");
		}
		resp.sendRedirect("admin.jsp");
	}

}

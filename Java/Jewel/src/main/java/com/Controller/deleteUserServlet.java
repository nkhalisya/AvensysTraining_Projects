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
 * Servlet implementation class deleteUserServlet
 */
@WebServlet("/deleteUser")
public class deleteUserServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		int adminId = Integer.parseInt(session.getAttribute("id").toString());
		
		int id = Integer.parseInt(req.getParameter("id").toString());
		
		User u = new User();
		u.setId(id);
		
		Model m = new Model();
		m.setUser(u);
		
		int deleted = m.delete();
		if( deleted == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to delete user");
		}
		else {			
			// update session of users
			u.setId(adminId);
			ArrayList<User> users = m.getUsers();
			session.setAttribute("users", users);
			
			session.setAttribute("type", "pass");
			session.setAttribute("note", "User deleted");
		}
		resp.sendRedirect("admin.jsp");
	}

}

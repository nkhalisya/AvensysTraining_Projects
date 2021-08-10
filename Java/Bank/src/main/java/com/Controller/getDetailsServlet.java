package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Model;

/**
 * Servlet implementation class getDetailsServlet
 */
@WebServlet("/getDetails")
public class getDetailsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		Model m = new Model();
		m.setId(id);
		
		boolean user = m.getUser();
		if(user) {
			// name set during LOGIN
			String email = m.getEmail();
			String account = m.getAccount();
			String username = m.getUsername();
			
			session.setAttribute("email", email);
			session.setAttribute("account", account);
			session.setAttribute("username", username);
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to retrieve details");
		}
		resp.sendRedirect("profile.jsp");
	}

}

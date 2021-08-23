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
 * Servlet implementation class getDetailsServlet
 */
@WebServlet("/getDetails")
public class getDetailsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		Model m = new Model();
		User u = new User();
		u.setId(id);
		m.setUser(u);
		
		boolean user = m.getUser("id");
		if(user) {
			User dbUser = m.getUser();
			String name = dbUser.getName();
			String email = dbUser.getEmail();
			String cardno = dbUser.getCardno();
			String cardtype = dbUser.getCardtype();
			String username = dbUser.getUsername();
			
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("cardno", cardno);
			session.setAttribute("cardtype", cardtype);
			session.setAttribute("username", username);
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to retrieve details");
		}
		resp.sendRedirect("profile.jsp");
	}

}

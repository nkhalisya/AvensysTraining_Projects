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
 * Servlet implementation class editDetailsServlet
 */
@WebServlet("/edit")
public class editDetailsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String cardno = req.getParameter("cardno");
		String cardtype = req.getParameter("cardtypeHidden");
		if(req.getParameter("cardtype") != null) {
			cardtype = req.getParameter("cardtype");
		}
		String username = req.getParameter("username");
				
		Model m = new Model();
		User u = new User();
		u.setId(id);
		m.setUser(u);
		
		int updated = 0;
		// get db variables
		boolean user = m.getUser("id");
		User dbUser = m.getUser();
		String dbName = dbUser.getName();
		String dbEmail = dbUser.getEmail();
		String dbCardno = dbUser.getCardno();
		String dbCardtype = dbUser.getCardtype();
		String dbUsername = dbUser.getUsername();
		
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
		
		// if cardno not same -> update
		if( !cardno.equals(dbCardno) ) {
			u.setCardno(cardno);
			m.setUser(u);
			updated += m.edit("cardno");
		}
		
		// if cardtype not same -> update
		if( !cardtype.equals(dbCardtype) ) {
			u.setCardtype(cardtype);
			m.setUser(u);
			updated += m.edit("cardtype");
		}
		
		// if username not same -> update
		if( !username.equals(dbUsername) ) {
			u.setUsername(username);
			m.setUser(u);
			updated += m.edit("username");
		}
		
		if( updated == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to update profile");
		}
		else {
			// reset attributes to new values [sessions from login]
			session.setAttribute("name", name);
			
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Profile succesfully updated");
		}
		resp.sendRedirect("profile.jsp");
		
	}

}

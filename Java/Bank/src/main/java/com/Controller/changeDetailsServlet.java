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
 * Servlet implementation class changeDetailsServlet
 */
@WebServlet("/changeDetails")
public class changeDetailsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String account = req.getParameter("account");
		String username = req.getParameter("username");
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		Model m = new Model();
		m.setId(id);
		
		int updated = 0;
		// get db variables
		boolean user = m.getUser();
		String dbName = m.getName();
		String dbEmail = m.getEmail();
		String dbAccount = m.getAccount();
		String dbUsername = m.getUsername();
		
		// if name not same -> update
		if( !name.equals(dbName) ) {
			m.setName(name);
			updated += m.edit("name");
		}
		
		// if email not same -> update
		if( !email.equals(dbEmail) ) {
			m.setEmail(email);
			updated += m.edit("email");
		}
		
		// if account not same -> update
		if( !account.equals(dbAccount) ) {
			m.setAccount(account);
			updated += m.edit("account");
		}
		
		// if username not same -> update
		if( !username.equals(dbUsername) ) {
			m.setUsername(username);
			updated += m.edit("username");
		}
		
		if( updated == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to update profile");
		}
		else {
			// reset attributes to new values
			session.setAttribute("name", name);
			
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Profile succesfully updated");
		}
		resp.sendRedirect("profile.jsp");
	}

}

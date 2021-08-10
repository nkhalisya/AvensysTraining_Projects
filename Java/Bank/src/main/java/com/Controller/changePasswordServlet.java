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
 * Servlet implementation class changePassword
 */
@WebServlet("/changePassword")
public class changePasswordServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String oldpw = req.getParameter("oldpw");
		String newpw = req.getParameter("newpw");
		String conpw = req.getParameter("conpw");
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		// check if new and confirm matches
		if( newpw.equals(conpw)) {
			Model m = new Model();
			m.setId(id);
			
			int updated = 0;
			boolean user = m.getUser();
			String dbpw = m.getPassword(); // password from db
			
			// check if old password is valid
			if( oldpw.equals(dbpw)) {
				
				// update new password
				m.setPassword(newpw);
				updated = m.edit("password"); // updated=1 if changed
			}
			
			if( updated == 0 ) {
				session.setAttribute("type", "fail");
				session.setAttribute("note", "Failed to change password");
			}
			else {
				session.setAttribute("type", "pass");
				session.setAttribute("note", "Password successfully changed");
			}
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Password mismatch during update");
		}
		resp.sendRedirect("profile.jsp");
	}

}

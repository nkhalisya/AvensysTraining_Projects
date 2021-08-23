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
 * Servlet implementation class registerServlet
 */
@WebServlet("/register")
public class registerServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String cardno = req.getParameter("cardno");
		String cardtype = req.getParameter("cardtype");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		
		boolean ok = false;
		if( password.equals(cpassword)) {
			Model m = new Model();
			User u = new User();
			u.setName(name);
			u.setEmail(email);
			u.setCardno(cardno);
			u.setCardtype(cardtype);
			u.setUsername(username);
			u.setPassword(password);
			
			m.setUser(u);
			
			int inserted = m.register();
			if( inserted == 0 ) {
				session.setAttribute("type", "fail");
				session.setAttribute("note", "Failed to register");
			}
			else {
				session.setAttribute("type", "pass");
				session.setAttribute("note", "You have been registered");
				ok = true;
			}
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Password mismatch during registration");
		}

		if(ok) {
			resp.sendRedirect("login.jsp");
		}
		else {
			resp.sendRedirect("register.jsp");
		}
	}

}

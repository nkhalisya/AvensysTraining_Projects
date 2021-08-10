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
 * Servlet implementation class loginServlet
 */
@WebServlet("/login")
public class loginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Model m = new Model();
		m.setUsername(username);
		m.setPassword(password);
		
		boolean ok = false;
		
		boolean user = m.login();
		if(user) {
			int id = m.getId();
			String name = m.getName();
			String account = m.getAccount();
			double balance = m.getBalance();
			
			session.setAttribute("id", id); // for user
			session.setAttribute("name", name); // index
			session.setAttribute("account", account); // deposit
			session.setAttribute("balance", balance); // deposit
			session.setAttribute("username", username); // profile
			
			resp.sendRedirect("index.jsp");
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to login");
			resp.sendRedirect("login.jsp");
		}
		
	}

}

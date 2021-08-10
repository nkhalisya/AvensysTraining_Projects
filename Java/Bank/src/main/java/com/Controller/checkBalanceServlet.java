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
 * Servlet implementation class checkBalanceServlet
 */
@WebServlet("/checkBalance")
public class checkBalanceServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		if(session.getAttribute("id") == null){ // id not in session	
			// redirect to login.html
			resp.sendRedirect("login.jsp");
		}
		else {
			int id = Integer.parseInt(session.getAttribute("id").toString());
			
			Model m = new Model();
			m.setId(id);
			
			boolean user = m.getUser();
			if(user) {
				String account = m.getAccount();
				double balance = m.getBalance();
				
				session.setAttribute("account", account);
				session.setAttribute("balance", balance);
				
				resp.sendRedirect("balance.jsp");
			}
			else {
				session.setAttribute("type", "fail");
				session.setAttribute("note", "Cannot retrieve balance");
				resp.sendRedirect("balance.jsp");
			}
		
		}
		
	}
}

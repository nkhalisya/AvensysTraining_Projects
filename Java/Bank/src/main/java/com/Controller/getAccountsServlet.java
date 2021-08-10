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

/**
 * Servlet implementation class getAccountsServlet
 */
@WebServlet("/getAccounts")
public class getAccountsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		if(session.getAttribute("id") == null){ // id not in session	
			// redirect to login.html
			resp.sendRedirect("login.jsp");
		}
		else {
			int UserId = Integer.parseInt(session.getAttribute("id").toString());
			
			Model m = new Model();
			
			// get user's account number
			m.setId(UserId);
			boolean user = m.getUser();
			String userAccount = m.getAccount();

			// get all accounts excluding user's
			m.setAccount(userAccount);
			ArrayList<Integer> ids = m.getAccounts();
			
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> accounts = new ArrayList<String>();
			for(Integer id: ids) {
				m.setId(id);
				user = m.getUser();
				if(user) {
					String name = m.getName();
					String account = m.getAccount();
					
					names.add(name);
					accounts.add(account);
				}
			}
			
			if( accounts.size() > 0 ) {
				session.setAttribute("names", names);
				session.setAttribute("accounts", accounts);
			}
			resp.sendRedirect("transfer.jsp");
		}
		
	}

}

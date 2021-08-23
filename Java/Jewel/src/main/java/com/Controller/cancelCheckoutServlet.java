package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Cart;
import com.Model.Model;

/**
 * Servlet implementation class cancelCheckoutServlet
 */
@WebServlet("/cancelCheckout")
public class cancelCheckoutServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		// delete recently created checkout
		Model m = new Model();
		int cid = m.getCheckoutId();
		
		int deleted = m.deleteCheckout(cid);
		if( deleted == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Could not cancel checkout");
		}
		else {
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Checkout cancelled");
		}
		resp.sendRedirect("getCart");
	}

}

package com.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Cart;
import com.Model.Checkout;
import com.Model.Model;
import com.Model.Product;
import com.Model.User;

/**
 * Servlet implementation class getCheckoutsServlet
 */
@WebServlet("/getCheckouts")
public class getCheckoutsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(); // true set in Register
		
		int userid = Integer.parseInt(session.getAttribute("id").toString());
		
		Model m = new Model();
		
		ArrayList<Checkout> checkouts = m.getCheckout(userid);

		if(checkouts.size() == 0) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "No checkouts");
		}
		else {
			session.setAttribute("checkouts", checkouts);
		}
		resp.sendRedirect("getCart");
		
	}

}

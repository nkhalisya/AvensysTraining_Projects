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
 * Servlet implementation class cartServlet
 */
@WebServlet("/cart")
public class cartServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(); // true set in Register
		
		int userid = Integer.parseInt(session.getAttribute("id").toString());
		int productid = Integer.parseInt(req.getParameter("id"));
		int qty = 1;
		
		Model m = new Model();
		
		// check if item in cart
		boolean exist = m.inCart(productid, userid);
		
		Cart c = new Cart();
		c.setUserid(userid);
		c.setProductid(productid);
		c.setQty(qty);	
		m.setCart(c);
		
		int modify = 0;
		if(exist) { // exist in cart
			// get existing qty
			m.getCart(productid, userid);
			c = m.getCart();
			qty = c.getQty();
			qty++; // + btn, increment by 1
			c.setQty(qty); // set new qty
			// update - productid, qty
			m.setCart(c);
			modify = m.updateQty();
		}
		else { // not in cart
			// insert - userid, productid, qty
			modify = m.addCart();
		}
			
		if( modify == 0) {
			// failed to insert/update
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to add to cart");
		}
		else {
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Added to cart successfully");
		}
		resp.sendRedirect("products.jsp");
	}

}

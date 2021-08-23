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
import com.Model.Model;
import com.Model.Product;

/**
 * Servlet implementation class manageCartServlet
 */
@WebServlet("/manageCart")
public class manageCartServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		int userid = Integer.parseInt(session.getAttribute("id").toString());
		
		int cid = Integer.parseInt(req.getParameter("cidHidden").toString());
		int productid = Integer.parseInt(req.getParameter("pidHidden").toString());
		int qty = Integer.parseInt(req.getParameter("qty"+cid).toString());
				
		Cart c = new Cart();
		c.setProductid(productid);
		c.setUserid(userid);
		c.setQty(qty); // set new qty
		
		// update - productid, qty
		Model m = new Model();
		m.setCart(c);
		int updated = m.updateQty();
		
		if( updated == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to update quantity");
		}
		else {			
			// update session of products
			ArrayList<Product> products = m.getAllProducts();
			session.setAttribute("products", products);
			
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Quantity succesfully updated");
		}
		resp.sendRedirect("getCart");
		
	}

}

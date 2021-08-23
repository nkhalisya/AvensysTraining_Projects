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
import com.Model.User;

/**
 * Servlet implementation class checkoutServlet
 */
@WebServlet("/checkout")
public class checkoutServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(); // true set in Register
		
		int userid = Integer.parseInt(session.getAttribute("id").toString());
		
		User u = new User();
		u.setId(userid);
		
		Model m = new Model();
		m.setUser(u);
		
		ArrayList<Cart> cart = m.getCartItems();
		
		double total = 0;
		Product p = new Product();
		for( Cart item: cart) {
			// get product single price
			p.id = item.productid;
			m.setProduct(p);
			m.getProduct("id");
			p = m.getProduct();
			
			// multiply qty
			item.price = p.price*item.qty;
			total += item.price;
		}
		
		int inserted = m.addCheckout(userid, total);
		if( inserted == 0 ) {
			// checkout table not created
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to checkout");
			resp.sendRedirect("getCart"); // cart.jsp
		}
		else {
			// go to confirm checkout
			resp.sendRedirect("checkout.jsp");
		}
		
	}

}

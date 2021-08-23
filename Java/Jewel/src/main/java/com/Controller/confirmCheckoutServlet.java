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
 * Servlet implementation class confirmCheckoutServlet
 */
@WebServlet("/confirmCheckout")
public class confirmCheckoutServlet extends HttpServlet {
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
		
		// get the checkout id
		int cid = m.getCheckoutId();
		
		// store in Details [loop]
		int insertDetails = 0;
		Product p = new Product();
		for( Cart item: cart) {
			// get product single price
			p.id = item.productid;
			m.setProduct(p);
			m.getProduct("id");
			p = m.getProduct();
			
			// multiply qty
			item.price = p.price*item.qty;
			
			insertDetails += m.addCheckoutDetails(cid, userid, item.productid, item.qty, item.price);
		}

		if(insertDetails == cart.size()) {
			// delete user's cart
			m.deleteUserCart(userid);
			session.removeAttribute("cart");
			
			// "purchases" checkout session
			ArrayList<Checkout> checkouts = m.getCheckout(userid);
			session.setAttribute("checkouts", checkouts);
			
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Checkout Completed");
		}
		else {
			// cld not add all items
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to confirm checkout");
		}
		resp.sendRedirect("getCart"); //cart.jsp
		
	}
}

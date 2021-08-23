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
import com.Model.Product;
import com.Model.User;
import com.Model.Cart;

/**
 * Servlet implementation class getCartServlet
 */
@WebServlet("/getCart")
public class getCartServlet extends HttpServlet {

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
		
		Product p = new Product();
		for( Cart item: cart) {
			p.id = item.productid;
			m.setProduct(p);
			m.getProduct("id");
			p = m.getProduct();
			
			item.type = p.type;
			item.img = p.img;
			item.name = p.name;
			item.price = p.price*item.qty;
		}
		
		if(cart.size() > 0) {
			session.setAttribute("cart", cart);
		}
		resp.sendRedirect("cart.jsp");
		
	}

}

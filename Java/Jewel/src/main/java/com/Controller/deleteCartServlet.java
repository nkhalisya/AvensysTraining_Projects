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
import com.Model.Cart;

/**
 * Servlet implementation class deleteCartServlet
 */
@WebServlet("/deleteCart")
public class deleteCartServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		int cid = Integer.parseInt(req.getParameter("id").toString());
		
		Cart c = new Cart();
		c.setId(cid);
		
		Model m = new Model();
		m.setCart(c);
		
		int deleted = m.deleteCart();
		if( deleted == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to delete product");
		}
		else {
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Product deleted");
		}
		resp.sendRedirect("getCart");
	}

}

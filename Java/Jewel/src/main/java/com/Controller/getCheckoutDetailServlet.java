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
import com.Model.Detail;
import com.Model.Model;
import com.Model.Product;
import com.Model.User;

/**
 * Servlet implementation class getCheckoutDetailServlet
 */
@WebServlet("/getCheckoutDetail")
public class getCheckoutDetailServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession(); // true set in Register
		
		int checkoutid = Integer.parseInt(req.getParameter("cid").toString());
		
		Model m = new Model();
		
		ArrayList<Detail> details = m.getCheckoutDetails(checkoutid);
		
		String date = "";
		double price = 0;
		Product p = new Product();
		for( Detail detail: details) {
			p.id = detail.productid;
			m.setProduct(p);
			m.getProduct("id");
			p = m.getProduct();
			
			detail.type = p.type;
			detail.img = p.img;
			detail.name = p.name;
			
			date = detail.datetime;
			price += detail.price;
		}
		
		if(details.size() > 0) {
			session.setAttribute("details", details);
			session.setAttribute("date", date);
			session.setAttribute("price", price);
		}
		resp.sendRedirect("checkoutDetails.jsp");
		
	}

}

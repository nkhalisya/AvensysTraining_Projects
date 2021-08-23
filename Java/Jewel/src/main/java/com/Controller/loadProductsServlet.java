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

/**
 * Servlet implementation class loadProductsServlet
 */
@WebServlet("/loadProducts")
public class loadProductsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		Model m = new Model();
		
		ArrayList<Product> products = m.getAllProducts();
		String sessionName = "products";
		if(req.getParameter("type") != null) { // type set
			
			String type = req.getParameter("type");
			if(type.equals("stone")) {
				sessionName = "stones";
			}
			else if(type.equals("ring")) {
				sessionName = "rings";
			}
			else if(type.equals("necklace")) {
				sessionName = "necklaces";
			}
			Product p = new Product();
			p.setType(type);
			m.setProduct(p);
			
			products = m.getProducts();
		}
				
		if(products.size() > 0) {
			session.setAttribute(sessionName, products);
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "There are no "+ sessionName);
		}
		resp.sendRedirect("products.jsp");
	}

}

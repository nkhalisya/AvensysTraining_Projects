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
 * Servlet implementation class getProductsServlet
 */
@WebServlet("/getProducts")
public class getProductsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		Model m = new Model();
		
		ArrayList<Product> products = m.getAllProducts();
		
		if(products.size() > 0) {
			session.setAttribute("products", products);
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "There are no products");
		}
		resp.sendRedirect("admin.jsp");
	}

}

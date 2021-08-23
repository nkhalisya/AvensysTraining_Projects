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
 * Servlet implementation class deleteProductServlet
 */
@WebServlet("/deleteProduct")
public class deleteProductServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		int id = Integer.parseInt(req.getParameter("id").toString());
		
		Product p = new Product();
		p.setId(id);
		
		Model m = new Model();
		m.setProduct(p);
		
		int deleted = m.deleteProduct();
		if( deleted == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to delete product");
		}
		else {			
			// update session of products
			ArrayList<Product> products = m.getAllProducts();
			session.setAttribute("products", products);
			
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Product deleted");
		}
		resp.sendRedirect("admin.jsp");
	}

}

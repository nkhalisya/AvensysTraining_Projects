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

/**
 * Servlet implementation class addProductServlet
 */
@WebServlet("/addProduct")
public class addProductServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String img = req.getParameter("img");
		String name = req.getParameter("name");
		String type = req.getParameter("type");
		String price = req.getParameter("price");
		
		Product p = new Product();
		p.setImg(img);
		p.setName(name);
		p.setType(type);
		p.setPrice(Double.parseDouble(price));
		
		Model m = new Model();
		m.setProduct(p);
		int inserted = m.addProduct();
		
		if( inserted == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to add product");
			resp.sendRedirect("addProduct.jsp");
		}
		else {
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Product added");
			
			ArrayList <Product> products = m.getAllProducts();
			session.setAttribute("products", products);
			resp.sendRedirect("admin.jsp");
		}

	}

}

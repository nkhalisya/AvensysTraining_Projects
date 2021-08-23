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
 * Servlet implementation class manageProductsServlet
 */
@WebServlet("/manageProducts")
public class manageProductsServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		int adminId = Integer.parseInt(session.getAttribute("id").toString());
		
		int id = Integer.parseInt(req.getParameter("idHidden").toString());
				
		String name = req.getParameter("name"+id);
		String img = req.getParameter("imgHidden"+id);
		String type = req.getParameter("typeHidden"+id);
		if(req.getParameter("type"+id) != null) {
			type = req.getParameter("type"+id);
		}
		double price = Double.parseDouble(req.getParameter("price"+id));

		Product p = new Product();
		p.setId(id);
		
		Model m = new Model();
		m.setProduct(p);
		
		int updated = 0;
		// get db variables
		boolean product = m.getProduct("id");
		Product dbProduct = m.getProduct();
		String dbName = dbProduct.getName();
		String dbImg = dbProduct.getImg();
		String dbType = dbProduct.getType();
		double dbPrice = dbProduct.getPrice();
		
		// if name not same -> update
		if( !name.equals(dbName) ) {
			p.setName(name); 
			m.setProduct(p);
			updated += m.editProduct("name");
		}
		
		// if email not same -> update
		if( !img.equals(dbImg) ) {
			p.setImg(img);
			m.setProduct(p);
			updated += m.editProduct("img");
		}
		
		// if username not same -> update
		if( !type.equals(dbType) ) {
			p.setType(type);
			m.setProduct(p);
			updated += m.editProduct("type");
		}
		
		// if role not same -> update
		if( price != dbPrice ) {
			p.setPrice(price);
			m.setProduct(p);
			updated += m.editProduct("price");
		}
		
		if( updated == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to update product");
		}
		else {			
			// update session of products
			ArrayList<Product> products = m.getAllProducts();
			session.setAttribute("products", products);
			
			session.setAttribute("type", "pass");
			session.setAttribute("note", "Product succesfully updated");
		}
		resp.sendRedirect("admin.jsp");
		
	}
	
}

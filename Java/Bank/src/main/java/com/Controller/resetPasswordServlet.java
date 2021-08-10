package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Model;

/**
 * Servlet implementation class resetPasswordServlet
 */
@WebServlet("/resetPassword")
public class resetPasswordServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = 0;
		int error = 0;
		String note = "";
		
		String form_otp = req.getParameter("otp");
		String new_pw = req.getParameter("npw");
		String con_pw = req.getParameter("cpw");
		
		HttpSession session = req.getSession();
		String email = session.getAttribute("email").toString();
		String otp = session.getAttribute("otp").toString();
		
		Model m = new Model();
		
		m.setEmail(email);
		boolean user = m.getUserByEmail(); // use email to get user id
		if(!user) {
			error = 1;
			note = "Email does not exist";
		}
		else {
			// to use update method
			id = m.getId();
		}
		
		boolean otpMatch = otp.equals(form_otp);
		if(!otpMatch) {
			error = 2;
			note = "Invalid OTP";
		}
		
		boolean pwMatch = new_pw.equals(con_pw);
		if(!pwMatch) {
			error = 3;
			note = "Password mismatch during reset password";
		}
		
		boolean ok = false;
		if( error == 0 ) { // ( user && otpMatch && pwMatch )
			m.setId(id);
			m.setPassword(new_pw);
			
			int update = m.edit("password");
			if( update == 0 ) {
				session.setAttribute("type", "fail");
				session.setAttribute("note", "Failed to reset password");
			}
			else {
				session.setAttribute("type", "pass");
				session.setAttribute("note", "Password reset successfully");
				ok = true;
			}
		}
		else {
			// got error
			session.setAttribute("type", "fail");
			session.setAttribute("note", note);
		}
		
		if(ok) {
			session.removeAttribute("email");
			resp.sendRedirect("login.jsp");
		}
		else {
			resp.sendRedirect("resetPassword.jsp");
		}
		
	}

}

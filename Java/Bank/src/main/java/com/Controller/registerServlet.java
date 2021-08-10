package com.Controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Model;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/register")
public class registerServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String account = req.getParameter("account");
		String balance = req.getParameter("balance");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String cpassword = req.getParameter("cpassword");
		
		boolean ok = false;
		Model m = new Model();
		
		// check if password match
		if( password.equals(cpassword)) {

			m.setName(name);
			m.setEmail(email);
			m.setUsername(username);
			m.setPassword(password);
			m.setAccount(account);
			m.setBalance(Double.parseDouble(balance));
			
			int inserted = m.register();
			if( inserted == 0 ) {
				session.setAttribute("type", "fail");
				session.setAttribute("note", "Failed to register");
			}
			else {
				session.setAttribute("type", "pass");
				session.setAttribute("note", "You have been registered");
				ok = true;
			}
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Password mismatch during registration");
		}
		
		if(ok) {
			sendEmail(m);
			resp.sendRedirect("login.jsp");
		}
		else {
			resp.sendRedirect("register.jsp");
		}
		
	}
	
	public void sendEmail( Model m) {
		
		String fromEmail = Credentials.email; //sender's mail id.
		String pwd = Credentials.password;		//sender's mail pwd.
		String toEmail = m.getEmail();
		
		String subject="DUIT | New User Registered [DO NOT REPLY]"; // mail subject line
		String msg="Hi " + m.getName() + ", \n\nWelcome to DUIT! You have been successfully registered!";
		msg += "\n\nDetails:\nName: " + m.getName() + "\nEmail: " + m.getEmail() + "\nUsername: " + m.getUsername() + "\nAccount Number: " + m.getAccount() + "\nBalance: $"+ m.getBalance();
		msg += "\n\nThank you,\nDUIT"; //mail body;
		
		//Creating Session Object
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				//sender's mail id and pwd is encapsulated inside "SendersCredentials.java"
				return new PasswordAuthentication(fromEmail, pwd);
			}
		});

		try {
			//Composing the Mail
			MimeMessage mesg = new MimeMessage(session);
			mesg.setFrom(new InternetAddress(fromEmail));
			mesg.addRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
			mesg.setSubject(subject);  
			mesg.setText(msg);  
			
			//Sending the Mail
			Transport.send(mesg);
			System.out.println("Mail Sent!! Register");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

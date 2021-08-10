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
 * Servlet implementation class addBalanceServlet
 */
@WebServlet("/addBalance")
public class addBalanceServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		double balance = Double.parseDouble(req.getParameter("balance"));
		String password = req.getParameter("password");
				
		Model m = new Model();
		m.setId(id);
		
		int updated = 0;
		boolean user = m.getUser();
		String dbpw = m.getPassword(); // password from db
		double dbBalance = m.getBalance(); // password from db
		double newBalance = 0;
		
		// check if password is valid
		if( password.equals(dbpw)) {
			
			// update new password
			newBalance = balance + dbBalance;
			m.setBalance(newBalance);
			updated = m.edit("balance"); // updated=1 if changed
		}
			
		if( updated == 0 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to deposit");
		}
		else {
			session.setAttribute("type", "pass");
			session.setAttribute("note", "You have deposited money into your account");
			
			session.setAttribute("balance", newBalance);
			sendEmail(m, balance);
		}
		resp.sendRedirect("deposit.jsp");
		
	}

	public void sendEmail( Model m, double addedBalance) {
		
		String fromEmail = Credentials.email; //sender's mail id.
		String pwd = Credentials.password;		//sender's mail pwd.
		String toEmail = m.getEmail();
		
		String subject="DUIT | Deposit [DO NOT REPLY]"; // mail subject line
		String msg="Hi " + m.getName() + ", \n\nYou have successfully deposited $" + addedBalance + " into your Account Number: " + m.getAccount();
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
			System.out.println("Mail Sent - Deposit");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

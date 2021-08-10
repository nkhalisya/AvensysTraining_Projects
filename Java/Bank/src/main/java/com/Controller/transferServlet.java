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
 * Servlet implementation class transferServlet
 */
@WebServlet("/transfer")
public class transferServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(); // true set in Register
		
		int id = Integer.parseInt(session.getAttribute("id").toString());
		
		// check if user has enough to ttransfer -> amount
		// yes -> minues from user account -> update user balance
		//	   -> add to transfer account -> update transfer balance
		// no -> ERROR: you don't have enough to transfer
		
		Model m = new Model();
		int updated = 0;
		boolean user;
		
		// either RADIO BUTTON or ACCOUNT NO
		int transferId = 0;
		String transferAcc = "";
		if(req.getParameter("accountId") != null) { // radio button selected
			transferAcc = req.getParameter("accountId");
		}
		else { // account no entered
			transferAcc = req.getParameter("account"); // accountno
		}
		// get id -> to update
		m.setAccount(transferAcc);
		user = m.getUserByAccount();
		if(user) {
			transferId = m.getId();
		}
		else {
			updated = 3; // account not in db
		}
		
		// check if transfer acc is user's account [SELF-TRANSFER]
		if(transferId == id) {
			updated = 3;
		}
		
		double balance = Double.parseDouble(req.getParameter("amount"));
		String password = req.getParameter("password");
		
		m.setId(id);
		user = m.getUser();
		String dbpw = m.getPassword(); // password from db
		double dbBalance = m.getBalance(); // balance from db
		double netBalance = dbBalance - balance;
		
		// check if password is valid
		if( password.equals(dbpw) && updated == 0 ) { // updated has no errors (updated=3)
			
			// check if user has enough
			if( netBalance < 0 ) {
				// not enough
				session.setAttribute("type", "fail");
				session.setAttribute("note", "You do not have enough to transfer");
			}
			else {
				// minus from user account
				m.setBalance(netBalance);
				updated += m.edit("balance"); // updated=1 if changed
				
				// get transfer account's balance from db
				m.setId(transferId);
				m.getUser();
				double tdbBalance = m.getBalance();
				double tnewBalance = tdbBalance + balance;
				
				// add to transfer account
				m.setBalance(tnewBalance);
				updated += m.edit("balance"); // updated=2 if changed
			}
		}
		
		// get logged in user details -> email
		m.setId(id);
		m.getUser();
			
		if( updated == 2 ) {
			session.setAttribute("type", "pass");
			session.setAttribute("note", "You have successfully transferred money");
			
			session.setAttribute("balance", netBalance);
			sendEmail(m, balance, transferAcc);
		}
		else if( updated == 3 ) {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Invalid account number");
		}
		else {
			session.setAttribute("type", "fail");
			session.setAttribute("note", "Failed to transfer");
		}
		resp.sendRedirect("transfer.jsp");
		
	}
	
	public void sendEmail( Model m, double transferAmount, String transferAccount) {
		
		String fromEmail = Credentials.email; //sender's mail id.
		String pwd = Credentials.password;		//sender's mail pwd.
		String toEmail = m.getEmail();
		
		String subject="DUIT | Money Transfer [DO NOT REPLY]"; // mail subject line
		String msg="Hi " + m.getName() + ", \n\nYou have successfully transferred $" + transferAmount + " to Account Number: " + transferAccount;
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
			System.out.println("Mail Sent - Transfer");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

package com.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Model.Model;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Servlet implementation class forgotPasswordServlet
 */
@WebServlet("/mailReset")
public class mailReset extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Random rand = new Random();
		int otp = rand.nextInt(900000)+100000;
		
		String fromEmail = Credentials.email; //sender's mail id.
		String pwd = Credentials.password;		//sender's mail pwd.
		String toEmail= req.getParameter("email");  //reciever's mail id.
		
		String subject="Jaeda's Jewel | Reset Password [DO NOT REPLY]"; // mail subject line
		String msg="Hi user, \n\nHere is the reset Password Link: http://localhost:8080/Jewel/resetPassword.jsp \nOTP: " + otp; //mail body
		msg += "\n\nThank you,\nJaeda's Jewel"; //mail body;
		
		HttpSession sess = req.getSession(true);
		sess.setAttribute("email", toEmail);
		sess.setAttribute("otp", otp);
		
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
			System.out.println("Mail Sent - Reset");
			resp.sendRedirect("resetPassword.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("login.jsp");
	}
}

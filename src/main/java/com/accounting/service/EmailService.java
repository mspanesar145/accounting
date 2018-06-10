package com.accounting.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.accounting.bo.Feedback;
import com.accounting.user.bo.User;

public class EmailService {
	
    // Sender's email ID needs to be mentioned
	private static String from = "astechnologies.gst@gmail.com";
	private Session session = null;
	
	public void sendUserFeedback(Feedback feedback) {
		// Recipient's email ID needs to be mentioned.
		
		User user = feedback.getUser();
	      try {
	    	  setAuthenticateSession();
	          // Create a default MimeMessage object.
	    	  MimeMessage message = new MimeMessage(session);    
	           message.addRecipient(Message.RecipientType.TO,new InternetAddress("jaspreet605@gmail.com"));    
	           message.setSubject("Feedback From :"+user.getFirstName());    
	           
	           StringBuffer sb = new StringBuffer();
	           sb.append(feedback.getFeedback());
	           message.setText(sb.toString());    
	           //send message  
	           Transport.send(message);    
	           System.out.println("message sent successfully");    

	          System.out.println("Sent message successfully....");
	       } catch (Exception ex) {
	    	   ex.printStackTrace();
	       }
	}
	
	public void setAuthenticateSession() {
		// Get system properties
      	Properties props = System.getProperties();
      	props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

      // Get the default Session object.
		// check the authentication
		session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "mapa2418");
			}
		});
	}
}

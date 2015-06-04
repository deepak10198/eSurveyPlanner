package com.esp.mail;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.esp.dto.UserDetailsDTO;
import com.esp.handler.HomeHandler;


public class Mail
{
	 @Autowired
	private HomeHandler handler;
	  
	public boolean mailSendingProcess(String sender, BigDecimal recieveId, String msg, String subject, String link, BigDecimal surveyId) throws Exception {
		
		System.out.println("---- enter processing --------");
		
		 String host = "localhost";
		 
		 String msgBody = msg+ " \n \n URL :"+link;
		 System.out.println("---mail chck- lis-sf-");
		 List<UserDetailsDTO> userlist = handler.fetchuserDetails(recieveId);
		 System.out.println("---mail chck- lis--");
		 String[] email = new String[userlist.size()] ;
		 int i = 0;
		 for(UserDetailsDTO user : userlist)
		 {
			email[i] = user.getEmail();
			i++;
		 }
		 
		 InternetAddress[] emailTo = new InternetAddress[email.length];
		 for(int j=0; j<email.length; j++){
			 emailTo[j] = new InternetAddress(email[j]);
			 System.out.println("---mail chck---"+emailTo[j]);
        }
		
		 Properties prop = System.getProperties();
		 prop.put("mail.smtp.host", host);
		 prop.put("mail.smtp.port", "8181");
		 Session session=Session.getDefaultInstance(prop,null);
		 System.out.println("---mail chck- hdgf--");
        MimeMessage message=new MimeMessage(session);
        
        message.setFrom(sender);
        message.addRecipients(Message.RecipientType.TO, emailTo);
        message.setSubject(subject);
        message.setText(msgBody);
        
        
        Transport.send(message);
        return true;
		
		
	}
	
}
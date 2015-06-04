package com.esp.dto;

import java.math.BigDecimal;

/**
 * @author 
 * 
 **/

public class mailDetailDTO{
	
	private String sender;
	private BigDecimal rListId;
	private BigDecimal surveyId;
	private String message;
	private String subject;
	private String surveylink;
	private String password;
	
	public String getPassword() {
	        return password;
	    }

	 public void setPassword(String password) {
	        this.password = password;
	    }
	 

		public String getSender() {
		        return sender;
		    }

		 public void setSender(String sender) {
		        this.sender = sender;
		    }
	 
	 public BigDecimal getRListId() {
	        return rListId;
	    }

	 public void setRListId(BigDecimal rListId) {
	        this.rListId = rListId;
	    }
	 
	 public BigDecimal getSurveyId(){
	        return surveyId;
	    }

	 public void setSurveyId(BigDecimal surveyId) {
	        this.surveyId = surveyId;
	    }
	 
	 public String getMessage() {
	        return message;
	    }

	 public void setMessage(String message) {
	        this.message = message;
	    }
	 public String getSubject() {
	        return subject;
	    }

	 public void setSubject(String subject) {
	        this.subject = subject;
	    }
	 public String getSurveylink() {
	        return surveylink;
	    }

	 public void setSurveylink(String surveylink) {
	        this.surveylink = surveylink;
	    }
	 
	
	 
	
}
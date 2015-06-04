package com.esp.dto;

/**
* <h1>Survey Questions DTO</h1>
* This DTO class is to maintain the following properties. 
* <p>
* questionId
* questionText
* ansId
* ansTextList
* ansTypeId
* ansType
* 
* 
*</p>
* @author  Deepak Kumar
* @version 1.0
* @since   08-09-2014 
*/


import java.math.BigDecimal;
import java.util.List;

public class SurveyQuestionDTO {
	
	private BigDecimal surveyQuesId;
	private int questionId;
	private String questionText;
	private int quesAnswerId;
	private int ansTypeId;
	private String ansType;
	private String mandatory;
	private String otherText;
	private List<String> ansTextList;
	private List<String> other;
	
	public BigDecimal getSurveyQuesid() {
		return surveyQuesId;
	}
	public void setSurveyQuesid(BigDecimal surveyQuesId) {
		this.surveyQuesId = surveyQuesId;
	}	
	public List<String> getAnsTextList() {
		return ansTextList;
	}
	public void setAnsTextList(List<String> ansTextList) {
		this.ansTextList = ansTextList;
	}
	public List<String> getOther() {
		return other;
	}
	public void setOther(List<String> other) {
		this.other = other;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getQuesAnswerId() {
		return quesAnswerId;
	}
	public void setQuesAnswerId(int quesAnswerId) {
		this.quesAnswerId = quesAnswerId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getOtherText() {
		return otherText;
	}
	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}
	public int getAnsTypeId() {
		return ansTypeId;
	}
	public void setAnsTypeId(int ansTypeId) {
		this.ansTypeId = ansTypeId;
	}
	public String getAnsType() {
		return ansType;
	}
	public void setAnsType(String ansType) {
		this.ansType = ansType;
	}
	 public String getMandatory() {
	        return mandatory;
	    }

	    public void setMandatory(String mandatory) {
	        this.mandatory = mandatory;
	    }
	    

	
	
	
	
}

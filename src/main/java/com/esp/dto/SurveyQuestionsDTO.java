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


import java.util.List;

public class SurveyQuestionsDTO {

	private int questionId;
	private String questionText;
	private int ansId;
	private List<String> ansTextList;
	private int ansTypeId;
	private String ansType;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public int getAnsId() {
		return ansId;
	}
	public void setAnsId(int ansId) {
		this.ansId = ansId;
	}
	public List<String> getAnsTextList() {
		return ansTextList;
	}
	public void setAnsTextList(List<String> ansTextList) {
		this.ansTextList = ansTextList;
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
	
	
	
}

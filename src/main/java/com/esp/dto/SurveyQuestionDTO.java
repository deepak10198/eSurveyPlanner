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

public class SurveyQuestionDTO {

	private int questionId;
	private String questionText;
	private int quesAnswerId;
	private int ansId;
	private List<AnswerDTO> answers;
	
	private List<Integer> ansIdList;
	
	public List<Integer> getAnsIdList() {
		return ansIdList;
	}
	public void setAnsIdList(List<Integer> ansIdList) {
		this.ansIdList = ansIdList;
	}
	private int ansTypeId;
	private String ansType;
	
	
	public List<AnswerDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerDTO> answers) {
		this.answers = answers;
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
	public int getAnsId() {
		return ansId;
	}
	public void setAnsId(int ansId) {
		this.ansId = ansId;
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

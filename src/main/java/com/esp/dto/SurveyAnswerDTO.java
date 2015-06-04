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

public class SurveyAnswerDTO {

	private int questionId;
	private List<Integer> ansIdList;
	private List<String> ansTextList;
	private int ansTypeId;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public List<Integer> getAnsIdList() {
		return ansIdList;
	}
	public void setAnsIdList(List<Integer> ansIdList) {
		this.ansIdList = ansIdList;
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
	
	
	
}

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


import java.util.ArrayList;
import java.util.List;

public class QuestionUIDTO {

	private int questionId;
	private String questionText;
	private int quesAnswerId;
	private List<ElementDTO> answers;
	private int ansTypeId;
	private String ansType;
	private String mandatory;
	private long surveyQuestionCount;
		 
		
	public List<ElementDTO> getAnswers() {
		return answers;
	}
	public void setAnswers(List<ElementDTO> answers) {
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
    
	
	public List<String> getAnsTextList (){
		
		List<String> ansTextList = new ArrayList<String>();
		
		for(ElementDTO answer :  getAnswers()){
			
			ansTextList.add(answer.getText());
			
		}
		
		return ansTextList;
	}
	
	public long getSurveyQuestionCount() {
		return surveyQuestionCount;
	}
	public void setSurveyQuestionCount(long surveyCount) {
		this.surveyQuestionCount = surveyCount;
	}

	
}

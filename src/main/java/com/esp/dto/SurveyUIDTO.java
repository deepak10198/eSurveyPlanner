package com.esp.dto;

import java.util.List;

/**
* <h1>User Survey DTO</h1>
* This DTO class is to maintain the following properties. 
* <p>
* surveyId
* surveyDisplayId
* surveyName
* surveyDesc
* surveyQuestions
* 
* 
* 
*</p>
* @author  Deepak Kumar
* @version 1.0
* @since   08-09-2014 
*/

public class SurveyUIDTO {
	
	private int surveyId;
	private String surveyDisplayId; // This is for survey id in the survey master tables.
	private String surveyName;
	private String surveyDesc;
	private List<QuestionUIDTO> surveyQuestions;
	
	public String toString(){
		
		String temp =null;
		
		temp= "surveyId:"+surveyId+"\n surveyDisplayId"+surveyDisplayId+"\n surveyName";
		
		for(QuestionUIDTO surveyQuestionDTO: surveyQuestions ){
			temp += "\n surveyQuestionDTO."+surveyQuestionDTO.getQuestionId();
				
			
					/*for(ElementDTO answer: surveyQuestionDTO.getAnswers()){
						if (answer!=null){
							temp += "\n getAnsId"+answer.getId()+"\n getAnsText"+answer.getText();	
							
						}
						
					}*/
					
								
		}
		
		return temp;
	}
	
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getSurveyDisplayId() {
		return surveyDisplayId;
	}
	public void setSurveyDisplayId(String surveyDisplayId) {
		this.surveyDisplayId = surveyDisplayId;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public String getSurveyDesc() {
		return surveyDesc;
	}
	public void setSurveyDesc(String surveyDesc) {
		this.surveyDesc = surveyDesc;
	}
	public List<QuestionUIDTO> getSurveyQuestions() {
		return surveyQuestions;
	}
	public void setSurveyQuestions(List<QuestionUIDTO> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}
	
	
	

}

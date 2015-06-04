package com.esp.dto;

import java.util.List;

public class SurveyResponseDTO {

	private int surveyId;
	private String surveyName;
	private List<SurveyQuestionDTO> surveyQuestions;
	private String surveyEmail;
	private int surveyCount;
	private List<String> mandatory;

	
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public List<SurveyQuestionDTO> getSurveyQuestions() {
		return surveyQuestions;
	}
	public void setSurveyQuestions(List<SurveyQuestionDTO> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}
	
	public String getSurveyEmail() {
		return surveyEmail;
	}
	public void setSurveyEmail(String surveyEmail) {
		this.surveyEmail = surveyEmail;
	}
	
	public int getSurveyCount() {
		return surveyCount;
	}
	public void setSurveyCount(int surveyCount) {
		this.surveyCount = surveyCount;
	}
	 public List<String> getMandatory() {
	        return mandatory;
	    }

	    public void setMandatory(List<String> mandatory) {
	        this.mandatory = mandatory;
	    }
	    

	
	
}

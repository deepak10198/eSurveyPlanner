package com.esp.dto;

import java.util.List;

public class SurveyResponseDTO {

	private int surveyId;
	private String surveyName;
	private List<SurveyQuestionDTO> surveyQuestions;
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
	
	

	
	
}

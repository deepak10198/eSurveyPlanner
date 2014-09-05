package com.esp.vo;

import java.math.BigDecimal;

public class SurveyVO {

	private String surveyId;
	private String surveyName;
	private BigDecimal ansId;
	private int QAMapId;
        
	
	
	
	public String getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(String surveyId) {
		this.surveyId = surveyId;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public BigDecimal getAnsId() {
		return ansId;
	}
	public void setAnsId(BigDecimal ansId) {
		this.ansId = ansId;
	}
	public int getQAMapId() {
		return QAMapId;
	}
	public void setQAMapId(int qAMapId) {
		QAMapId = qAMapId;
	}
	
	
}

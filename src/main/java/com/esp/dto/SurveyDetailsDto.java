package com.esp.dto;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class SurveyDetailsDto {

	private String surveyName;
	private Date startDate;
	private Date endDate;
	private String descriotion;
	private String surveyType;
	
		
/*	public SurveyDetailsDto(String surveyName, Date startDate, Date endDate,
			String descriotion, String surveyType) {
		super();
		this.surveyName = surveyName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.descriotion = descriotion;
		this.surveyType = surveyType;
	}*/
	
	
		
	public String getSurveyName() {
		return surveyName;
	}
	
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDescriotion() {
		return descriotion;
	}
	
	public void setDescriotion(String descriotion) {
		this.descriotion = descriotion;
	}
	public String getSurveyType() {
		return surveyType;
	}
	
	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}
	
	
	
	
}

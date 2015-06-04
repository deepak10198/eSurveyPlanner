/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Rakesh.k
 */
public class SurveyDetailsDTO {
	private BigDecimal surveyId;
    private String surveyname;
    private String description;
    private String surveystart;
    private String surveyend;
    private int type;
    
    private String published;
    private List<QuestionUIDTO> surveyQuestions;
    
    public BigDecimal getSurveyId()
    {
    	return surveyId;
    }
    
    public void setSurveyId(BigDecimal surveyId)
    {
    	this.surveyId = surveyId;
    }
    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getSurveyname() {
        return surveyname;
    }

    public void setSurveyname(String surveyname) {
        this.surveyname = surveyname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSurveystart() {
        return surveystart;
    }

    public void setSurveystart(String surveystart) {
        this.surveystart = surveystart;
    }

    public String getSurveyend() {
        return surveyend;
    }

    public void setSurveyend(String surveyend) {
        this.surveyend = surveyend;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public List<QuestionUIDTO> getSurveyQuestions() {
		return surveyQuestions;
	}
	public void setSurveyQuestions(List<QuestionUIDTO> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}
	

   
    
    
}

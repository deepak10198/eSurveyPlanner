package com.esp.dto;

public class SurveyDTO {

    private int surveyId;
    private String surveyName;
    private int ansId;
    private int quesAnsId;
    private int ansTypeID;
    private String surveyDesc;
    
   
    public int getAnsTypeID() {
        return ansTypeID;
    }

    public void setAnsTypeID(int ansTypeID) {
        this.ansTypeID = ansTypeID;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }
    public String getSurveyDesc() {
        return surveyDesc;
    }

    public void setSurveyDesc(String surveyDesc) {
        this.surveyDesc = surveyDesc;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public int getAnsId() {
        return ansId;
    }

    public void setAnsId(int ansId) {
        this.ansId = ansId;
    }

    public int getQuesAnsId() {
        return quesAnsId;
    }

    public void setQuesAnsId(int quesAnsId) {
        this.quesAnsId = quesAnsId;
    }

}

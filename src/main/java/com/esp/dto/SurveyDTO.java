package com.esp.dto;

public class SurveyDTO {

    private int surveyId;
    private String surveyName;
    private int ansId;
    private int quesAnsId;
    private int ansTypeID;

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
        quesAnsId = quesAnsId;
    }

}

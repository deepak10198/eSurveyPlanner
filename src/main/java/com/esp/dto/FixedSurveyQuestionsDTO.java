/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.dto;

import java.util.List;

/**
 *
 * @author Rakesh.K
 */
public class FixedSurveyQuestionsDTO {
    
    private List<String> questionText;
    private int ansTypeId;
    private int ansId;

    public int getAnsTypeId() {
		return ansTypeId;
	}

	public void setAnsTypeId(int ansTypeId) {
		this.ansTypeId = ansTypeId;
	}

	public int getAnsId() {
		return ansId;
	}

	public void setAnsId(int ansId) {
		this.ansId = ansId;
	}

	public List<String> getQuestionText() {
        return questionText;
    }

    public void setQuestionText(List<String> questionText) {
        this.questionText = questionText;
    }
    
    
    
}

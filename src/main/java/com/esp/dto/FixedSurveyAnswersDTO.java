package com.esp.dto;

import java.util.List;


public class FixedSurveyAnswersDTO {

	private int ansTypeId;
	private List<String> ansTextList;

	
	
	public List<String> getAnsTextList() {
		return ansTextList;
	}
	public void setAnsTextList(List<String> ansDesc) {
		this.ansTextList = ansDesc;
	}
	public int getAnsTypeId() {
		return ansTypeId;
	}
	public void setAnsTypeId(int ansTypeId) {
		this.ansTypeId = ansTypeId;
	}
	
	
	
}

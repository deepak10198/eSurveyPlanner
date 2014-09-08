package com.esp.dto;

import java.util.List;


public class FixedSurveyAnswerDetailsDTO {

	private String ansType;
	private List<String> ansTextList;

	
	public FixedSurveyAnswerDetailsDTO() {
		//ansDesc = LazyList
	}
	
	public List<String> getAnsTextList() {
		return ansTextList;
	}
	public void setAnsTextList(List<String> ansDesc) {
		this.ansTextList = ansDesc;
	}
	public String getAnsType() {
		return ansType;
	}
	public void setAnsType(String ansType) {
		this.ansType = ansType;
	}
	
	
	
}

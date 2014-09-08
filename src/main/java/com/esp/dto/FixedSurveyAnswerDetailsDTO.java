package com.esp.dto;

import java.util.List;


public class FixedSurveyAnswerDetailsDTO {

	private String ansType;
	private List<String> ansDesc;

	
	public FixedSurveyAnswerDetailsDTO() {
		//ansDesc = LazyList
	}
	
	public List<String> getAnsDesc() {
		return ansDesc;
	}
	public void setAnsDesc(List<String> ansDesc) {
		this.ansDesc = ansDesc;
	}
	public String getAnsType() {
		return ansType;
	}
	public void setAnsType(String ansType) {
		this.ansType = ansType;
	}
	
	
	
}

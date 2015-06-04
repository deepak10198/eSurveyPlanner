package com.esp.dto;

import java.util.List;


public class FixedSurveyAnswersDTO {

	private int ansTypeId;
	private List<String> ansTextList;
	private List<String> other;

	
	
	public List<String> getAnsTextList() {
		return ansTextList;
	}
	public void setAnsTextList(List<String> ansDesc) {
		this.ansTextList = ansDesc;
	}
	public List<String> getOther() {
		return other;
	}
	public void setOther(List<String> other) {
		this.other = other;
	}
	public int getAnsTypeId() {
		return ansTypeId;
	}
	public void setAnsTypeId(int ansTypeId) {
		this.ansTypeId = ansTypeId;
	}
	
	
	
}

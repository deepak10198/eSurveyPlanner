package com.esp.vo;

import java.util.List;

import org.apache.commons.collections.list.LazyList;

public class FSAnswerDetailsVO {

	private String ansType;
	private List<String> ansDesc;
	
	
	public FSAnswerDetailsVO() {
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

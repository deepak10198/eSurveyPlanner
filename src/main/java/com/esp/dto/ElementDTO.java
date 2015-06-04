package com.esp.dto;

import java.math.BigDecimal;
import java.util.List;

/**
* <h1>Element DTO</h1>
* This is a generic DTO to maintain the following properties. 
* <p>
* id
* text
* 
*</p>
* @author  Deepak Kumar
* @version 1.0
* @since   12-09-2014 
*/

public class ElementDTO {

	private int id;
	private String text;
	private String other;
	private List<String> otherText;
	private BigDecimal id2;
	private long elementCount;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<String> getOtherText() {
		return otherText;
	}
	public void setOtherText(List<String> otherText) {
		this.otherText = otherText;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public BigDecimal getId2() {
		return id2;
	}
	public void setId2(BigDecimal id2) {
		this.id2 = id2;
		
	}
	public long getElementCount() {
		return elementCount;
	}
	public void setElementCount(long elementCount) {
		this.elementCount = elementCount;
	}
	
	
}

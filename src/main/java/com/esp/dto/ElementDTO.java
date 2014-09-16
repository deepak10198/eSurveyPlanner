package com.esp.dto;

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
	
	
	
}

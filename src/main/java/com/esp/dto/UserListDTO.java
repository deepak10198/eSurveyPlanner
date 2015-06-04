package com.esp.dto;

import java.math.BigDecimal;
/**
 * @author 
 * 
 **/

public class UserListDTO{
	
	private BigDecimal userlistId;
	private String userListName;
	private String description;
	private String creationDate;
	private long count;
	
	public BigDecimal getUserlistId()
    {
    	return userlistId;
    }
    
    public void setUserlistId(BigDecimal userlistId)
    {
    	this.userlistId = userlistId;
    }
	 	public String getUserListName() {
	        return userListName;
	    }
	 
	    public void setUserListName(String userListName) {
	        this.userListName = userListName;
	    }
	    public String getDescription()
	    {
	    	return description;
	    }
	    public void setDescription(String description)
	    {
	    	this.description = description;
	    }
	    public String getCreationDate()
	    {
	    	return creationDate;
	    }
	    public void setCreationDate(String creationDate)
	    {
	    	this.creationDate = creationDate;
	    }
	    public long getCount()
	    {
	    	return count;
	    }
	    public void setCount(long count)
	    {
	    	this.count = count;
	    }
	    
}
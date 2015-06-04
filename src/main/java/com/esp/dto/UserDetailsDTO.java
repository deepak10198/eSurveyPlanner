package com.esp.dto;

import java.math.BigDecimal;

/**
 * @author 
 * 
 **/

public class UserDetailsDTO{
	
	private BigDecimal userid;
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String dob;
	private String email;
	private String loginId;
	private String martialStatus;
	private String password;
	
	
	public BigDecimal getUserid() {
        return userid;
    }

 public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }
	
	 public String getFirstName() {
	        return firstName;
	    }

	 public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	 
	 public String getMiddleName() {
	        return middleName;
	    }

	 public void setMiddleName(String middleName) {
	        this.middleName = middleName;
	    }
	 
	 public String getLastName() {
	        return lastName;
	    }

	 public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	 
	 public String getGender() {
	        return gender;
	    }

	 public void setGender(String gender) {
	        this.gender = gender;
	    }
	 
	 public String getdob() {
	        return dob;
	    }

	 public void setdob(String dob) {
	        this.dob = dob;
	    }
	 
	 public String getEmail() {
	        return email;
	    }

	 public void setEmail(String email) {
	        this.email = email;
	    }
	 

	 public String getLoginId() {
	        return loginId;
	    }

	 public void setLoginId(String loginId) {
	        this.loginId = loginId;
	    }
	 

	 public String getMartialStatus() {
	        return martialStatus;
	    }

	 public void setMartialStatus(String martialStatus) {
	        this.martialStatus = martialStatus;
	    }
	 

	 public String getPassword() {
	        return password;
	    }

	 public void setPassword(String password) {
	        this.password = password;
	    }
	 
	 
	
}
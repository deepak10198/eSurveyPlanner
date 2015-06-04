
package com.esp.dto;

import org.springframework.web.multipart.MultipartFile;
 
public class uploadFileDTO {
 
    private MultipartFile file;
    private String userListName;
    private String description;
 
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
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
}
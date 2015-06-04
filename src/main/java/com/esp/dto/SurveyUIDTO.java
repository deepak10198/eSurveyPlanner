package com.esp.dto;

import java.util.List;

/**
* <h1>User Survey DTO</h1>
* This DTO class is to maintain the following properties. 
* <p>
* surveyId
* surveyDisplayId
* surveyName
* surveyDesc
* surveyQuestions
* 
* 
* 
*</p>
* @author  Deepak Kumar
* @version 1.0
* @since   08-09-2014 
*/

public class SurveyUIDTO {
	
	private int surveyId;
	private String surveyDisplayId; // This is for survey id in the survey master tables.
	private String surveyName;
	private String surveyDesc;
	private List<QuestionUIDTO> surveyQuestions;
	private long surveyCount;
	private String published;
	private String surveystart;
    private String surveyend;
    private int type;
	
	public String toString(){
		
		String temp =null;
		
		temp= "surveyId:"+surveyId+"\n surveyDisplayId"+surveyDisplayId+"\n surveyName";
		
		for(QuestionUIDTO surveyQuestionDTO: surveyQuestions ){
			temp += "\n surveyQuestionDTO."+surveyQuestionDTO.getQuestionId();
				
			
					/*for(ElementDTO answer: surveyQuestionDTO.getAnswers()){
						if (answer!=null){
							temp += "\n getAnsId"+answer.getId()+"\n getAnsText"+answer.getText();	
							
						}
						
					}*/
					
								
		}
		
		return temp;
	}
	public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

	public String getSurveystart() {
        return surveystart;
    }

    public void setSurveystart(String surveystart) {
        this.surveystart = surveystart;
    }

    public String getSurveyend() {
        return surveyend;
    }

    public void setSurveyend(String surveyend) {
        this.surveyend = surveyend;
    }
	
	public int getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}
	public String getSurveyDisplayId() {
		return surveyDisplayId;
	}
	public void setSurveyDisplayId(String surveyDisplayId) {
		this.surveyDisplayId = surveyDisplayId;
	}
	public String getSurveyName() {
		return surveyName;
	}
	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	public String getSurveyDesc() {
		return surveyDesc;
	}
	public void setSurveyDesc(String surveyDesc) {
		this.surveyDesc = surveyDesc;
	}
	public List<QuestionUIDTO> getSurveyQuestions() {
		return surveyQuestions;
	}
	public void setSurveyQuestions(List<QuestionUIDTO> surveyQuestions) {
		this.surveyQuestions = surveyQuestions;
	}
	public long getSurveyCount() {
		return surveyCount;
	}
	public void setSurveyCount(long surveyCount) {
		this.surveyCount = surveyCount;
	}
	 public int getType() {
	        return type;
	    }

	    public void setType(int type) {
	        this.type = type;
	    }

	
	

}

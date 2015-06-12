package com.esp.handler;




import com.esp.entity.AnswerMaster;
import com.esp.entity.AnswerTextMaster;
import com.esp.entity.AnswerTypeMaster;
import com.esp.entity.QuestionAnswerMapping;
import com.esp.entity.QuestionMaster;
import com.esp.entity.SurveyMaster;
import com.esp.entity.SurveyQuestionMapping;
import com.esp.entity.SurveyResponse;
import com.esp.entity.SurveyTypeMaster;
import com.esp.entity.UserListMapping;
import com.esp.entity.UserMaster;
import com.esp.entity.UserSurveyUrlMapping;

import com.esp.service.AnswerMasterService;
import com.esp.service.GenericService;
import com.esp.service.SurveyMasterService;
import com.esp.service.SurveyResponseService;
import com.esp.entity.UserList;
import com.esp.dto.ElementDTO;
import com.esp.dto.FixedSurveyAnswersDTO;
import com.esp.dto.QuestionUIDTO;
import com.esp.dto.FixedSurveyQuestionsDTO;
import com.esp.dto.SurveyDTO;
import com.esp.dto.UserDetailsDTO;
import com.esp.dto.UserListDTO;

import com.esp.dto.SurveyDetailsDTO;
import com.esp.dto.SurveyAnswerDTO;
import com.esp.dto.SurveyQuestionDTO;
import com.esp.dto.SurveyResponseDTO;
import com.esp.dto.SurveyUIDTO;
import com.esp.dto.uploadFileDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;


import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.activation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;





@Component
public class HomeHandler {

	 private Logger log = Logger.getLogger(this.getClass().getName());

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date todayDate;
    
    @Autowired
    @Qualifier("AnswerTextMasterService")
    private GenericService answerTextMasterService;
    
    @Autowired
    @Qualifier("SurveyMasterService")
    private GenericService surveyMasterService;
    
    @Autowired
    @Qualifier("SurveyTypeMasterService")
    private GenericService surveyTypeMasterService;
    
    
    @Autowired
    @Qualifier("AnswerMasterService")
    private GenericService answerService;
    
    
    @Autowired
    @Qualifier("AnswerTypeMasterService")
    private GenericService answerTypeMasterService;
    
    @Autowired
    @Qualifier("QuestionMasterService")
    private GenericService questionMasterService;
    
    @Autowired
    @Qualifier("QuestionAnswerMappingService")
    private GenericService questionAnswerMappingService;
    
    @Autowired
    @Qualifier("SurveyQuestionMappingService")
    private GenericService surveyQuestionMappingService;
    
    @Autowired
    @Qualifier("SurveyResponseService")
    private GenericService<SurveyResponse> surveyResponseService;
    @Autowired
    @Qualifier("UserMasterService")
    private GenericService userMasterService;
    @Autowired
    @Qualifier("UserListService")
    private GenericService userListService;
    @Autowired
    @Qualifier("UserListMappingService")
    private GenericService userListMappingService;
    @Autowired
    @Qualifier("UserSurveyURLMappingService")
    private GenericService userSurveyURLMappingService;
    
    
 
	
   
    public UserMaster mapToUsermaster(UserMaster user, UserDetailsDTO details) throws Exception
    {
    	todayDate = new Date();
      	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    	
    	List<UserMaster> userMasterlist = userMasterService.fetchByParam(details.getLoginId());
    	
    	
    	
    	UserMaster usermaster = new UserMaster();
    	
    	if(userMasterlist==null || userMasterlist.isEmpty())
    	{
    		usermaster = saveUserMaster(user,details);
        	 
    		userMasterService.add(usermaster);
        	      	
        	        	
    	}
    	else
    	{
    		for(UserMaster userlist : userMasterlist)
    		{
    			usermaster.setId(userlist.getId());
    			usermaster.setFirstName(userlist.getFirstName());
    	    	usermaster.setMiddleName(userlist.getMiddleName());
    	    	usermaster.setLastName(userlist.getLastName());
    	    	usermaster.setGender(userlist.getGender());
    	    	usermaster.setDob(formatter.parse(userlist.getDob().toString()));
    	    	usermaster.setLoginId(userlist.getLoginId());
    	    	usermaster.setEmail(userlist.getEmail());
    	    	usermaster.setPassword(userlist.getPassword());
    	    	usermaster.setMaritalStatus(userlist.getMaritalStatus());
    	    	usermaster.setCreatedById(userlist.getCreatedById());
    	    	usermaster.setOrgId(userlist.getOrgId());
    	    	usermaster.setCreationDate(userlist.getCreationDate());
    		}
    			
    	}
    	
    	return usermaster;
    	
    }
    public UserMaster saveUserMaster(UserMaster user, UserDetailsDTO details) throws Exception
    {	
    	todayDate = new Date();
    	Date date = null;
    	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    	
    	UserMaster usermaster = new UserMaster();
    	usermaster.setFirstName(details.getFirstName());
    	usermaster.setMiddleName(details.getMiddleName());
    	usermaster.setLastName(details.getLastName());
    	usermaster.setGender(details.getGender());
    	usermaster.setDob(formatter.parse(details.getdob().toString()));
    	usermaster.setLoginId(details.getLoginId());
    	usermaster.setEmail(details.getEmail());
    	usermaster.setPassword(details.getPassword());
    	usermaster.setMaritalStatus(details.getMartialStatus());
    	usermaster.setCreatedById(user.getId().toBigInteger());
    	usermaster.setOrgId(user.getOrgId());
    	usermaster.setCreationDate(date);
    	
    	return usermaster;
    }

	public UserList maptoUserList(uploadFileDTO uploadfile, UserMaster user) {
		
		todayDate = new Date();
    	
		
    	UserList userlist = new UserList();
    	
    	userlist.setUserListName(uploadfile.getUserListName());
    	userlist.setDescription(uploadfile.getDescription());
    	userlist.setCreatedById(user);
    	userlist.setOrgId(user.getOrgId());
    
    	userlist.setCreationDate(todayDate);
    	
    	return userlist;
	}
	
	public UserListMapping mapToUserListMapping(UserList userlist,UserMaster usermaster) {
		
		UserListMapping usermap = new UserListMapping();
		todayDate = new Date();
		
		usermap.setUserId(usermaster);
		usermap.setUserListId(userlist);
		usermap.setCreationDate(todayDate);
		
		return usermap;
		
		
	}
	
    
	 public UserSurveyUrlMapping maptoUserSurveyUrl(UserMaster user,SurveyMaster survey, String link) {
			
	    	UserSurveyUrlMapping userUrl = new UserSurveyUrlMapping();
	    	todayDate = new Date();
	    	
	    	
	    	userUrl.setSurveyid(survey);
	    	userUrl.setUserid(user);
	    	userUrl.setUrlstring(link);
	    	userUrl.setCreationDate(todayDate);
	    	
	    	return userUrl;
	    	
	   	}
	
    
   

	public SurveyMaster mapToSurveymaster(UserMaster usermaster,SurveyTypeMaster surveyTypeMaster,SurveyDetailsDTO detailsVO) throws ParseException            
    {
		todayDate = new Date();
		Date date = null;
        SurveyMaster surveymaster = new SurveyMaster(); 
      
        surveymaster.setSurveyName(detailsVO.getSurveyname());
        surveymaster.setSurveyDesc(detailsVO.getDescription());       
        surveymaster.setCreatedDate(todayDate);
        date = dateFormat.parse(detailsVO.getSurveyend().trim());
        surveymaster.setEndDate(date);
        date = dateFormat.parse(detailsVO.getSurveystart());
        surveymaster.setStartDate(date);
        surveymaster.setLastModifiedDate(todayDate);
        surveymaster.setSurveyCategory(null);
        surveymaster.setSurveyTypeId(surveyTypeMaster);
        surveymaster.setCreatedById(usermaster);
        surveymaster.setLastModifiedById(usermaster);
        
        return surveymaster;
        
    }
	public SurveyMaster mapToUpdateSurveymaster(SurveyDetailsDTO detailsVO,SurveyUIDTO existSurveyDetails) throws ParseException            
    {
		todayDate = new Date();
		Date date = null;
        SurveyMaster surveymaster = new SurveyMaster(); 
        SurveyTypeMaster surveytypemaster = (SurveyTypeMaster) surveyTypeMasterService.fetch(BigDecimal.valueOf(existSurveyDetails.getType()));
        
      
        surveymaster.setId(detailsVO.getSurveyId());
        surveymaster.setSurveyName(detailsVO.getSurveyname());
        surveymaster.setSurveyDesc(detailsVO.getDescription());
        surveymaster.setSurveyTypeId(surveytypemaster);
        date = dateFormat.parse(detailsVO.getSurveyend().trim());
        surveymaster.setEndDate(date);
        date = dateFormat.parse(detailsVO.getSurveystart());
        surveymaster.setStartDate(date);
        surveymaster.setLastModifiedDate(todayDate);
        surveymaster.setSurveyCategory(null);
       
        return surveymaster;
        
    }
    
	 public AnswerMaster mapToAnswerMaster(FixedSurveyAnswersDTO ansDetailsDTO) throws Exception    
	    {
	    	log.info("--- mapToAnswerMaster :");
	        Date date = null;
	        Class noparams[] = {};
	        
	        Class[] paramAnsText = new Class[1];
	        paramAnsText[0] = AnswerTextMaster.class;
	        
	        AnswerMaster ansMaster = new AnswerMaster(); 
	        
	        List<String> ansTextList = ansDetailsDTO.getAnsTextList();
	        List<String> otherinfo = ansDetailsDTO.getOther();
	        
	        log.info("----descList.size() :"+ansTextList);	
	        
	        if (ansTextList.size() >5) throw new Exception("Answers's list should not be greater than 5");
	    	Class ansMasterClass = AnswerMaster.class;
	    	
	        for (int i =0; i<ansTextList.size(); i++ ) {
	            Method method = ansMasterClass.getDeclaredMethod("setAnsText"+(i+1), paramAnsText);
	        	
	        	method.invoke(ansMaster,saveAnsText(ansTextList.get(i),otherinfo.get(i)) );
	        	        	
			}
	        
	        answerService.add(ansMaster); 
	        return ansMaster;
	        
	    }
    
    
   private AnswerTextMaster saveAnsText(String text, String otherinfo) throws ParseException    {
	   
	  
	   
	   char first = Character.toUpperCase(text.charAt(0));
	   text = first + text.substring(1).toLowerCase();
	   
	   log.info("Answertext :"+text);
	   
	   List<AnswerTextMaster> ansTextList = answerTextMasterService.fetchByMultipleParam(text, otherinfo);
	  
	   
	   System.out.println("....2.1 =-----"+ansTextList);
	   
	   if (ansTextList==null || ansTextList.isEmpty()){
		   AnswerTextMaster ansText = maptoAnswerTextMaster(text,otherinfo);
		  
		   answerTextMasterService.add(ansText);
		   ansTextList = answerTextMasterService.fetchByMultipleParam(text, otherinfo);
		   
		   
	   }
	   
	   return ansTextList.get(0);
	   
   }
   public AnswerTextMaster maptoAnswerTextMaster(String text, String otherinfo)
   {
	   AnswerTextMaster anstext = new AnswerTextMaster();
	   anstext.setAnsText(text);
	   anstext.setOtherInfo(otherinfo);
	   return anstext;
   }
    
	
	public String handleRequest(){
		
		
		return "home";
	   }
	

	
	/*public SurveyDetailsDto setDetails(ApplicationContext context, ModelMap model,HttpServletRequest request){
		
		//context= request.getSession().getServletContext();
		
		//WebApplicationContext context= WebApplicationContextUtils.getRequiredWebApplicationContext(contex1t);
		SurveyDetailsDto surveyDetails= context.getBean("surveyDetailsDto",SurveyDetailsDto.class); 
		surveyDetails.setSurveyName(request.getParameter("surveyname"));
		surveyDetails.setDescriotion(request.getParameter("description"));
		
		
		
		Date sDate=null;
		Date eDate=null;
		try {
			sDate = dateFormat.parse(request.getParameter("surveystart"));
			eDate= dateFormat.parse(request.getParameter("surveyend"));
		} catch (ParseException e) {
		
			e.printStackTrace();
		}
		
		
		surveyDetails.setStartDate(sDate);
		surveyDetails.setEndDate(eDate);
		surveyDetails.setSurveyType(request.getParameter("type"));
		
		model.addAttribute("survey",surveyDetails);
		
		return surveyDetails;
		
	}*/
        

	public SurveyQuestionMapping mapToQuestionMaster(FixedSurveyQuestionsDTO questionDTO, UserMaster usermaster,SurveyDTO surveyDTO ){
		
	
		
		todayDate = new Date();
		QuestionAnswerMapping questionAnswerMapping = null;
		SurveyQuestionMapping surveyQuestionMapping = null;
		SurveyMaster surveyMaster = (SurveyMaster) surveyMasterService.fetch(BigDecimal.valueOf(surveyDTO.getSurveyId()));
		
		QuestionMaster questionMaster =	new QuestionMaster(); 
		int i=0;
		for(String questionText: questionDTO.getQuestionText()){
			
			String mandatory = questionDTO.getMandatory().get(i);
			
		
			AnswerMaster answerMaster= null;
			questionMaster = saveAndfetchQuesText(questionText,usermaster);
			
			
			if(questionDTO.getAnsTypeId() != 3)
			{
				 answerMaster = (AnswerMaster) answerService.fetch(BigDecimal.valueOf(questionDTO.getAnsId()));
				 
			}
			AnswerTypeMaster answerTypeMaster = (AnswerTypeMaster) answerTypeMasterService.fetch(BigDecimal.valueOf(questionDTO.getAnsTypeId()));
			
			questionAnswerMapping = saveQuesAnsMapping(questionMaster,answerMaster,answerTypeMaster , usermaster);
			surveyQuestionMapping = saveSurveyQuestionMapping(surveyMaster,questionAnswerMapping,usermaster,mandatory);
			i++;
			
		}
		
		
		
			
		return surveyQuestionMapping;
	}
	
	
	
	private QuestionMaster saveAndfetchQuesText(String questionText, UserMaster usermaster )    {
		   
		 log.info("questionText :--"+questionText);	
		   List<QuestionMaster> quesMasterList = questionMasterService.fetchByParam(questionText);
		   
		   if (quesMasterList==null || quesMasterList.isEmpty()){
			   QuestionMaster questionMaster =	new QuestionMaster(); 
			   questionMaster.setCreatedById(usermaster);
				questionMaster.setCreationDate(todayDate);
				questionMaster.setLastModifiedById(usermaster);
				questionMaster.setLastModifiedDate(todayDate);
				questionMaster.setQuestionText(questionText);
				questionMasterService.add(questionMaster);
			   
				quesMasterList = questionMasterService.fetchByParam(questionText);
			   
			   
		   }
		   
		   return quesMasterList.get(0);
		   
	   } 
	
	
	
private QuestionAnswerMapping saveQuesAnsMapping( QuestionMaster questionMaster, AnswerMaster answerMaster, AnswerTypeMaster answerTypeMaster, UserMaster usermaster){
		
		todayDate = new Date();
		QuestionAnswerMapping questionAnswerMapping = new QuestionAnswerMapping();
		
		questionAnswerMapping.setAnsTypeId(answerTypeMaster);
		questionAnswerMapping.setAnswerId(answerMaster);
		questionAnswerMapping.setCreatedById(usermaster);
		questionAnswerMapping.setCreatedDate(todayDate);
		questionAnswerMapping.setLastModifiedById(usermaster);
		questionAnswerMapping.setLastModifiedDate(todayDate);
		questionAnswerMapping.setQuestionId(questionMaster);
		
		questionAnswerMappingService.add(questionAnswerMapping);
		
		
		return questionAnswerMapping;
		
	}
	
	
public SurveyQuestionMapping saveQuestionAnswers(SurveyResponseDTO surveyResponseDTO, UserMaster usermaster) throws Exception{
	
	SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(BigDecimal.valueOf(surveyResponseDTO.getSurveyId()));
	
	QuestionAnswerMapping questionAnswerMapping = null;
	SurveyQuestionMapping surveyQuestionMapping = null;
	
	QuestionMaster questionMaster =	new QuestionMaster(); 
	
	for(SurveyQuestionDTO questionDTO: surveyResponseDTO.getSurveyQuestions()){
		
		String quesText=questionDTO.getQuestionText();
		String mandatory= questionDTO.getMandatory();
		
	
		
	
		if(quesText!=null && !"".equals(quesText)){
			questionMaster = saveAndfetchQuesText(quesText,usermaster);
			
			AnswerMaster answerMaster = null;
			if(questionDTO.getAnsTypeId()!=3){
				FixedSurveyAnswersDTO ansDetailsDTO = new FixedSurveyAnswersDTO();
				ansDetailsDTO.setAnsTypeId(questionDTO.getAnsTypeId());
				ansDetailsDTO.setAnsTextList(questionDTO.getAnsTextList());
				System.out.println("--------^^^^"+questionDTO.getOther());
				ansDetailsDTO.setOther(questionDTO.getOther());
				
				ansDetailsDTO.setOther(questionDTO.getOther());
				
				answerMaster = mapToAnswerMaster(ansDetailsDTO);
				
			}
			
			AnswerTypeMaster answerTypeMaster = (AnswerTypeMaster) answerTypeMasterService.fetch(BigDecimal.valueOf(questionDTO.getAnsTypeId()));
			
			questionAnswerMapping = saveQuesAnsMapping(questionMaster,answerMaster,answerTypeMaster , usermaster);
			surveyQuestionMapping = saveSurveyQuestionMapping(surveyMaster,questionAnswerMapping,usermaster,mandatory);
			
		}
		
		
	}
	
	
	return surveyQuestionMapping;
	
}
	
public SurveyQuestionMapping saveSurveyQuestionMapping(SurveyMaster surveyMaster, QuestionAnswerMapping questionAnswerMapping,UserMaster usermaster,String mandatory){
	
	SurveyQuestionMapping surveyQuestionMapping = new SurveyQuestionMapping();
	
	surveyQuestionMapping.setCreatedById(usermaster);
	surveyQuestionMapping.setCreationDate(todayDate);
	surveyQuestionMapping.setLastModifiedById(usermaster);
	surveyQuestionMapping.setLastModifiedDate(todayDate);
	surveyQuestionMapping.setQuestionAnsId(questionAnswerMapping);
	surveyQuestionMapping.setSurveyId(surveyMaster);
	surveyQuestionMapping.setMandatory(mandatory);
	
	
	surveyQuestionMappingService.add(surveyQuestionMapping);
	return surveyQuestionMapping;
}
	
	
	public String handleAllRequest(HttpServletRequest request){
		
		String page = request.getRequestURI();
		log.info("URI req "+ page);
		page = page.substring(page.lastIndexOf("/")+1, page.length());
		return page;
	}
	
	/**
     * This method is used to fetch all the details of Survey in order to display it on the basis of Survey ID.
     * 
     * @param surveyId Survey ID for which details needs to be fetched.
     * @return UserSurveyDTO DTO having all the details of a survey.
     */
	
	public SurveyUIDTO fetchSurveyDetails(int surveyId){
		
		
		log.info("Fetching Survey Details for Survey :"+surveyId);
		
		SurveyUIDTO userSurveyDTO = null;
		userSurveyDTO= new SurveyUIDTO();
		SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(BigDecimal.valueOf(surveyId));
		
		
		userSurveyDTO.setSurveyId(Integer.parseInt(surveyMaster.getId().toString()));
		userSurveyDTO.setSurveyDisplayId(surveyMaster.getSurveyId());
		userSurveyDTO.setSurveyName(surveyMaster.getSurveyName());
		userSurveyDTO.setSurveyDesc(surveyMaster.getSurveyDesc());
		userSurveyDTO.setSurveystart(surveyMaster.getStartDate().toString());
		userSurveyDTO.setSurveyend(surveyMaster.getEndDate().toString());
		
		System.out.println("------"+surveyMaster.getSurveyTypeId());
		userSurveyDTO.setType(decToInt(surveyMaster.getSurveyTypeId().getId()));
		
		userSurveyDTO.setPublished(surveyStatus(surveyMaster.getStartDate(),surveyMaster.getEndDate()));	
		
		
		userSurveyDTO.setSurveyQuestions(fetchSurveyQuestions(surveyId));
		
		String query="Select count(distinct email_id) from Survey_Response where survey_ID="+surveyId;
		
		int surveyCount= decToInt(surveyResponseService.count(query));
		
		userSurveyDTO.setSurveyCount(surveyCount);
		
		
		return userSurveyDTO;
		
	}
	public boolean deleteSurveyDetail(int surveyId){
		
		
		boolean resultDetail = deleteSurveyQuestions(surveyId);
	
		if(resultDetail == true)
		{
			
			
			
			surveyMasterService.delete(BigDecimal.valueOf(surveyId));
			log.info("  Delete complete for Survey : "+surveyId);
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	public boolean deleteSurveyQuestions(int surveyId){
		
		log.info("Deleting Survey Questions & answers --");
		SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(BigDecimal.valueOf(surveyId));
		List<SurveyQuestionMapping> surveyQuestionMappingList =   surveyQuestionMappingService.fetchByParam(surveyMaster.getId());
		
		String qery= "select Count(*) from user_survey_url_mapping where surveyid="+surveyId;
		int surveyurlCount= decToInt(userSurveyURLMappingService.count(qery));
		for(int i=1; i<=surveyurlCount; i++)
		{
			
			userSurveyURLMappingService.deleteObj(BigDecimal.valueOf(surveyId));
			
		}
		
		
		String qry= "select Count(*) from survey_response where survey_Id="+surveyId;
		int surveyCount= decToInt(surveyResponseService.count(qry));
		
		
		
		for(int i=1; i<=surveyCount; i++)
		{
			
			surveyResponseService.deleteObj(BigDecimal.valueOf(surveyId));
			
		}
		for(SurveyQuestionMapping  surveyQuestionMapping: surveyQuestionMappingList){
			
				
			
				QuestionAnswerMapping questionAnswerMapping= surveyQuestionMapping.getQuestionAnsId();
				
				surveyQuestionMappingService.delete(surveyQuestionMapping.getId());
				
				
				questionAnswerMappingService.delete(questionAnswerMapping.getId());
				
				
		}

		for(SurveyQuestionMapping  surveyQuestionMapping: surveyQuestionMappingList){
			
			QuestionAnswerMapping questionAnswerMapping= surveyQuestionMapping.getQuestionAnsId();
		
			
				
				try
				{
					if(decToInt(surveyQuestionMapping.getSurveyId().getSurveyTypeId().getId()) == 1)
					{
						
						answerDelete(questionAnswerMapping.getAnswerId().getId());
						break;
					}
					else
					{
						answerDelete(questionAnswerMapping.getAnswerId().getId());
						
					}
				}
				catch(NullPointerException e)
				{
					log.info("List Empty");
					break;	
				}
				
		}
		
		return true;	
		
	}
	
	public boolean answerDelete(BigDecimal id)
	{
		
		
		answerService.delete(id);

		
		return true;
	}
	public boolean deleteUserList(int userlistId){
		log.info("user list deletion start");
		
		String qry = "select count(*) from User_List_Mapping where user_list_id = "+userlistId;
    	int count = decToInt(userListMappingService.count(qry));
    
    	if(count>0)
    	{
	    	boolean listMap= false;
	    	for(int i=0; i<count; i++)
	    	{
	    		listMap = userListMappingService.deleteObj(BigDecimal.valueOf(userlistId));
	    	}
			
	    	if(listMap == true)
			{
				return userListService.delete(BigDecimal.valueOf(userlistId));
			}
			else
			{
					return false;
			}
    	}
    	else
    	{
    		return userListService.delete(BigDecimal.valueOf(userlistId));
    	}
	
	}
	public boolean deleteQuestion(int questionId, SurveyDetailsDTO surveyDetails) {
		
		log.info("Deleting Question :"+questionId);
		List<QuestionAnswerMapping> quesAnsMapList = questionAnswerMappingService.fetchByParam(BigDecimal.valueOf(questionId));
		boolean answerDel = false;
		boolean quesansmapDel = false;
		for(QuestionAnswerMapping quesAnsMap :quesAnsMapList)
		{
			List<SurveyQuestionMapping> surveyquesMapList = surveyQuestionMappingService.fetchByMultipleParam(quesAnsMap.getId(), surveyDetails.getSurveyId());
			for(SurveyQuestionMapping surveyquesMap :surveyquesMapList)
			{
				if(surveyquesMap != null)
				{
					boolean surveyquesmapdel = surveyQuestionMappingService.delete(surveyquesMap.getId());
					
					quesansmapDel = questionAnswerMappingService.delete(surveyquesMap.getQuestionAnsId().getId());
					 if(decToInt(quesAnsMap.getAnsTypeId().getId())!=3)
					 {
						if(surveyDetails.getType() == 2)
						{
							 answerDel = answerService.delete(surveyquesMap.getQuestionAnsId().getAnswerId().getId());
						}
						else
						{
							List<SurveyQuestionMapping> surveyMaplist = surveyQuestionMappingService.fetchByParam(surveyDetails.getSurveyId());
							
							if(surveyMaplist.size()==0)
							{
								 answerDel = answerService.delete(surveyquesMap.getQuestionAnsId().getAnswerId().getId());
							}
						}
					 }
				}
			}
		}
		if(answerDel==true ||quesansmapDel== true )
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	


	public SurveyDetailsDTO fetchSurveyDetail(int surveyId) throws ParseException{
		
		log.info("Fetching Survey Detail for Survey --- :"+surveyId);
		
		String DATE_FORMAT = "yyyy-MM-dd";
	   
	    
	    SimpleDateFormat ft =  new SimpleDateFormat (DATE_FORMAT,Locale.getDefault());
	    
		
		
		SurveyDetailsDTO surveyDTO=null;
		
		surveyDTO= new SurveyDetailsDTO();
		
		SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(BigDecimal.valueOf(surveyId));
		
		surveyDTO.setSurveyId(surveyMaster.getId());
		surveyDTO.setSurveyname(surveyMaster.getSurveyName().toString());
		surveyDTO.setDescription(surveyMaster.getSurveyDesc().toString());
		
		
		
		Date strDate= ft.parse(surveyMaster.getStartDate().toString());
		Date endDate= ft.parse(surveyMaster.getEndDate().toString());
		
		SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-YYYY");
		
		String strt=ft1.format(strDate);
		String end=ft1.format(endDate);
		
		surveyDTO.setSurveystart(strt);
		surveyDTO.setSurveyend(end);
		
		surveyDTO.setPublished(surveyStatus(surveyMaster.getStartDate(),surveyMaster.getEndDate()));
		
		surveyDTO.setSurveyQuestions(fetchSurveyQuestions(surveyId));
		
	
		return surveyDTO;
	
		
	}
	public List<UserListDTO> fetchAlluserlists() throws Exception
	{
		
		log.info("Fetching details of all user lists");
		List<UserList> userlist = userListService.fetchAll();
		
		List<UserListDTO> userListDetails = new ArrayList<UserListDTO>();
		
		for(UserList user : userlist)
		{
			UserListDTO list = new UserListDTO();
			String qry = "select count(user_id) from User_List_Mapping where user_list_id = "+user.getId();
	    	int count = decToInt(userListMappingService.count(qry));
	    	
	    	DateFormat dateParse = new SimpleDateFormat("yyyy-MM-dd");
	    	 Date date =  dateParse.parse(user.getCreationDate().toString());
	    	 DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		    	
			list.setUserlistId(user.getId());
			list.setUserListName(user.getUserListName());
			list.setDescription(user.getDescription());
			list.setCreationDate(dateFormat.format(date));
			list.setCount(count);
			userListDetails.add(list);
		}
		
		return userListDetails;
	}
	public List<SurveyDetailsDTO> fetchAllSurveyDetails() throws Exception{
		
		log.info("Fetching Details of all the surveys");
		
		List<SurveyMaster> surveyMaster = surveyMasterService.fetchAll();
    	
			
   	 
			List<SurveyDetailsDTO> surveyDetails = new ArrayList<SurveyDetailsDTO>();
   	
			for(SurveyMaster surveymaster: surveyMaster)
			{
					
				String DATE_FORMAT = "yyyy-MM-dd";
						    
			    SimpleDateFormat ft =  new SimpleDateFormat (DATE_FORMAT,Locale.getDefault());
			   
					SurveyDetailsDTO survey=new SurveyDetailsDTO();
   		
					survey.setSurveyname(surveymaster.getSurveyName().toString());
					
					survey.setDescription(surveymaster.getSurveyDesc().toString());
					survey.setSurveyId(surveymaster.getId());
					
					
					Date strDate= ft.parse(surveymaster.getStartDate().toString());
					Date endDate= ft.parse(surveymaster.getEndDate().toString());
					
					SimpleDateFormat ft1 = new SimpleDateFormat("dd-MM-yyyy");
					
					String strt=ft1.format(strDate);
					String end=ft1.format(endDate);
					
					survey.setSurveystart(strt);
					survey.setSurveyend(end);
					
					survey.setPublished(surveyStatus(surveymaster.getStartDate(),surveymaster.getEndDate()));
					
					surveyDetails.add(survey);
   		
			}
			
		return surveyDetails;
		
	}
	public String surveyStatus(Date start, Date end)
	{	
		log.info(" Checking survey Status (Pending/Active/Closed) ");
		
		todayDate = new Date();
		String active="Active";
		String closed="Closed";
		String pending="Pending";
		
		if(todayDate.after(start) && todayDate.before(end))
		{
			return active;
		}
		if(todayDate.after(end))
		{
			return closed;
		}
		if(todayDate.before(start))
		{
			return pending;
		}
		
		return null;
		
	}
	
	
	
	
	public List<QuestionUIDTO> fetchSurveyQuestions(int surveyId){
		
		log.info("Fetching Survey Questions for survey :"+surveyId);
		
		List<QuestionUIDTO> questionsList = new ArrayList<QuestionUIDTO>();
		
		
		SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(BigDecimal.valueOf(surveyId));
		
		List<SurveyQuestionMapping> surveyQuestionMappingList =   surveyQuestionMappingService.fetchByParam(surveyMaster.getId());
		
		
		for(SurveyQuestionMapping  surveyQuestionMapping: surveyQuestionMappingList){
			
			QuestionUIDTO questionUIDTO = new QuestionUIDTO();
			List<String> ansTextList = new ArrayList<String>();
			List<ElementDTO> answerList = new ArrayList<ElementDTO>();
			
			String mandatory = surveyQuestionMapping.getMandatory().toString();
			
			
			//QuestionAnswerMapping questionAnswerMapping= (QuestionAnswerMapping) questionAnswerMappingService.fetch (Integer.parseInt(surveyQuestionMapping.getQuestionAnsId().toString())) ;
			
			QuestionAnswerMapping questionAnswerMapping= surveyQuestionMapping.getQuestionAnsId();
			
			String query = "Select count(distinct email_id) from Survey_Response where survey_ID="+surveyId+" AND ques_ans_mapping_id="+surveyQuestionMapping.getQuestionAnsId().getId();
	
			int qCount = decToInt(surveyResponseService.count(query));
			
			
		
			
			questionUIDTO.setQuesAnswerId(decToInt(questionAnswerMapping.getId()));
			
			if(decToInt(questionAnswerMapping.getAnsTypeId().getId()) != 3)
			{
				answerList = mapAnswersMasterToList(questionAnswerMapping.getAnswerId(),questionAnswerMapping.getId());
				questionUIDTO.setAnswers(answerList);
				

				
			}
			else
			{
				List<SurveyResponse> surveyres = surveyResponseService.fetchByParam(questionAnswerMapping);
				ElementDTO answer =null;
				for(SurveyResponse s :surveyres )
				{
					answer = new ElementDTO();
					answer.setText(s.getAnsText());
				
					answerList.add(answer);
				}
			
				
				questionUIDTO.setAnswers(answerList);
		
			}
			
			
			
			
			questionUIDTO.setAnsType(questionAnswerMapping.getAnsTypeId().getAnsTypeText());
			questionUIDTO.setAnsTypeId(decToInt(questionAnswerMapping.getAnsTypeId().getId()));
			questionUIDTO.setQuestionId(decToInt(questionAnswerMapping.getQuestionId().getId()));
			questionUIDTO.setQuestionText(questionAnswerMapping.getQuestionId().getQuestionText());
			questionUIDTO.setSurveyQuestionCount(qCount);
			questionUIDTO.setMandatory(mandatory);
			
			questionsList.add(questionUIDTO);
			
		}
		
		
		return questionsList;
		
	}
	public List<ElementDTO> answerMasterToList(AnswerMaster answerMaster){
		
		List<ElementDTO> answerList = new ArrayList<ElementDTO>();
		
		Class ansMaster  = AnswerMaster.class;
		try{
			for(int i=1; i<=10; i++){
				Method method = AnswerMaster.class.getDeclaredMethod("getAnsText"+i);
				 
				AnswerTextMaster answerTextMaster = (AnswerTextMaster) method.invoke(answerMaster);
				ElementDTO answer = null;
				if (answerTextMaster!=null){
					answer = new ElementDTO();
					answer.setId( decToInt(answerTextMaster.getId()) );
					answer.setText(answerTextMaster.getAnsText());
					answer.setOther(answerTextMaster.getOtherInfo());
					
					answerList.add(answer);
					
				}else{}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return answerList;
		
	}
	
	private List<ElementDTO> mapAnswersMasterToList(AnswerMaster answerMaster, BigDecimal quesAnsMap){
		
		log.info("Mapping Answers to list \n \n");
		
		log.info("...mapAnswersMasterToList"+answerMaster.getId());
		
		List<ElementDTO> answerList = new ArrayList<ElementDTO>();
		
		
		Class ansMaster  = AnswerMaster.class;
		try{
			for(int i=1; i<=10; i++){
				Method method = AnswerMaster.class.getDeclaredMethod("getAnsText"+i);
				 
				AnswerTextMaster answerTextMaster = (AnswerTextMaster) method.invoke(answerMaster);
				ElementDTO answer = null;
				
				if (answerTextMaster!=null){
					answer = new ElementDTO();
					answer.setId( decToInt(answerTextMaster.getId()) );
					answer.setText(answerTextMaster.getAnsText());
					answer.setOther(answerTextMaster.getOtherInfo());
					
					if(("true").equals(answerTextMaster.getOtherInfo()))
					{
						List<SurveyResponse> otherTextAns = surveyResponseService.fetchByMultipleParam(answerTextMaster.getId(), quesAnsMap);
						List<String> othert = new ArrayList<String>();
						for(SurveyResponse othertext : otherTextAns)
						{
							othert.add(othertext.getAnsText().toString());
						}
						answer.setOtherText(othert);
					}
					
					String query = "Select count(distinct email_id) from Survey_Response where ans_text_id="+answerTextMaster.getId()+"AND ques_ans_mapping_id="+quesAnsMap;
					
					int aCount=decToInt(surveyResponseService.count(query));
					
					answer.setElementCount(aCount);
					
				
				
					answerList.add(answer);
					
				}else{
					
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return answerList;
		
	}
	
	/*public List<SurveyAnswerDTO> fetchSurveyresponse() throws IOException
	{
	
		
	}
	*/
	public boolean editQuestionText(QuestionUIDTO question, int id, SurveyDetailsDTO survey) {
		QuestionMaster ques =null;
		ques = mapToQuestionMaster(question,id);
		
		questionMasterService.update(ques);
		return true;
	}
	
	private QuestionMaster mapToQuestionMaster(QuestionUIDTO question, int id) {
		QuestionMaster ques = new QuestionMaster();
		ques.setId(BigDecimal.valueOf(id));
		ques.setQuestionText(question.getQuestionText());
		ques.setLastModifiedDate(todayDate);
		return ques;
		
	}
	public void updateMandatoryStatus(QuestionUIDTO question,SurveyDetailsDTO survey,int uri) {
		
		List<QuestionAnswerMapping> quesansmapList = questionAnswerMappingService.fetchByParam(BigDecimal.valueOf(uri));
		for(QuestionAnswerMapping quesansmap :quesansmapList)
		{
			List<SurveyQuestionMapping> surveyquesmapList = surveyQuestionMappingService.fetchByMultipleParam(quesansmap.getId(), survey.getSurveyId());
			for(SurveyQuestionMapping surveyquesmap : surveyquesmapList)
			{
				SurveyQuestionMapping surveyQues = mapToSurveyQuestionMapping(surveyquesmap,question);
				surveyQuestionMappingService.update(surveyQues);
			}
			
		}
		
		
	}
	private SurveyQuestionMapping mapToSurveyQuestionMapping(SurveyQuestionMapping surveyquesmap, QuestionUIDTO question) {
		SurveyQuestionMapping surveyQues = new SurveyQuestionMapping();
		
		surveyQues.setId(surveyquesmap.getId());
		surveyQues.setQuestionAnsId(surveyquesmap.getQuestionAnsId());
		surveyQues.setSurveyId(surveyquesmap.getSurveyId());
		surveyQues.setLastModifiedDate(todayDate);
		surveyQues.setLastModifiedById(surveyquesmap.getCreatedById());
		if(question.getMandatory().equalsIgnoreCase("true")){
			surveyQues.setMandatory("true");
		}
		else{
			surveyQues.setMandatory("false");
		}
		
		return surveyQues;
	}
	public void submitSurveyResponse(SurveyResponseDTO surveyResponseDTO, UserMaster usermaster, String ipAddress, String email){
		log.info("Submitting user response and save record in the database");
		
		SurveyMaster surveyMaster = (SurveyMaster) surveyMasterService.fetch(BigDecimal.valueOf(surveyResponseDTO.getSurveyId()));
		
			for (SurveyQuestionDTO surveyQuestionDTO: surveyResponseDTO.getSurveyQuestions()){
				SurveyResponse surveyResponse = new SurveyResponse();
				
				QuestionAnswerMapping quesAnsMapping=(QuestionAnswerMapping) questionAnswerMappingService.fetch(BigDecimal.valueOf(surveyQuestionDTO.getQuesAnswerId()));
				
				surveyResponse.setEmailId(email);
				surveyResponse.setIpAddress(ipAddress);
				
				surveyResponse.setQuesAnsMappingId(quesAnsMapping);
				surveyResponse.setSurveyId(surveyMaster);
				surveyResponse.setUserId(usermaster);
				
				
				if(surveyQuestionDTO.getAnsTextList()!=null  ){
				for (String ansId: surveyQuestionDTO.getAnsTextList() ){
					if((surveyQuestionDTO.getAnsTypeId()==1 || surveyQuestionDTO.getAnsTypeId()==2))
					{
						AnswerTextMaster answerText = (AnswerTextMaster) answerTextMasterService.fetch(new BigDecimal(ansId.trim()));
						surveyResponse.setAnsTextId(answerText);
						
						if(("true").equals(answerText.getOtherInfo()))
						{
							surveyResponse.setAnsText(surveyQuestionDTO.getOtherText());
						}
						else
						{
							surveyResponse.setAnsText(answerText.getAnsText());
						}
						surveyResponseService.add(surveyResponse);
					}
					else
					{
						surveyResponse.setAnsText(ansId);
						surveyResponseService.add(surveyResponse);
					}
				}
			}
				
			}
		}
	
	
	
	/**
     * This method is used to fetch all survey types.
     * 
     * @param NA
     * @return List of Element DTO.
	 * @throws Exception if there is not survey types defined.
     */
	public List<ElementDTO> fetchSurveyType () throws Exception{
		
		log.info("Request for fetching survey type");
		List<ElementDTO> surveyTypes = new ArrayList<ElementDTO>();
		
		List<SurveyTypeMaster> surveyTypeMasters = surveyTypeMasterService.fetchAll();
		
		if(surveyTypeMasters==null || surveyTypeMasters.isEmpty()){
			
			log.error("No Survey Type Defined");
			
			throw new Exception("There is no survey type defined!");
		}
		
		for(SurveyTypeMaster surveyTypeMaster: surveyTypeMasters){
			ElementDTO element = new ElementDTO(); 
			element.setId( decToInt(surveyTypeMaster.getId()) );
			element.setText(surveyTypeMaster.getSurveyTypeText());
			
			surveyTypes.add(element);
		}
		
		return surveyTypes;
		
	}
	public  List<UserDetailsDTO> fetchuserDetails(BigDecimal Id)
	{
		 
		 List<UserDetailsDTO> users = new ArrayList<UserDetailsDTO>();
		 List<UserListMapping> userid = userListMappingService.fetchByParam(Id);
		 
		 userid.size();
		
		 for(UserListMapping user : userid)
		 {
			 UserDetailsDTO userDet = new UserDetailsDTO();
			 UserMaster userFetchDet =   (UserMaster) userMasterService.fetch(user.getUserId().getId());
			 
			 userDet.setFirstName(userFetchDet.getFirstName());
			 userDet.setEmail(userFetchDet.getEmail());
			 userDet.setUserid(userFetchDet.getId());
			 users.add(userDet);
						 
		 }
		 return users;
		 
	}
	public boolean doSendMail(String sender,String password,BigDecimal recieveId, String text, String subject, String link, BigDecimal surveyId) throws AddressException,MessagingException  {
		
		log.info("Mail sending  start");
		
			Properties prop = System.getProperties();
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.socketFactory.port", "587");
			prop.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			prop.put("mail.smtp.starttls.enable", true);
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "587");

			Session session = Session.getInstance(prop, null);	
			MimeMessage message = new MimeMessage(session);
			
			message.setFrom(sender);
			 
			 
			List<UserDetailsDTO> userlist = fetchuserDetails(recieveId);
			 
			
						 
			 String[] email = new String[userlist.size()] ;
			 Long[] uniquecode = new Long[userlist.size()] ;
			 
			 int i = 0;
			 for(UserDetailsDTO user : userlist)
			 {
				email[i] = user.getEmail();
				uniquecode[i] = uniqueEncode(user.getUserid(),surveyId);
			
				i++;
			 }
			 
			 
			 InternetAddress emailTo = new InternetAddress();
			  
			 for(int j=0; j<email.length; j++)
			 {
				 emailTo = new InternetAddress(email[j].toString());
			
			 
				 message.setRecipient(Message.RecipientType.TO, emailTo);
				
				 
				 message.setSubject(subject);
				 message.setSentDate(new Date());
				 
				 String messageBody = text + " \n \n URL :- \t "+link+"&u"+uniquecode[j];
				 message.setText(messageBody);
				 			 
				 
				 try
				 {
					 Transport.send(message, sender, password); // ----------------- mail sending 
				 }
				 catch(AuthenticationFailedException e)
				 {
					 log.info("Wrong User id and password");
					 e.printStackTrace();
					 return false;
				 }
			 }

		     log.info("mail Sent \n  \n Saving Records ");
		     
		     SurveyMaster survey = (SurveyMaster) surveyMasterService.fetch(surveyId);
			 List<UserListMapping> users = userListMappingService.fetchByParam(recieveId);
		     
			 
		     for(UserListMapping user :users )
		     {
				    		UserMaster USER = (UserMaster) userMasterService.fetch(user.getUserId().getId());
				    		
				    		List<UserSurveyUrlMapping> uSurvey = userSurveyURLMappingService.fetchByMultipleParam(USER.getId(), survey.getId());
				    		
				    		if(uSurvey == null || uSurvey.isEmpty())
				    		{
				    			UserSurveyUrlMapping userSurveyUrl = maptoUserSurveyUrl(USER,survey,link);
					    	 
					    		userSurveyURLMappingService.add(userSurveyUrl);
					    	}
		  
		    	
		     }
		     log.info("End");
        return true;
    }
		
	
	
	
	
	
	
	
	/**
     * This method is used to convert the BigDecimal value into integer.
     * 
     * @param surveyId Survey ID for which details needs to be fetched.
     * @return UserSurveyDTO DTO having all the details of a survey.
     */
	private int decToInt(BigDecimal dec){
		
		return Integer.parseInt(dec.toString());
		
	}
	public long uniqueEncode(BigDecimal userid , BigDecimal surveyid)
	{
		long id=0;
		int user= decToInt(userid);
		int survey= decToInt(surveyid);
		user=user*13;
		survey = survey*13;
		
		id= user+survey;
		id=id*13;
		
		
		return id;
	}
	public long uniqueDecode(long uniqueCode , long surveyid)
	{
		
		long id=0;
		uniqueCode = uniqueCode/13;
		surveyid = surveyid*13;
		uniqueCode = uniqueCode-surveyid;
		id= uniqueCode/13;
		
		return id;
	}
	public String checkUser(BigDecimal userid, long surveyid) {
		
		
		UserMaster user = null;
		try
		{
		  user = (UserMaster) userMasterService.fetch(userid);
		}catch(IndexOutOfBoundsException e)
		{
			return "wronguser";
		}
		if(!user.equals(null))
		{
			List<SurveyResponse> existingResponse = surveyResponseService.fetchUser(BigDecimal.valueOf(surveyid));
			int flag=0;
			for(SurveyResponse e : existingResponse)
			{
				if(e.getEmailId().toString().toLowerCase().equalsIgnoreCase(user.getEmail().toString()))
				{
					flag++;
				}
				
			}
			if(flag==0)
			{
				return "true";
			}
			else{
				return "false";
			}
		}
		else
		{
			return "wronguser";
		}
		
	}
	
	
	






	
	
}

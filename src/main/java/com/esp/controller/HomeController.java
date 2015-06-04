package com.esp.controller;

import com.esp.entity.AnswerMaster;
import com.esp.entity.AnswerTypeMaster;
import com.esp.entity.QuestionAnswerMapping;
import com.esp.entity.SurveyMaster;
import com.esp.entity.SurveyQuestionMapping;
import com.esp.entity.SurveyResponse;
import com.esp.entity.SurveyTypeMaster;
import com.esp.entity.UserList;
import com.esp.entity.UserListMapping;
import com.esp.entity.UserMaster;
import com.esp.handler.HomeHandler;

import com.esp.mail.Mail;
//import com.esp.excelUser.*;
import com.esp.service.*;
import com.esp.dto.ElementDTO;
import com.esp.dto.FixedSurveyAnswersDTO;
import com.esp.dto.FixedSurveyQuestionsDTO;

import com.esp.dto.QuestionUIDTO;
import com.esp.dto.SurveyAnswerDTO;
import com.esp.dto.UserDetailsDTO;
import com.esp.dto.UserListDTO;
import com.esp.dto.mailDetailDTO;
import com.esp.dto.uploadFileDTO;

import com.esp.dto.SurveyDetailsDTO;
import com.esp.dto.SurveyDTO;
import com.esp.dto.SurveyQuestionDTO;
import com.esp.dto.SurveyResponseDTO;
import com.esp.dto.SurveyUIDTO;
import com.esp.userExcel.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    @Qualifier("SurveyQuestionMappingService")
    private GenericService surveyQuestionMappingService;
    @Autowired
    @Qualifier("SurveyResponseService")
    private GenericService surveyResponseService;
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
    @Qualifier("QuestionAnswerMappingService")
    private GenericService questionAnswerMappingService;
    
    @Autowired
    private HomeHandler handler;
    
    
 
    //for testing
    Integer userId = 101;
    private Logger log = Logger.getLogger(this.getClass().getName());

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date todayDate = new Date();
    
    @RequestMapping(value = "/home")
    public ModelAndView Home(Principal principal) throws IOException {

        log.info("logged in user :" + principal.getName()); // Principal class is related to spring Security 
        return new ModelAndView("home");

    }

    @RequestMapping(value = "")
    public ModelAndView HomePage(Principal principal) throws IOException {

        log.info("logged in user :" + principal.getName());
        return new ModelAndView("home");

    }
    @RequestMapping(value = "/s/home")
    public ModelAndView HomepageUser() throws IOException {
    	log.info(" \n User UI ");
    	return new ModelAndView("index");

    }
    
    @RequestMapping(value="/download/survey{uri}", method=RequestMethod.GET)
    public ModelAndView generatePDF(HttpServletRequest req, HttpServletResponse res,  ModelMap model ,@PathVariable(value = "uri") String uri) throws Exception
    {
    	SurveyUIDTO surveyDto;
    	surveyDto = handler.fetchSurveyDetails(Integer.parseInt(uri));
    	model.addAttribute("survey", surveyDto);
    	log.info("PDF..............");
    	return new ModelAndView("pdfView");		 
      }
       
    @RequestMapping(value = "/createSurvey")
    public ModelAndView createSurvey(ModelMap model) throws Exception {
        log.info("Request for Creating Survey");
        List<ElementDTO> surveyTypes;
        surveyTypes = handler.fetchSurveyType(); // calls the function fetchSurveyType from handler class 
        model.addAttribute("surveyTypes", surveyTypes);
        return new ModelAndView("createSurvey");
    }
    
    @RequestMapping(value = "/createUserList")
    public ModelAndView createUserList(ModelMap model) throws Exception {
        log.info("Request for Creating User List.... ");

        return new ModelAndView("createUserList");
    }
    
    
    @RequestMapping(value="/viewSurvey")
    public ModelAndView viewSurvey(ModelMap model,HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	log.info("Request for viewing Surveys by admin");
    	
    	List<SurveyDetailsDTO> surveyDetails;
    	
    	surveyDetails = handler.fetchAllSurveyDetails();
    	
    	
    	log.info("Survey Details Fetched");
    	
    	String path	= req.getContextPath();
    	model.addAttribute("path", path);
    	
    	model.addAttribute("surveyDet",surveyDetails);
    	    	   	
    	   	    	
    	return new ModelAndView("viewSurveyAdmin");
    	
    	
    }
  
    @RequestMapping(value = "/submitSurveyMaster") // called during submitting the Create_survey_page.... 
    public ModelAndView submitSurvey(ModelMap model, @ModelAttribute("surveyDetailsDTO") SurveyDetailsDTO surveyDetailsDTO,HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {
            log.info("Going to save Survey Master Details -");
            log.info("submitting survey to database");
            
            log.info("name :" + surveyDetailsDTO.getSurveyname());
            log.info("desc :" + surveyDetailsDTO.getDescription());
            log.info("type ;" + surveyDetailsDTO.getType());
            
           
            UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
            SurveyTypeMaster surveytypemaster = (SurveyTypeMaster) surveyTypeMasterService.fetch(BigDecimal.valueOf(surveyDetailsDTO.getType()));
            log.info("----!!"+surveytypemaster);
            SurveyMaster surveymaster = handler.mapToSurveymaster(usermaster, surveytypemaster, surveyDetailsDTO);
            surveyMasterService.add(surveymaster);
            log.info("Survey Saved to database");

            log.info("getting answertype master list");

            List<AnswerTypeMaster> answermasters = answerTypeMasterService.fetchAll(); // getting answer types from data

            log.info("Answertype master list fetched");

            SurveyDTO surveyDTO = new SurveyDTO();
            surveyDTO.setSurveyId(Integer.parseInt(surveymaster.getId().toString()));
            surveyDTO.setSurveyName(surveymaster.getSurveyName());
         
            
            log.info("SurveyVO is set");
            String path	= req.getServerName()+":"+req.getServerPort()+""+req.getContextPath();
            model.addAttribute("path", path);

            model.addAttribute("surveyDTO", surveyDTO);
            model.addAttribute("answerTypeMaster", answermasters);

            if (surveyDetailsDTO.getType() == 1) {

                return new ModelAndView("answerType");

            } else if (surveyDetailsDTO.getType() == 2) {

                return new ModelAndView("addQuestionAnswer");
            }

        } catch (ParseException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        
        return null;
    }
    @RequestMapping(value = "/surveyMaster") 
    public ModelAndView continueSurveyUpdate(ModelMap model, @ModelAttribute("surveyDetailsDTO") SurveyDetailsDTO surveyDetailsDTO,HttpServletRequest req, HttpServletResponse res) throws IOException {
    	 try {
             log.info("Going to save Survey Master Details -");
             log.info("submitting survey to database");
             
             log.info("name :" + surveyDetailsDTO.getSurveyname());
             log.info("desc :" + surveyDetailsDTO.getDescription());
             log.info("type ;" + surveyDetailsDTO.getType());
             
             UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
             SurveyTypeMaster surveytypemaster = (SurveyTypeMaster) surveyTypeMasterService.fetch(BigDecimal.valueOf(surveyDetailsDTO.getType()));
             
             log.info("getting answertype master list");

             List<AnswerTypeMaster> answermasters = answerTypeMasterService.fetchAll(); // getting answer types from data

             log.info("Answertype master list fetched");

             SurveyDTO surveyDTO = new SurveyDTO();
             surveyDTO.setSurveyId(decToInt(surveyDetailsDTO.getSurveyId()));
             surveyDTO.setSurveyName(surveyDetailsDTO.getSurveyname());
                       
             
             log.info("SurveyVO is set");
             String path	= ""+req.getContextPath();
             model.addAttribute("path", path);

           
            

             if (surveyDetailsDTO.getType() == 1) {
            
            	 List<SurveyQuestionMapping> surveyQues = surveyQuestionMappingService.fetchByParam(surveyDetailsDTO.getSurveyId());
            	 log.info("---check------------"+surveyQues);
            	 if(surveyQues.isEmpty())
            	 { 
            		    model.addAttribute("surveyDTO", surveyDTO);
                        model.addAttribute("answerTypeMaster", answermasters);

            		 return new ModelAndView("answerType");
            		 
            	 }
            	 else
            	 {
            		 List<ElementDTO> anslist = new ArrayList<ElementDTO>();
            		 QuestionAnswerMapping quesAnsmap = null;
            		 for(SurveyQuestionMapping surveyques: surveyQues)
            		 {
            			 log.info("---check"+surveyques.getId());
            			  quesAnsmap = (QuestionAnswerMapping) questionAnswerMappingService.fetch(surveyques.getQuestionAnsId().getId());
            			 break;
            		 }
            		 log.info("---------"+quesAnsmap.getAnswerId());
            		AnswerMaster ansmaster = (AnswerMaster) answerService.fetch(quesAnsmap.getAnswerId().getId());
       	            anslist = handler.answerMasterToList(ansmaster);
       	            
       	            surveyDTO.setAnsId(decToInt(quesAnsmap.getAnswerId().getId()));
       	            surveyDTO.setAnsTypeID(decToInt(quesAnsmap.getAnsTypeId().getId()));
       	                   	            
       	            model.addAttribute("answers", anslist);
            		model.addAttribute("surveyDTO", surveyDTO);
       	            return new ModelAndView("addQuestions");
            		 
            	 }

             } else if (surveyDetailsDTO.getType() == 2) {
            	   model.addAttribute("surveyDTO", surveyDTO);
            	   model.addAttribute("answerTypeMaster", answermasters);
                 return new ModelAndView("addQuestionAnswer");
             }

         } catch (Exception ex) {
             log.error(ex.getMessage());
             ex.printStackTrace();
         }
         
         return null;
    }

    	
    @RequestMapping(value="/surveyMaster{uri}")
    public ModelAndView updateSurvey(ModelMap model,@PathVariable(value = "uri") String uri, @ModelAttribute("surveyDetailsDTO") SurveyDetailsDTO surveyDetailsDTO,HttpServletRequest req, HttpServletResponse res) throws IOException,ParseException{
    	
    		if(("updating").equals(req.getParameter("updateStatus")))
    		{
    		    log.info("-----"+req.getParameter("updateStatus"));
    			log.info("Going to update Survey Master Details -");
	             log.info("submitting survey to database");
	             
	             log.info("name :" + surveyDetailsDTO.getSurveyname());
	             log.info("desc :" + surveyDetailsDTO.getDescription());
	             log.info("Id :" + surveyDetailsDTO.getSurveyId());
	             
	           
	             SurveyUIDTO surveyDTO = handler.fetchSurveyDetails(decToInt(surveyDetailsDTO.getSurveyId()));
	             
	             SurveyMaster surveymaster = handler.mapToUpdateSurveymaster(surveyDetailsDTO,surveyDTO);
	             
	             surveyMasterService.update(surveymaster);
	             
    		}
    		else
    		{
    			 log.info("name :" + surveyDetailsDTO.getSurveyname());
  	             log.info("desc :" + surveyDetailsDTO.getDescription());
  	             log.info("Id :" + surveyDetailsDTO.getSurveyId());
  	             
  	             log.info("-----"+req.getParameter("updateStatus"));
	             
    		}
             SurveyUIDTO userSurveyDTO = handler.fetchSurveyDetails(decToInt(surveyDetailsDTO.getSurveyId()));
             
             String path	= req.getServerName()+":"+req.getServerPort()+""+req.getContextPath();
             model.addAttribute("path", path);
          
             
             log.info("Survey Updated");
           
         	model.addAttribute("survey",userSurveyDTO);
             
             return new ModelAndView("updateSurvey");

    }

    @RequestMapping(value = "/addQuestions")
    public ModelAndView addQuestions(ModelMap model, @ModelAttribute("surveyDTO") SurveyDTO surveyDTO,
            @ModelAttribute("answerDetailsDTO") FixedSurveyAnswersDTO answerDetailsDTO,HttpServletRequest req, HttpServletResponse res) throws IOException {

        try {
        	List<ElementDTO> anslist = new ArrayList<ElementDTO>();
        	if(answerDetailsDTO.getAnsTypeId() != 3)
        	{
        	
	            log.info("Going to save Answer Master details -");
	
	            log.info("Going answerDetailsDTO.getAnsType()-" + answerDetailsDTO.getAnsTextList());
	            log.info("Going answerDetailsDTO.getAnsType()-other info status" + answerDetailsDTO.getOther());
	
	            AnswerMaster answerMaster = handler.mapToAnswerMaster(answerDetailsDTO);
	
	
	            surveyDTO.setAnsId(Integer.parseInt(answerMaster.getId().toString()));
	            log.info("survey name " + surveyDTO.getSurveyName());
	            log.info("survey id " + surveyDTO.getSurveyId());
	            log.info("survey ans id " + surveyDTO.getAnsId());
	            log.info("survey ans type id " + surveyDTO.getAnsTypeID());
	            log.info("survey answerDetailsDTO type id " + answerDetailsDTO.getAnsTypeId());
	            surveyDTO.setAnsTypeID(answerDetailsDTO.getAnsTypeId());
	            
	            log.info("survey ans type id" + surveyDTO.getAnsTypeID());
	            
	            AnswerMaster ansmaster = (AnswerMaster) answerService.fetch(BigDecimal.valueOf(surveyDTO.getAnsId()));
	            anslist = handler.answerMasterToList(ansmaster);
	            model.addAttribute("answers", anslist);
        	}
        	   
	        log.info("survey answerDetailsDTO type id " + answerDetailsDTO.getAnsTypeId());
        	surveyDTO.setAnsTypeID(answerDetailsDTO.getAnsTypeId());
        	log.info("survey ans type id " + surveyDTO.getAnsTypeID());
        	
        	
        	model.addAttribute("surveyDTO", surveyDTO);
            return new ModelAndView("addQuestions");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
   

    @RequestMapping(value = "saveQuestions", method = RequestMethod.POST)
    public ModelAndView saveQuestions(ModelMap model,HttpServletRequest req, HttpServletResponse res ,@ModelAttribute("questionDTO") FixedSurveyQuestionsDTO questionDTO, @ModelAttribute("surveyDTO") SurveyDTO surveyDTO, @ModelAttribute("surveyDetailsDTO") SurveyDetailsDTO surveyDetailsDTO )throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        log.info("--->survey id :\t" + surveyDTO.getSurveyId());
        log.info("--->survey ans id:\t" + surveyDTO.getAnsId());
        log.info("--->survey ansType id:\t" + surveyDTO.getAnsTypeID());
        log.info("--->survey question list size : \t" + questionDTO.getQuestionText().size());
        log.info("question :----" + questionDTO.getQuestionText());
        for (String question : questionDTO.getQuestionText()) {
            log.info("question :" + question);
        }
        for(String mandatory : questionDTO.getMandatory()){
        	log.info("mandatory :------------"+mandatory);
        }

        UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
        
       // SurveyMaster surveymaster = (SurveyMaster) surveyMasterService.fetch(BigDecimal.valueOf(surveyDTO.getSurveyId()));

        SurveyQuestionMapping surveyQuestionMapping = handler.mapToQuestionMaster(questionDTO, usermaster, surveyDTO);

        log.info("Survey question answers saved in - " + surveyQuestionMapping.getId());
        model.addAttribute("survey", surveyDTO);
        model.addAttribute("surveyDetails",surveyDetailsDTO);
        long code = handler.uniqueEncode(BigDecimal.valueOf(0), BigDecimal.valueOf(surveyDTO.getSurveyId()));
        String path	= req.getServerName()+":"+req.getServerPort()+""+req.getContextPath()+"/s/survey"+surveyDTO.getSurveyId()+"&u"+code;
        model.addAttribute("path", path);
      
        modelAndView.setViewName("thanksAdmin");
        return modelAndView;
    }
    
  

    @RequestMapping(value = "saveQuestionAns", method = RequestMethod.POST)
    public ModelAndView submitSurveyResponse1(ModelMap model, @ModelAttribute("surveyResponseDTO") SurveyResponseDTO surveyResponseDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        System.out.println("-->>>>> surveyResponseDTO" + surveyResponseDTO);
        if (surveyResponseDTO != null) {

            System.out.println("-->>>>> surveyResponseDTO Survey Questions" + surveyResponseDTO.getSurveyQuestions());

            for (SurveyQuestionDTO q : surveyResponseDTO.getSurveyQuestions()) {
                System.out.println("-->>>>> q.getQuestionText()" + q.getQuestionText() + " -- " + q.getAnsTypeId());
                /*if (q.get getAnswers()!=null){
                System.out.println("-->>>>> q.getAnswers()"+q.getAnswers());
                for (ElementDTO	a:q.getAnswers()){
                System.out.println("-->>>>> a. ans text ()"+a.getText());

                }
                }*/

                /*if (q.getAnsIdList() !=null){
                System.out.println("-->>>>> q.getAnsIdList()"+q.getAnsIdList());
                for (AnswerDTO	a:q.getAnswers()){
                System.out.println("-->>>>> ans text "+a.getAnsText());

                }
                }*/

            }

        }

        //model.addAttribute("survey",surveyDTO);
        UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
        SurveyQuestionMapping surveyQuestionMapping = handler.saveQuestionAnswers(surveyResponseDTO, usermaster);

        //log.info("Survey question answers saved in - " + surveyQuestionMapping.getId());

        SurveyDTO surveyDTO = new SurveyDTO();
        surveyDTO.setSurveyId(surveyResponseDTO.getSurveyId());
        surveyDTO.setSurveyName(surveyResponseDTO.getSurveyName());
        String path	= request.getServerName()+":"+request.getServerPort()+""+request.getContextPath();
        
        model.addAttribute("path", path);
        model.addAttribute("survey", surveyDTO);
        modelAndView.setViewName("thanksAdmin");
        return modelAndView;

    }

    /**
     * This method is used to add two integers. This is
     * a the simplest form of a class method, just to
     * show the usage of various javadoc Tags.
     * @param numA This is the first parameter to addNum method
     * @param numB  This is the second parameter to addNum method
     * @return int This returns sum of numA and numB.
     */
    @RequestMapping(value = "/s/survey{uri}&u{ure}", method = RequestMethod.GET)
    public ModelAndView getPublishedSurvey(ModelMap model, @PathVariable(value = "uri") String uri,@PathVariable(value = "ure") String ure) throws IOException {

        
        log.info("String here in getPublishedSurvey " + uri);
        log.info("String here in getUser " + ure);
        long userId = handler.uniqueDecode(Integer.parseInt(ure), Integer.parseInt(uri));
        log.info("User Id " + userId);
        SurveyUIDTO userSurveyDTO = new SurveyUIDTO();
        try
        {
        	 userSurveyDTO = handler.fetchSurveyDetails(Integer.parseInt(uri));
        }
        catch(IndexOutOfBoundsException e)
		{
        	String surveyRes = "<h4>This Survey has been Deleted</h4>";
        	model.addAttribute("surveyRes", surveyRes);
		      return new ModelAndView("thanksUser");
		}
        
        if(userId!=0)
	    {
	        String publishStatus = userSurveyDTO.getPublished().toString();
	        if(publishStatus.equalsIgnoreCase("active"))
	        {
	        	System.out.println("userfetch---------------------------------------------start");
		        String validUser = handler.checkUser(BigDecimal.valueOf(userId),Integer.parseInt(uri));
		        System.out.println("userfetch---------------------------------------------start" + validUser);
		        
		        if(validUser.equals("true"))
		        {
		        	UserMaster user = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
			        
			        log.info("userSurveyDTO " + userSurveyDTO.getSurveyId() + userSurveyDTO.getSurveyName());
			        UserDetailsDTO userdetails = new UserDetailsDTO();
			        userdetails.setFirstName(user.getFirstName().toString());
			        userdetails.setEmail(user.getEmail().toString());
			        
			        model.addAttribute("user", userdetails);
			        model.addAttribute("survey", userSurveyDTO);
			        return new ModelAndView("viewSurvey");
			     }
		        else if(validUser.equals("false"))
		        {
		        	String surveyRes = "You have already submitted your response for this survey";
		            model.addAttribute("surveyRes", surveyRes);
			        return new ModelAndView("thanksUser");
		        }
		        else
		        {
		        	String surveyRes = "You are not the Authorised Person to attempt this survey";
		        	 model.addAttribute("surveyRes", surveyRes);
				      return new ModelAndView("thanksUser");
		        }
	        }
	        else if(publishStatus.equalsIgnoreCase("pending"))
	        {
	        	String surveyRes = " This Survey has not been Started yet "+"<br>"+"Survey Start date is "+userSurveyDTO.getSurveystart().toString();
	        	 model.addAttribute("surveyRes", surveyRes);
			        return new ModelAndView("thanksUser");
	        }
	        else
	        {
	        	String surveyRes = " This Survey has been closed since "+userSurveyDTO.getSurveystart().toString();
	        	 model.addAttribute("surveyRes", surveyRes);
			        return new ModelAndView("thanksUser");
	        }
	    }
	    else
	    {	
	    	 String publishStatus = userSurveyDTO.getPublished().toString();
		        if(publishStatus.equalsIgnoreCase("active"))
		        {
		        	log.info("userSurveyDTO " + userSurveyDTO.getSurveyId() + userSurveyDTO.getSurveyName());
		        	model.addAttribute("survey", userSurveyDTO);
		        	return new ModelAndView("viewSurvey");
		        }
		        else if(publishStatus.equalsIgnoreCase("pending"))
		        {
		        	String surveyRes = " This Survey has not been Started yet "+"<br>"+"Survey Start date is "+userSurveyDTO.getSurveystart().toString();
		        	 model.addAttribute("surveyRes", surveyRes);
				        return new ModelAndView("thanksUser");
		        }
		        else
		        {
		        	String surveyRes = " This Survey has been closed since "+userSurveyDTO.getSurveystart().toString();
		        	 model.addAttribute("surveyRes", surveyRes);
				        return new ModelAndView("thanksUser");
		        }
		        
	    }
    }
    
    @RequestMapping(value = "/v/survey{uri}", method = RequestMethod.GET)
    public ModelAndView viewSurveyRecord(ModelMap model, @PathVariable(value = "uri") String uri, @ModelAttribute("surveyAnswerDTO") SurveyAnswerDTO surveyAnswerDTO , HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
    	
    	log.info("\n \n fetch Records of the Survey "+uri);
    	
    	    	
    	SurveyDetailsDTO surveyDTO = handler.fetchSurveyDetail(Integer.parseInt(uri));
    	
    	SurveyUIDTO userSurveyDTO = handler.fetchSurveyDetails(Integer.parseInt(uri));
    	
    	String path	= request.getContextPath();
    	model.addAttribute("path", path);
    	    	    	   
    	model.addAttribute("survey", surveyDTO);
    	model.addAttribute("userSurvey", userSurveyDTO);
    	
    	    	
    	 return new ModelAndView("surveyRecord");
    	
    }
    
   @RequestMapping(value = "/d/survey{uri}", method = RequestMethod.GET)
    public ModelAndView deleteSurveyRecord(ModelMap model, @PathVariable(value = "uri") String uri) throws IOException {
    	
    	log.info("\n \n \ndelete Records of  Survey "+uri);
    	System.out.println("  Survey  deletion start");
    	boolean surveyDel = handler.deleteSurveyDetail(Integer.parseInt(uri));
    	
    	String result = "surveyDeleted";
    	
    	if(surveyDel == true)
    	{
    		model.addAttribute("result", result);
    		return new ModelAndView("delete");
    		
    	}
    	else
    	{
    		return new ModelAndView("home");
    	}
    	
    	
    	
    	
    	 
    	
    }
    
    @RequestMapping(value="/e/survey{uri}")
    public ModelAndView editSurvey(ModelMap model, @PathVariable(value = "uri") String uri) throws IOException,ParseException {
    	
    	SurveyDetailsDTO surveyDTO = handler.fetchSurveyDetail(Integer.parseInt(uri));
    	
    	model.addAttribute("survey",surveyDTO);
    	
    	return new ModelAndView("editSurvey");
    	
    	
    	
    }
    @RequestMapping(value="/e/question{uri}")
    public void editQuestion(ModelMap model, @PathVariable(value = "uri") String uri, @ModelAttribute("QuestionUIDTO") QuestionUIDTO question,@ModelAttribute("SurveyDetailsDTO") SurveyDetailsDTO survey) throws IOException,ParseException {
    	
    	System.out.println("-----"+question.getQuestionText());
    	System.out.println("-----"+question.getMandatory());
    	handler.updateMandatoryStatus(question,survey,Integer.parseInt(uri));
    		
    	handler.editQuestionText(question,Integer.parseInt(uri),survey);
    	
    		
    	
    	
    	
    }
    
    @RequestMapping(value="/d/question{uri}")
    public ModelAndView deleteQuestion(ModelMap model,@PathVariable(value = "uri") String uri, @ModelAttribute("SurveyDetailsDTO") SurveyDetailsDTO surveyDetails,HttpServletRequest request, HttpServletResponse response){
     
    	System.out.println("-------------------------"+	surveyDetails.getSurveyId()+"=== "+surveyDetails.getSurveyname());
    	boolean del = handler.deleteQuestion(Integer.parseInt(uri),surveyDetails);
    	SurveyUIDTO survey = new SurveyUIDTO();
    	
    	survey.setSurveyId(decToInt(surveyDetails.getSurveyId()));
    	survey.setSurveyName(surveyDetails.getSurveyname());
    	survey.setSurveyDesc(surveyDetails.getDescription());
    	survey.setType(surveyDetails.getType());
   
    	if(del == true)
    	{
    		String result = "questionDeleted";
    		model.addAttribute("result",result);
    		model.addAttribute("survey",survey);
    	return new ModelAndView("delete");
    	}
    	else{
    		return new ModelAndView("iio");
    	}
    }
    
    @RequestMapping(value = "/s/submitSurveyResponse", method = RequestMethod.POST)
    public ModelAndView submitSurveyResponse(ModelMap model, @ModelAttribute("surveyResponseDTO") SurveyResponseDTO surveyResponseDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        String email = request.getParameter("surveyEmail");
      
        System.out.println("-->>>>> surveyResponseDTO" + surveyResponseDTO);
        if (surveyResponseDTO != null) {

            System.out.println("-->>>>> surveyResponseDTO Survey Questions" + surveyResponseDTO.getSurveyQuestions());

            for (SurveyQuestionDTO q : surveyResponseDTO.getSurveyQuestions()) {
                System.out.println("-->>>>> q.getQuestionId()" + q.getQuestionId());
                /*if (q.getAnswers()!=null){
                System.out.println("-->>>>> q.getAnswers()"+q.getAnswers());
                for (AnswerDTO	a:q.getAnswers()){
                System.out.println("-->>>>> a.getAnsId()"+a.getAnsId());

                }
                }*/

                if (q.getAnsTextList() != null) {
                    System.out.println("-->>>>> q.getAnsTextList()" + q.getAnsTextList());
                    for (String a : q.getAnsTextList()) {
                        System.out.println("-->>>>> ans text " + a);

                    }
                }

            }

        }
        UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
        List<SurveyResponse> existingResponse = surveyResponseService.fetchUser(BigDecimal.valueOf(surveyResponseDTO.getSurveyId()));
		int flag=0;
			for(SurveyResponse e : existingResponse)
			{
				if(e.getEmailId().toString().toLowerCase().equalsIgnoreCase(email.toString()))
				{
					flag++;
				}
				
			}
		
			if(flag == 0)
			{
				handler.submitSurveyResponse(surveyResponseDTO, usermaster, request.getRemoteAddr(), email);
				 model.addAttribute("surveyRes", "Thanks for completing this survey.");
				 model.addAttribute("survey", surveyResponseDTO.getSurveyName());
			}
			else
			{
				 model.addAttribute("surveyRes", "You have already submitted your response for this Survey");
			}
        /*System.out.println("----Addr: "+request.getRemoteAddr()+"----Host: " +request.getRemoteHost()+"----Port: " + request.getRemotePort()+"----User: " +request.getRemoteUser());
        //UserSurveyUrlMapping surveyUrlMapping = userSurveyUrlService.getUserSurveyURL(uri); 
        

        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        System.out.println("==1=>"+ip);
        ip = request.getHeader("Proxy-Client-IP");
        System.out.println("==2=>"+ip);
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getHeader("WL-Proxy-Client-IP");
        System.out.println("==3=>"+ip);
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
        ip = request.getRemoteAddr();
        System.out.println("==4=>"+ip);
        }*/









        //log.info("userSurveyDTO "+userSurveyDTO.getSurveyId() + userSurveyDTO.getSurveyName());
       
        return new ModelAndView("thanksUser");
    }
	
    @RequestMapping(value="/userList", method = RequestMethod.POST)
    public ModelAndView uploadFile(ModelMap model,@ModelAttribute("uploadFile") uploadFileDTO uploadFile, HttpServletRequest request, HttpServletResponse response )throws Exception
    {
    	String saveDirectory = "D://eSurveyPlanner//src//main//webapp//resources//upload//";
    	List<UserDetailsDTO> userDetails = null;
    	MultipartFile file = uploadFile.getFile(); 
    	
    	System.out.println("file name is:- \t "+uploadFile.getFile().getOriginalFilename());
    	String userList = uploadFile.getUserListName();
    	System.out.println("User List name is:- \t "+uploadFile.getUserListName());
    	System.out.println("User List Description is:- \t "+uploadFile.getDescription());
    	 UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
    	  
    	 UserList userlist = null;
    	
    	String filename = "";
    	if (null != file && file.getSize() > 0) {
    		
    		   filename = file.getOriginalFilename();
    		
    		  if (!"".equalsIgnoreCase(filename)) {
                  
    			  File dest = new File(saveDirectory + userList + ".xls");
    			  
    			  file.transferTo(dest);
    			  System.out.println("-------------------"+dest.getPath());
    			  
    			  userlist = handler.maptoUserList(uploadFile,usermaster);
    			  userListService.add(userlist);
    			  log.info("user ----------------- :-"+userlist);
    			  log.info("--------User list Saved -------------");
    			  
    			  
    			  userDetails = userExcel.mapUserDataToList(dest.getPath().toString());
    			  System.out.println("-------------------"+userDetails.size());
    			  dest.delete();
    		  	}
    	  	}
    
    	  userDetails.remove(0);
    	    
    	  for(UserDetailsDTO user : userDetails)
    	  {
    		  int i=0;
    		  UserMaster userMaster = handler.mapToUsermaster(usermaster,user);
    		  log.info("user ----------------- :-"+userMaster);
    		  log.info("user saved :-"+i);
    		  
    		  UserListMapping userMap = handler.mapToUserListMapping(userlist,userMaster);
    		  log.info("user saved ------------------------------------:-"+i+"------"+userMap.getUserId()+" "+userMap.getUserListId());
    		  userListMappingService.add(userMap);
    		
    		  i++;
    		  
    	  }
    	  log.info("saved-------");
    	  
    	model.addAttribute("file", uploadFile);
    	model.addAttribute("userDetails", userDetails);
    	
    	return new ModelAndView("excelUpload");
    }
    
    @RequestMapping(value="/viewUserLists", method = RequestMethod.GET)
    public ModelAndView fetchUserLists(ModelMap model,HttpServletRequest req, HttpServletResponse res)throws Exception
    {
    	log.info("Request for Viewing all user lists-----------");
    	
    	List<UserListDTO> userlist;
    	
    	userlist = handler.fetchAlluserlists();

    	String path	= req.getContextPath();
    	model.addAttribute("path", path);
    	
    	
    	model.addAttribute("details", userlist);
    	return new ModelAndView("viewUserList");
    }
    
    @RequestMapping(value = "/d/userList{uri}", method = RequestMethod.GET)
    public ModelAndView deleteUserList(ModelMap model, @PathVariable(value = "uri") String uri) throws IOException {
    	
    	log.info("\n \n \ndelete Records of  Users "+uri);
    	System.out.println("  UserList deletion start");
    	
    	boolean userlistDel = handler.deleteUserList(Integer.parseInt(uri));
    		
    	String result = "userListDeleted";
    	
	    	if(userlistDel == true )
	    	{
	    		System.out.println("  deleted User Details..... "+Integer.parseInt(uri));
	
	    		model.addAttribute("result", result);
	    		return new ModelAndView("delete");
	    		
	    	}
	    	else
	    	{
	    		return new ModelAndView("home");
	    	}
    
    }
    @RequestMapping(value = "/send_survey{uri}", method = RequestMethod.GET)
    public ModelAndView mailUI(ModelMap model,@PathVariable(value = "uri") String uri,HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	log.info("--- Mail UI ----");
    	
    	List<UserListDTO> userlist	= handler.fetchAlluserlists();
    	SurveyDetailsDTO SurveyDTO = handler.fetchSurveyDetail(Integer.parseInt(uri));
    	String path	= req.getServerName()+":"+req.getServerPort()+""+req.getContextPath();
    	
    	
    	model.addAttribute("survey", SurveyDTO);
    	model.addAttribute("user", userlist);
    	model.addAttribute("path", path);
    	return new ModelAndView("sendmail");
    	
	    	
    
    }
    
    
    @RequestMapping(value = "/mailSent", method = RequestMethod.POST)
    public ModelAndView mailSending(ModelMap model,  @ModelAttribute("mailDetailDTO") mailDetailDTO mailDetails,HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	log.info("--- Mail Sending ----");
    		
    		String sender = mailDetails.getSender();
    		String password = mailDetails.getPassword();
    		BigDecimal recieveId = mailDetails.getRListId();
    		String message = mailDetails.getMessage();
    		String subject = mailDetails.getSubject(); 
    		String link = mailDetails.getSurveylink();
    		BigDecimal surveyId = mailDetails.getSurveyId();
    		
	    		System.out.println("Sender ------"+sender);
	    		System.out.println("Sender ------"+password);
	    		System.out.println("Reciever list------"+recieveId);
	    		System.out.println("Message ------"+message);
	    		System.out.println(" subject	------"+subject);
	    		System.out.println(" link ------"+link);
	    		System.out.println("survey id-------"+surveyId);
    	
	    boolean mailsendStatus = false;
	    System.out.println("startup----------------"+mailsendStatus);
    	
    	 mailsendStatus = handler.doSendMail(sender,password, recieveId , message , subject , link, surveyId);
    	 
    	 
    	if(mailsendStatus = true)
    	{
    		return new ModelAndView("mailSuccess");
    	}
    	else
    	{
    		return new ModelAndView("blank");
    	}
	    	
    
    }
    @RequestMapping(value="/publish{uri}")
    public ModelAndView publish(ModelMap model,@PathVariable(value = "uri") String uri,HttpServletRequest req, HttpServletResponse res) throws IOException,ParseException {
    	
    	System.out.println("------------"+uri);
    	long code = handler.uniqueEncode(BigDecimal.valueOf(0), BigDecimal.valueOf(Integer.parseInt(uri)));
   	 String path	= req.getServerName()+":"+req.getServerPort()+""+req.getContextPath()+"/s/survey"+BigDecimal.valueOf(Integer.parseInt(uri))+"&u"+code;
   	 
   	 SurveyUIDTO surveydto = handler.fetchSurveyDetails(Integer.parseInt(uri));
   	
   	 model.addAttribute("survey", surveydto);
   	 
     model.addAttribute("path", path);
    	
    	return new ModelAndView("randomUrl");
    	
    	
    	
    }
   
   
    
    private int decToInt(BigDecimal dec){
			
			return Integer.parseInt(dec.toString());
			
		}
	}

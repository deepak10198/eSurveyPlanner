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

        log.info("logged in user/admin :" + principal.getName()); 
        return new ModelAndView("home");

    }

    @RequestMapping(value = "")
    public ModelAndView HomePage(Principal principal) throws IOException {

        log.info("logged in user/admin :" + principal.getName());
        return new ModelAndView("home");

    }
    @RequestMapping(value = "/s/home")
    public ModelAndView HomepageUser() throws IOException {
    	
    	log.info(" USER homepage ");
    	
    	return new ModelAndView("index");

    }
    
    @RequestMapping(value="/download/survey{uri}", method=RequestMethod.GET)	// Report Generation(PDF) 
    public ModelAndView generatePDF(HttpServletRequest req, HttpServletResponse res,  ModelMap model ,@PathVariable(value = "uri") String uri) throws Exception
    {
    	log.info("Report generation for survey : "+uri);
    	
    	SurveyUIDTO surveyDto;
    	surveyDto = handler.fetchSurveyDetails(Integer.parseInt(uri));
    	model.addAttribute("survey", surveyDto);
    	
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
        log.info("Request for Creating User List");

        return new ModelAndView("createUserList");
    }
    
    
    @RequestMapping(value="/viewSurvey")
    public ModelAndView viewSurvey(ModelMap model,HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	log.info("Request for viewing list of all Surveys ");
    	
    	List<SurveyDetailsDTO> surveyDetails;
    	
    	surveyDetails = handler.fetchAllSurveyDetails();
    	
    	  
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
            
            log.info("Survey "+surveyDetailsDTO.getSurveyname()+" ;\n type "+surveyDetailsDTO.getDescription()+"; \nDescription :" + surveyDetailsDTO.getDescription());
            
            
            
           
            UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
            SurveyTypeMaster surveytypemaster = (SurveyTypeMaster) surveyTypeMasterService.fetch(BigDecimal.valueOf(surveyDetailsDTO.getType()));
            log.info("----"+surveytypemaster);
            SurveyMaster surveymaster = handler.mapToSurveymaster(usermaster, surveytypemaster, surveyDetailsDTO);
            surveyMasterService.add(surveymaster);
            
            log.info("Survey Details Saved to database");

           

            List<AnswerTypeMaster> answermasters = answerTypeMasterService.fetchAll(); // getting answer types from data

    

            SurveyDTO surveyDTO = new SurveyDTO();
            surveyDTO.setSurveyId(Integer.parseInt(surveymaster.getId().toString()));
            surveyDTO.setSurveyName(surveymaster.getSurveyName());
         
            
            log.info("SurveyVO is set");
            
            String path	= req.getContextPath();
            model.addAttribute("path", path);

            model.addAttribute("surveyDTO", surveyDTO);
            model.addAttribute("answerTypeMaster", answermasters);

            if (surveyDetailsDTO.getType() == 1) {

               log.info(" \n Fixed type Survey creation start");
            	return new ModelAndView("answerType");

            } else if (surveyDetailsDTO.getType() == 2) {


                log.info(" \n Customized type Survey creation start");
                return new ModelAndView("addQuestionAnswer");
            }

        } catch (ParseException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        
        return null;
    }
    @RequestMapping(value = "/surveyMaster") // adding more Question and answer to the existing survey
    public ModelAndView continueSurveyUpdate(ModelMap model, @ModelAttribute("surveyDetailsDTO") SurveyDetailsDTO surveyDetailsDTO,HttpServletRequest req, HttpServletResponse res) throws IOException {
    	 try {
    		  log.info("Adding more Questions to Existing survey -");
    		  log.info("Fetching Survey Master Details for updation -");
             
             
             log.info("name :" + surveyDetailsDTO.getSurveyname());
             log.info("desc :" + surveyDetailsDTO.getDescription());
             log.info("type ;" + surveyDetailsDTO.getType());
             
             UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
             SurveyTypeMaster surveytypemaster = (SurveyTypeMaster) surveyTypeMasterService.fetch(BigDecimal.valueOf(surveyDetailsDTO.getType()));
             
            

             List<AnswerTypeMaster> answermasters = answerTypeMasterService.fetchAll(); // getting answer types from data


             SurveyDTO surveyDTO = new SurveyDTO();
             surveyDTO.setSurveyId(decToInt(surveyDetailsDTO.getSurveyId()));
             surveyDTO.setSurveyName(surveyDetailsDTO.getSurveyname());
                       
         
             String path	= ""+req.getContextPath();
             model.addAttribute("path", path);

           
            

             if (surveyDetailsDTO.getType() == 1) {
            
            	 List<SurveyQuestionMapping> surveyQues = surveyQuestionMappingService.fetchByParam(surveyDetailsDTO.getSurveyId());
            	 
            	 
            	 if(surveyQues.isEmpty())
            	 { 
            		    log.info("There is no Question and answer in the Survey(Fixed) :"+surveyDetailsDTO.getSurveyId());
            		    log.info("First adding answers to the Survey");
            		 	model.addAttribute("surveyDTO", surveyDTO);
                        model.addAttribute("answerTypeMaster", answermasters);

                        return new ModelAndView("answerType");
            		 
            	 }
            	 else
            	 {
            		 log.info("Adding more Question to the Survey(Fixed) :"+surveyDetailsDTO.getSurveyId());
         		    
            		 List<ElementDTO> anslist = new ArrayList<ElementDTO>();
            		 QuestionAnswerMapping quesAnsmap = null;
            		 for(SurveyQuestionMapping surveyques: surveyQues)
            		 {
            			 
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
            	 log.info("Adding more Question Answer to the Survey(Customized) :"+surveyDetailsDTO.getSurveyId());
     		    
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

    	
    @RequestMapping(value="/surveyMaster{uri}")	// updating Survey Master MetaData
    public ModelAndView updateSurvey(ModelMap model,@PathVariable(value = "uri") String uri, @ModelAttribute("surveyDetailsDTO") SurveyDetailsDTO surveyDetailsDTO,HttpServletRequest req, HttpServletResponse res) throws IOException,ParseException{
    	  log.info("Survey Metadata Updated -");
    	  
    	  log.info("update Status : "+req.getParameter("updateStatus"));	
    	  log.info("name :" + surveyDetailsDTO.getSurveyname());
    	  log.info("desc :" + surveyDetailsDTO.getDescription());
    	  log.info("Id :" + surveyDetailsDTO.getSurveyId());
         
         
    	if(("updating").equals(req.getParameter("updateStatus")))
    		{
    		    
    			log.info("Going to update Survey Master Details -");

	             SurveyUIDTO surveyDTO = handler.fetchSurveyDetails(decToInt(surveyDetailsDTO.getSurveyId()));
	             
	             SurveyMaster surveymaster = handler.mapToUpdateSurveymaster(surveyDetailsDTO,surveyDTO);
	             
	             surveyMasterService.update(surveymaster);
	             
    		}
    		
             SurveyUIDTO userSurveyDTO = handler.fetchSurveyDetails(decToInt(surveyDetailsDTO.getSurveyId()));
             
             String path	= ""+req.getContextPath();
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
        	log.info("mandatory status :-"+mandatory);
        }

        UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
        
        SurveyQuestionMapping surveyQuestionMapping = handler.mapToQuestionMaster(questionDTO, usermaster, surveyDTO);

        log.info("Survey question answers saved in - " + surveyQuestionMapping.getId());
        model.addAttribute("survey", surveyDTO);
        model.addAttribute("surveyDetails",surveyDetailsDTO);
        long code = handler.uniqueEncode(BigDecimal.valueOf(0), BigDecimal.valueOf(surveyDTO.getSurveyId()));
        String path	= req.getServerName()+":"+req.getServerPort()+""+req.getContextPath()+"/s/survey"+surveyDTO.getSurveyId()+"&u"+code;
        model.addAttribute("path", path);
        log.info("Survey Saved");
        modelAndView.setViewName("thanksAdmin");
        return modelAndView;
    }
    
  

    @RequestMapping(value = "saveQuestionAns", method = RequestMethod.POST)
    public ModelAndView submitSurveyResponse1(ModelMap model, @ModelAttribute("surveyResponseDTO") SurveyResponseDTO surveyResponseDTO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
       log.info("Saving Question Answers");
        if (surveyResponseDTO != null) {

          
            for (SurveyQuestionDTO q : surveyResponseDTO.getSurveyQuestions()) {
                log.info("Question Text -------" + q.getQuestionText() + " -- " + q.getAnsTypeId());
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
        long code = handler.uniqueEncode(BigDecimal.valueOf(0), BigDecimal.valueOf(surveyDTO.getSurveyId()));
        String path	= request.getServerName()+":"+request.getServerPort()+""+request.getContextPath()+"/s/survey"+surveyDTO.getSurveyId()+"&u"+code;
        log.info("Survey Saved");
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
    @RequestMapping(value = "/s/survey{uri}&u{ure}", method = RequestMethod.GET) // Survey Page for Users
    public ModelAndView getPublishedSurvey(ModelMap model, @PathVariable(value = "uri") String uri,@PathVariable(value = "ure") String ure) throws IOException {

    	log.info("Request for viewing Survey Page (User) -");
        log.info("Survey: " + uri);
        log.info("unique code :" + ure);
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
        	log.info("Survey Deleted");
        	model.addAttribute("surveyRes", surveyRes);
		      return new ModelAndView("thanksUser");
		}
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
	        	
		        String validUser = handler.checkUser(BigDecimal.valueOf(userId),Integer.parseInt(uri));
		        log.info("Survey is Active and user is "+validUser);
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
	        	log.info("Survey is Pending");
		        
	        	String surveyRes = " This Survey has not been Started yet "+"<br>"+"Survey Start date is "+userSurveyDTO.getSurveystart().toString();
	        	 model.addAttribute("surveyRes", surveyRes);
			        return new ModelAndView("thanksUser");
	        }
	        else
	        {
	        	log.info("Survey is Closed");
			       
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
		        	log.info("Survey is Active");
		        	log.info("userSurveyDTO " + userSurveyDTO.getSurveyId() + userSurveyDTO.getSurveyName());
		        	model.addAttribute("survey", userSurveyDTO);
		        	return new ModelAndView("viewSurvey");
		        }
		        else if(publishStatus.equalsIgnoreCase("pending"))
		        {
		        	log.info("Survey is Pending");
		        	String surveyRes = " This Survey has not been Started yet "+"<br>"+"Survey Start date is "+userSurveyDTO.getSurveystart().toString();
		        	 model.addAttribute("surveyRes", surveyRes);
				        return new ModelAndView("thanksUser");
		        }
		        else
		        {
		        	log.info("Survey is Closed");
		        	String surveyRes = " This Survey has been closed since "+userSurveyDTO.getSurveystart().toString();
		        	 model.addAttribute("surveyRes", surveyRes);
				        return new ModelAndView("thanksUser");
		        }
		        
	    }
    }
    
    @RequestMapping(value = "/v/survey{uri}", method = RequestMethod.GET)
    public ModelAndView viewSurveyRecord(ModelMap model, @PathVariable(value = "uri") String uri, @ModelAttribute("surveyAnswerDTO") SurveyAnswerDTO surveyAnswerDTO , HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
    	
    	  log.info("Request For viewing Survey Record of Survey :"+uri);
    	
    	    	
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
	   log.info("Delete Request for the Survey : "+uri);
    	
    	
    	boolean surveyDel = handler.deleteSurveyDetail(Integer.parseInt(uri));
    	
    	String result = "surveyDeleted";
    	
    	if(surveyDel == true)
    	{

    		log.info("Survey Deleted");
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
    	
    	  log.info("Editing Request for the Survey -"+uri);
    	SurveyDetailsDTO surveyDTO = handler.fetchSurveyDetail(Integer.parseInt(uri));
    	
    	model.addAttribute("survey",surveyDTO);
    	
    	return new ModelAndView("editSurvey");
    	
    	
    	
    }
    @RequestMapping(value="/e/question{uri}")
    public void editQuestion(ModelMap model, @PathVariable(value = "uri") String uri, @ModelAttribute("QuestionUIDTO") QuestionUIDTO question,@ModelAttribute("SurveyDetailsDTO") SurveyDetailsDTO survey) throws IOException,ParseException {
    	
    	 log.info("Editing Request for Question-"+uri);
    	
    	handler.updateMandatoryStatus(question,survey,Integer.parseInt(uri));
    		
    	handler.editQuestionText(question,Integer.parseInt(uri),survey);
    	
    		
    	
    	
    	
    }
    
    @RequestMapping(value="/d/question{uri}")
    public ModelAndView deleteQuestion(ModelMap model,@PathVariable(value = "uri") String uri, @ModelAttribute("SurveyDetailsDTO") SurveyDetailsDTO surveyDetails,HttpServletRequest request, HttpServletResponse response){
     
    	  log.info("Deletion Request for Question :-"+uri);
    	
    	  boolean del = handler.deleteQuestion(Integer.parseInt(uri),surveyDetails);
    	SurveyUIDTO survey = new SurveyUIDTO();
    	
    	survey.setSurveyId(decToInt(surveyDetails.getSurveyId()));
    	survey.setSurveyName(surveyDetails.getSurveyname());
    	survey.setSurveyDesc(surveyDetails.getDescription());
    	survey.setType(surveyDetails.getType());
   
    	if(del == true)
    	{
    		log.info("Question Deleted");
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
    	
    	log.info("Submitting Survey Reponse By the User-");
        ModelAndView modelAndView = new ModelAndView();
        String email = request.getParameter("surveyEmail");
      
        if (surveyResponseDTO != null) {

         
            for (SurveyQuestionDTO q : surveyResponseDTO.getSurveyQuestions()) {
               /*if (q.getAnswers()!=null){
                System.out.println("-->>>>> q.getAnswers()"+q.getAnswers());
                for (AnswerDTO	a:q.getAnswers()){
                System.out.println("-->>>>> a.getAnsId()"+a.getAnsId());

                }
                }*/

                if (q.getAnsTextList() != null) {
                   
                    for (String a : q.getAnsTextList()) {
                       

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
				log.info("response is already submitted by the user for this survey");
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
    	  log.info("Request for Creating User List through Excel file");
    	
    	File temp = File.createTempFile("ESurveyPlanner","");
    	temp.delete();
    	temp.mkdir();
    	log.info("Temporary folder Path :- "+temp.getAbsolutePath().toString());
    	
    	String saveDirectory = temp.getAbsolutePath().toString().trim();
    	log.info("Uploading file.. . . ..");
    	
    	List<UserDetailsDTO> userDetails = null;
    	MultipartFile file = uploadFile.getFile(); 
    	
    	log.info("file name is:- \t "+uploadFile.getFile().getOriginalFilename());
    	String userList = uploadFile.getUserListName();
    	log.info("User List name is:- \t "+uploadFile.getUserListName());
    	log.info("User List Description is:- \t "+uploadFile.getDescription());
    	 UserMaster usermaster = (UserMaster) userMasterService.fetch(BigDecimal.valueOf(userId));
    	  
    	 UserList userlist = null;
    	
    	String filename = "";
    	if (null != file && file.getSize() > 0) {
    		
    		   filename = file.getOriginalFilename();
    		
    		  if (!"".equalsIgnoreCase(filename)) {
                  
    			  File dest = new File(saveDirectory + userList + ".xls");
    			  
    			  file.transferTo(dest);
    			  log.info("File Uploaded");
    		    	
    			    			  
    			  userlist = handler.maptoUserList(uploadFile,usermaster);
    			  userListService.add(userlist);
    			
    			  log.info("---- user list Saved ----");
    			  
    			  
    			  userDetails = userExcel.mapUserDataToList(dest.getPath().toString());
    			 
    			  temp.delete();
    			  dest.delete();
    		  	}
    	  	}
    
    	  userDetails.remove(0);
    	    
    	  for(UserDetailsDTO user : userDetails)
    	  {
    		  log.info("Saving user details in the database ..................");
    		  int i=0;
    		  UserMaster userMaster = handler.mapToUsermaster(usermaster,user);
    		  
    		  UserListMapping userMap = handler.mapToUserListMapping(userlist,userMaster);
    		  log.info("USER  :-"+i+" userID :-"+userMap.getUserId().getId()+" User List id is :"+userMap.getUserListId().getId());
    		  userListMappingService.add(userMap);
    		
    		  i++;
    		  
    	  }
    	  log.info("save ------- DONE");
    	  
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
    	
    	log.info("Request for deleting user list :-"+uri);
    	
    	
    	
    	boolean userlistDel = handler.deleteUserList(Integer.parseInt(uri));
    		
    	String result = "userListDeleted";
    	
	    	if(userlistDel == true )
	    	{
	    		
	    		log.info("User list Deleted");
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
    	
    	log.info("Request for Sending Link of Survey "+uri +" through Email to the users ");
    	
    	List<UserListDTO> userlist	= handler.fetchAlluserlists();
    	SurveyDetailsDTO SurveyDTO = handler.fetchSurveyDetail(Integer.parseInt(uri));
    	String path	= req.getServerName()+":"+req.getServerPort()+""+req.getContextPath();
    	
    	
    	model.addAttribute("survey", SurveyDTO);
    	model.addAttribute("user", userlist);
    	model.addAttribute("path", path);
    	return new ModelAndView("sendmail");
    	
	    	
    
    }
    
    
    @RequestMapping(value = "/mail", method = RequestMethod.POST)
    public ModelAndView mailSending(ModelMap model,  @ModelAttribute("mailDetailDTO") mailDetailDTO mailDetails,HttpServletRequest req, HttpServletResponse res) throws Exception {
    	
    	log.info("Mail Sending.....");
    		
    		String sender = mailDetails.getSender();
    		String password = mailDetails.getPassword();
    		BigDecimal recieveId = mailDetails.getRListId();
    		String message = mailDetails.getMessage();
    		String subject = mailDetails.getSubject(); 
    		String link = mailDetails.getSurveylink();
    		BigDecimal surveyId = mailDetails.getSurveyId();
    		
	    		
    	
	    boolean mailsendStatus = false;
	 	
    	 mailsendStatus = handler.doSendMail(sender,password, recieveId , message , subject , link, surveyId);
    	 
    	 log.info(""+mailsendStatus);
    	if(mailsendStatus == true)
    	{
    	 	model.addAttribute("status", "success");
    		return new ModelAndView("mailSuccess");
    	}
    	else
    	{
    		log.error("Error Occured while Sending mail");
    		String path	= req.getContextPath()+"/send_survey"+surveyId;
    	   	 
    	   	 
    	 	model.addAttribute("path", path);
    	 	model.addAttribute("status", "error");
    		
    	 	return new ModelAndView("mailSuccess");
    	}
    
    }
    @RequestMapping(value="/publish{uri}")
    public ModelAndView publish(ModelMap model,@PathVariable(value = "uri") String uri,HttpServletRequest req, HttpServletResponse res) throws IOException,ParseException {
    	
    	log.info("Request For Publishing URL to the Social Network");
		
    	
    	
    	long code = handler.uniqueEncode(BigDecimal.valueOf(0), BigDecimal.valueOf(Integer.parseInt(uri)));
   	 String path	= req.getServerName()+":"+req.getServerPort()+""+req.getContextPath()+"/s/survey"+BigDecimal.valueOf(Integer.parseInt(uri))+"&u"+code;
   	 
   	 SurveyUIDTO surveydto = handler.fetchSurveyDetails(Integer.parseInt(uri));
   	
   	 model.addAttribute("survey", surveydto);
   	 
     model.addAttribute("path", path);
    	
    	return new ModelAndView("randomUrl");
    	
    	
    	
    }
   
   
    
    private int decToInt(BigDecimal dec){
			
			return Integer.parseInt(dec.toString()); // converting BigDecimal to Integer
			
		}
	}

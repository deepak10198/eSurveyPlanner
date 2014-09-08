package com.esp.handler;




import com.esp.entity.AnswerMaster;
import com.esp.entity.AnswerTextMaster;
import com.esp.entity.SurveyMaster;
import com.esp.entity.SurveyQuestionMapping;
import com.esp.entity.SurveyTypeMaster;
import com.esp.entity.UserMaster;
import com.esp.service.AnswerTextMasterService;
import com.esp.service.GenericService;
import com.esp.dto.FixedSurveyAnswerDetailsDTO;
import com.esp.dto.SurveyDetailsDTO;
import com.esp.dto.SurveyQuestionsDTO;
import com.esp.dto.UserSurveyDTO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;




@Component
public class HomeHandler {

 
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    @Autowired
    @Qualifier("AnswerTextMasterService")
    GenericService answerTextMasterService;
    
    @Autowired
    @Qualifier("SurveyMasterService")
    private GenericService surveyMasterService;
    
    @Autowired
    @Qualifier("SurveyQuestionMappingService")
    private GenericService surveyQuestionMappingService;
    
    @Autowired
    @Qualifier("QuestionAnswerMappingService")
    private GenericService questionAnswerMappingService;
    
    @Autowired
    @Qualifier("AnswerMasterService")
    private GenericService answerService;
    
    @Autowired
    @Qualifier("AnswerTypeMasterService")
    private GenericService answerTypeMasterService;
    

	public SurveyMaster mapToSurveymaster(UserMaster usermaster,SurveyTypeMaster surveyTypeMaster,SurveyDetailsDTO detailsVO) throws ParseException            
    {
        Date date = null;
        SurveyMaster surveymaster = new SurveyMaster(); 
        surveymaster.setSurveyName(detailsVO.getSurveyname());
        surveymaster.setSurveyDesc(detailsVO.getDescription());       
        surveymaster.setCreatedDate(new Date());
        date = dateFormat.parse(detailsVO.getSurveyend().trim());
        surveymaster.setEndDate(date);
        date = dateFormat.parse(detailsVO.getSurveystart());
        surveymaster.setStartDate(date);
        surveymaster.setLastModifiedDate(new Date());
        surveymaster.setSurveyCategory(null);
        surveymaster.setSurveyTypeId(surveyTypeMaster);
        surveymaster.setCreatedById(usermaster);
        surveymaster.setLastModifiedById(usermaster);
        return surveymaster;
        
    }
    
    public AnswerMaster mapToAnswerMaster(FixedSurveyAnswerDetailsDTO ansDetailsDTO) throws Exception    
    {
    	System.out.println("----mapToAnswerMaster :");	
        Date date = null;
        Class noparams[] = {};
        
        Class[] paramAnsText = new Class[1];
        paramAnsText[0] = AnswerTextMaster.class;
        
        AnswerMaster ansMaster = new AnswerMaster(); 
        
        List<String> ansTextList = ansDetailsDTO.getAnsTextList();
        System.out.println("----descList.size() :"+ansTextList);	
        
        if (ansTextList.size() >5) throw new Exception("Answers's list should not be greater than 5");
    	Class ansMasterClass = AnswerMaster.class;
    	
        for (int i =0; i<ansTextList.size(); i++ ) {
            Method method = ansMasterClass.getDeclaredMethod("setAnsText"+(i+1), paramAnsText);
        	System.out.println("----descList.get(i) :"+ansTextList.get(i));	
        	method.invoke(ansMaster,saveAnsText(ansTextList.get(i)) );
        	        	
		}
        
        return ansMaster;
        
    }
    
    
   private AnswerTextMaster saveAnsText(String text) throws ParseException    {
	   
	   System.out.println("----text :"+text);	
	   System.out.println("...."+answerTextMasterService);
	   List<AnswerTextMaster> ansTextList = answerTextMasterService.fetchByParam(text);
	   System.out.println("....2");
	   if (ansTextList==null || ansTextList.isEmpty()){
		   AnswerTextMaster ansText = new AnswerTextMaster();
		   ansText.setAnsText(text);
		   answerTextMasterService.add(ansText);
		   ansTextList = answerTextMasterService.fetchByParam(text);
		   
		   
	   }
	   System.out.println("....3");
	   return ansTextList.get(0);
	   
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		surveyDetails.setStartDate(sDate);
		surveyDetails.setEndDate(eDate);
		surveyDetails.setSurveyType(request.getParameter("type"));
		
		model.addAttribute("survey",surveyDetails);
		
		return surveyDetails;
		
	}*/
        

	
	public String handleAllRequest(HttpServletRequest request){
		
		String page = request.getRequestURI();
		System.out.println("URI req "+ page);
		page = page.substring(page.lastIndexOf("/")+1, page.length());
		return page;
	}
	
	/**
     * This method is used to fetch all the details of Survey in order to display it on the basis of Survey ID.
     * 
     * @param surveyId Survey ID for which details needs to be fetched.
     * @return UserSurveyDTO DTO having all the details of a survey.
     */
	public UserSurveyDTO fetchSurveyDetails(int surveyId){
		UserSurveyDTO userSurveyDTO = null;
		userSurveyDTO= new UserSurveyDTO();
		
		SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(surveyId);
		
		userSurveyDTO.setSurveyId(Integer.parseInt(surveyMaster.getId().toString()));
		userSurveyDTO.setSurveyDisplayId(surveyMaster.getSurveyId());
		userSurveyDTO.setSurveyName(surveyMaster.getSurveyName());
		userSurveyDTO.setSurveyDesc(surveyMaster.getSurveyDesc());
		
		userSurveyDTO.setSurveyQuestions(fetchSurveyQuestions(surveyId));
		
		return userSurveyDTO;
		
	}
	
	public SurveyQuestionsDTO fetchSurveyQuestions(int surveyId){
		SurveyQuestionsDTO surveyQuestionsDTO = new SurveyQuestionsDTO();
		SurveyQuestionMapping surveyQuestionMapping =   (SurveyQuestionMapping) surveyQuestionMappingService.fetchByParam(surveyId);
		
		
		
		
		return surveyQuestionsDTO;
		
	}
	
}

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
import com.esp.entity.UserMaster;
import com.esp.service.GenericService;
import com.esp.dto.AnswerDTO;
import com.esp.dto.FixedSurveyAnswerDetailsDTO;
import com.esp.dto.QuestionDTO;
import com.esp.dto.SurveyAnswerDTO;
import com.esp.dto.SurveyDTO;
import com.esp.dto.SurveyDetailsDTO;
import com.esp.dto.SurveyQuestionDTO;
import com.esp.dto.SurveyResponseDTO;
import com.esp.dto.UserSurveyDTO;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;





@Component
public class HomeHandler {

 
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    Date todayDate;
    
    @Autowired
    @Qualifier("AnswerTextMasterService")
    private GenericService answerTextMasterService;
    
    @Autowired
    @Qualifier("SurveyMasterService")
    private GenericService surveyMasterService;
    
    
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
    private GenericService surveyResponseService;
    
    
    
   

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
        

	public SurveyQuestionMapping mapToQuestionMaster(QuestionDTO questionDTO, UserMaster usermaster,SurveyDTO surveyDTO ){
		
		 System.out.println("----mapToQuestionMaster : Start");	
		todayDate = new Date();
		QuestionAnswerMapping questionAnswerMapping = null;
		SurveyQuestionMapping surveyQuestionMapping = null;
		SurveyMaster surveyMaster = (SurveyMaster) surveyMasterService.fetch(surveyDTO.getSurveyId());
		
		QuestionMaster questionMaster =	new QuestionMaster(); 
		
		for(String questionText: questionDTO.getQuestionText()){
			
			
			
			
			
			questionMaster = saveAndfetchQuesText(questionText,usermaster);
			
			
			
			AnswerMaster answerMaster = (AnswerMaster) answerService.fetch(surveyDTO.getAnsId());
			AnswerTypeMaster answerTypeMaster = (AnswerTypeMaster) answerTypeMasterService.fetch(surveyDTO.getAnsTypeID()); 
			
			questionAnswerMapping = saveQuesAnsMapping(questionMaster,answerMaster,answerTypeMaster , usermaster);
			surveyQuestionMapping = saveSurveyQuestionMapping(surveyMaster,questionAnswerMapping,usermaster);
			
		}
		
		
		
		System.out.println("----mapToQuestionMaster : end");	
		return surveyQuestionMapping;
	}

	private QuestionMaster saveAndfetchQuesText(String questionText, UserMaster usermaster )    {
		   
		   System.out.println("----questionText :"+questionText);	
		   List<QuestionMaster> quesMasterList = questionMasterService.fetchByParam(questionText);
		   System.out.println("....2");
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
		   System.out.println("....3");
		   return quesMasterList.get(0);
		   
	   } QuestionAnswerMapping saveQuesAnsMapping( QuestionMaster questionMaster, AnswerMaster answerMaster, AnswerTypeMaster answerTypeMaster, UserMaster usermaster){
		
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
	
	public SurveyQuestionMapping saveSurveyQuestionMapping(SurveyMaster surveyMaster, QuestionAnswerMapping questionAnswerMapping,UserMaster usermaster){
		
		SurveyQuestionMapping surveyQuestionMapping = new SurveyQuestionMapping();
		
		surveyQuestionMapping.setCreatedById(usermaster);
		surveyQuestionMapping.setCreationDate(todayDate);
		surveyQuestionMapping.setLastModifiedById(usermaster);
		surveyQuestionMapping.setLastModifiedDate(todayDate);
		surveyQuestionMapping.setQuestionAnsId(questionAnswerMapping);
		surveyQuestionMapping.setSurveyId(surveyMaster);
		
		
		surveyQuestionMappingService.add(surveyQuestionMapping);
		return surveyQuestionMapping;
	}
	
	
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
		System.out.println("surveyId for fetching details"+surveyId);
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
	
	public List<SurveyQuestionDTO> fetchSurveyQuestions(int surveyId){
		
		List<SurveyQuestionDTO> surveyQuestionsList = new ArrayList<SurveyQuestionDTO>();
		
		
		SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(surveyId);
		List<SurveyQuestionMapping> surveyQuestionMappingList =   surveyQuestionMappingService.fetchByParam(surveyMaster);
		
		
		for(SurveyQuestionMapping  surveyQuestionMapping: surveyQuestionMappingList){
			SurveyQuestionDTO surveyQuestionsDTO = new SurveyQuestionDTO();
			List<String> ansTextList = new ArrayList<String>();
			List<AnswerDTO> answerList = new ArrayList<AnswerDTO>();
			
			
			//QuestionAnswerMapping questionAnswerMapping= (QuestionAnswerMapping) questionAnswerMappingService.fetch (Integer.parseInt(surveyQuestionMapping.getQuestionAnsId().toString())) ;
			
			QuestionAnswerMapping questionAnswerMapping= surveyQuestionMapping.getQuestionAnsId();
			
			System.out.println("questionAnswerMapping "+questionAnswerMapping.getId());
			
			surveyQuestionsDTO.setQuesAnswerId(decToInt(questionAnswerMapping.getId()));
			surveyQuestionsDTO.setAnsId(Integer.parseInt(questionAnswerMapping.getAnswerId().getId().toString()  ));
			
			answerList = mapAnswersMasterToList(questionAnswerMapping.getAnswerId() );
			
			System.out.println("questionAnswerMapping.getAnswerId() "+questionAnswerMapping.getAnswerId().getId());
			
			/*if (questionAnswerMapping.getAnswerId().getAnsText1()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText1().getAnsText() );
				answerList.
				
			}
			if (questionAnswerMapping.getAnswerId().getAnsText2()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText2().getAnsText() );
			}
			if (questionAnswerMapping.getAnswerId().getAnsText3()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText3().getAnsText() );
			}
			if (questionAnswerMapping.getAnswerId().getAnsText4()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText4().getAnsText() );
			}
			if (questionAnswerMapping.getAnswerId().getAnsText5()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText5().getAnsText() );
			}
			if (questionAnswerMapping.getAnswerId().getAnsText6()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText6().getAnsText() );
			}
			if (questionAnswerMapping.getAnswerId().getAnsText7()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText7().getAnsText() );
			}
			if (questionAnswerMapping.getAnswerId().getAnsText8()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText8().getAnsText() );
			}
			if (questionAnswerMapping.getAnswerId().getAnsText9()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText9().getAnsText() );
			}
			if (questionAnswerMapping.getAnswerId().getAnsText10()!=null){
				ansTextList.add( questionAnswerMapping.getAnswerId().getAnsText10().getAnsText() );
			}*/
			
			surveyQuestionsDTO.setAnswers(answerList);
			surveyQuestionsDTO.setAnsType(questionAnswerMapping.getAnsTypeId().getAnsTypeText());
			surveyQuestionsDTO.setAnsTypeId(decToInt(questionAnswerMapping.getAnsTypeId().getId()));
			surveyQuestionsDTO.setQuestionId(decToInt(questionAnswerMapping.getQuestionId().getId()));
			surveyQuestionsDTO.setQuestionText(questionAnswerMapping.getQuestionId().getQuestionText());
			
			surveyQuestionsList.add(surveyQuestionsDTO);
			
		}
		
		
		return surveyQuestionsList;
		
	}
	
	private List<AnswerDTO> mapAnswersMasterToList(AnswerMaster answerMaster){
		System.out.println("...mapAnswersMasterToList"+answerMaster.getId());
		List<AnswerDTO> answerList = new ArrayList<AnswerDTO>();
		
		
		Class ansMaster  = AnswerMaster.class;
		try{
			for(int i=1; i<=10; i++){
				Method method = AnswerMaster.class.getDeclaredMethod("getAnsText"+i);
				 
				AnswerTextMaster answerTextMaster = (AnswerTextMaster) method.invoke(answerMaster);
				AnswerDTO answer = null;
				System.out.println(i+"...1 answerTextMaster" +answerTextMaster);
				if (answerTextMaster!=null){
					answer = new AnswerDTO();
					answer.setAnsId( decToInt(answerTextMaster.getId()) );
					answer.setAnsText(answerTextMaster.getAnsText());
					//answer.setAnsId( decToInt( ((AnswerTextMaster)method.invoke(answerMaster)).getId() ) );
					
					System.out.println("...2"+answerTextMaster.getAnsText());
					
					//answer.setAnsText( ((AnswerTextMaster)method.invoke(answerMaster)).getAnsText() );
					answerList.add(answer);
					
				}else{
					
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return answerList;
		
	}
	
	
	public void submitSurveyResponse(SurveyResponseDTO surveyResponseDTO, UserMaster usermaster, String ipAddress, String email){
		
		System.out.println("IP : "+ipAddress);
		SurveyMaster surveyMaster = (SurveyMaster) surveyMasterService.fetch(surveyResponseDTO.getSurveyId());
		
		
		for (SurveyQuestionDTO surveyQuestionDTO: surveyResponseDTO.getSurveyQuestions()){
			SurveyResponse surveyResponse = new SurveyResponse();
			
			QuestionAnswerMapping quesAnsMapping=(QuestionAnswerMapping) questionAnswerMappingService.fetch(surveyQuestionDTO.getQuesAnswerId());
			
			
			surveyResponse.setEmailId(email);
			surveyResponse.setIpAddress(ipAddress);
			
			surveyResponse.setQuesAnsMappingId(quesAnsMapping);
			surveyResponse.setSurveyId(surveyMaster);
			surveyResponse.setUserId(usermaster);
			
			if(surveyQuestionDTO.getAnsIdList()!=null){
			for (Integer ansId: surveyQuestionDTO.getAnsIdList() ){
					AnswerTextMaster answerText = (AnswerTextMaster) answerTextMasterService.fetch(ansId);
					surveyResponse.setAnsTextId(answerText);
					surveyResponse.setAnsText(answerText.getAnsText());
					surveyResponseService.add(surveyResponse);
				}
			}
			System.out.println("surveyResponse.getId() : "+surveyResponse.getId());
		}
	}
	
	private int decToInt(BigDecimal dec){
		
		return Integer.parseInt(dec.toString());
		
	}
	
	
}

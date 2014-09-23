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
import com.esp.dto.ElementDTO;
import com.esp.dto.FixedSurveyAnswersDTO;
import com.esp.dto.QuestionUIDTO;
import com.esp.dto.FixedSurveyQuestionsDTO;
import com.esp.dto.SurveyDTO;
import com.esp.dto.SurveyDetailsDTO;
import com.esp.dto.SurveyQuestionDTO;
import com.esp.dto.SurveyResponseDTO;
import com.esp.dto.SurveyUIDTO;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
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
    
    public AnswerMaster mapToAnswerMaster(FixedSurveyAnswersDTO ansDetailsDTO) throws Exception    
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
        
        answerService.add(ansMaster); 
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
        

	public SurveyQuestionMapping mapToQuestionMaster(FixedSurveyQuestionsDTO questionDTO, UserMaster usermaster,SurveyDTO surveyDTO ){
		
		 System.out.println("----mapToQuestionMaster : Start");	
		todayDate = new Date();
		QuestionAnswerMapping questionAnswerMapping = null;
		SurveyQuestionMapping surveyQuestionMapping = null;
		SurveyMaster surveyMaster = (SurveyMaster) surveyMasterService.fetch(BigDecimal.valueOf(surveyDTO.getSurveyId()));
		
		QuestionMaster questionMaster =	new QuestionMaster(); 
		
		for(String questionText: questionDTO.getQuestionText()){
			
			
			questionMaster = saveAndfetchQuesText(questionText,usermaster);
			
			AnswerMaster answerMaster = (AnswerMaster) answerService.fetch(BigDecimal.valueOf(questionDTO.getAnsId()));
			AnswerTypeMaster answerTypeMaster = (AnswerTypeMaster) answerTypeMasterService.fetch(BigDecimal.valueOf(questionDTO.getAnsTypeId()));
			
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
			if(quesText!=null && "".equals(quesText)){
				questionMaster = saveAndfetchQuesText(quesText,usermaster);
				
				FixedSurveyAnswersDTO ansDetailsDTO = new FixedSurveyAnswersDTO();
				ansDetailsDTO.setAnsTypeId(questionDTO.getAnsTypeId());
				ansDetailsDTO.setAnsTextList(questionDTO.getAnsTextList());
				
				AnswerMaster answerMaster = mapToAnswerMaster(ansDetailsDTO);
				AnswerTypeMaster answerTypeMaster = (AnswerTypeMaster) answerTypeMasterService.fetch(BigDecimal.valueOf(questionDTO.getAnsTypeId()));
				
				questionAnswerMapping = saveQuesAnsMapping(questionMaster,answerMaster,answerTypeMaster , usermaster);
				surveyQuestionMapping = saveSurveyQuestionMapping(surveyMaster,questionAnswerMapping,usermaster);
				System.out.println("Survey question answers saved inaa - " + surveyQuestionMapping.getId());
			}
			
			
		}
		
		
		return surveyQuestionMapping;
		
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
	public SurveyUIDTO fetchSurveyDetails(int surveyId){
		System.out.println("surveyId for fetching details"+surveyId);
		SurveyUIDTO userSurveyDTO = null;
		userSurveyDTO= new SurveyUIDTO();
		
		SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(BigDecimal.valueOf(surveyId));
		
		userSurveyDTO.setSurveyId(Integer.parseInt(surveyMaster.getId().toString()));
		userSurveyDTO.setSurveyDisplayId(surveyMaster.getSurveyId());
		userSurveyDTO.setSurveyName(surveyMaster.getSurveyName());
		userSurveyDTO.setSurveyDesc(surveyMaster.getSurveyDesc());
		
		userSurveyDTO.setSurveyQuestions(fetchSurveyQuestions(surveyId));
		
		return userSurveyDTO;
		
	}
	
	public List<QuestionUIDTO> fetchSurveyQuestions(int surveyId){
		
		List<QuestionUIDTO> questionsList = new ArrayList<QuestionUIDTO>();
		
		
		SurveyMaster surveyMaster = (SurveyMaster)surveyMasterService.fetch(BigDecimal.valueOf(surveyId));
		List<SurveyQuestionMapping> surveyQuestionMappingList =   surveyQuestionMappingService.fetchByParam(surveyMaster);
		
		
		for(SurveyQuestionMapping  surveyQuestionMapping: surveyQuestionMappingList){
			QuestionUIDTO questionUIDTO = new QuestionUIDTO();
			List<String> ansTextList = new ArrayList<String>();
			List<ElementDTO> answerList = new ArrayList<ElementDTO>();
			
			
			//QuestionAnswerMapping questionAnswerMapping= (QuestionAnswerMapping) questionAnswerMappingService.fetch (Integer.parseInt(surveyQuestionMapping.getQuestionAnsId().toString())) ;
			
			QuestionAnswerMapping questionAnswerMapping= surveyQuestionMapping.getQuestionAnsId();
			
			System.out.println("questionAnswerMapping "+questionAnswerMapping.getId());
			
			questionUIDTO.setQuesAnswerId(decToInt(questionAnswerMapping.getId()));
			
			answerList = mapAnswersMasterToList(questionAnswerMapping.getAnswerId() );
			
			System.out.println("questionAnswerMapping.getAnswerId() "+questionAnswerMapping.getAnswerId().getId());
			
			
			questionUIDTO.setAnswers(answerList);
			questionUIDTO.setAnsType(questionAnswerMapping.getAnsTypeId().getAnsTypeText());
			questionUIDTO.setAnsTypeId(decToInt(questionAnswerMapping.getAnsTypeId().getId()));
			questionUIDTO.setQuestionId(decToInt(questionAnswerMapping.getQuestionId().getId()));
			questionUIDTO.setQuestionText(questionAnswerMapping.getQuestionId().getQuestionText());
			
			questionsList.add(questionUIDTO);
			
		}
		
		
		return questionsList;
		
	}
	
	private List<ElementDTO> mapAnswersMasterToList(AnswerMaster answerMaster){
		System.out.println("...mapAnswersMasterToList"+answerMaster.getId());
		List<ElementDTO> answerList = new ArrayList<ElementDTO>();
		
		
		Class ansMaster  = AnswerMaster.class;
		try{
			for(int i=1; i<=10; i++){
				Method method = AnswerMaster.class.getDeclaredMethod("getAnsText"+i);
				 
				AnswerTextMaster answerTextMaster = (AnswerTextMaster) method.invoke(answerMaster);
				ElementDTO answer = null;
				System.out.println(i+"...1 answerTextMaster" +answerTextMaster);
				if (answerTextMaster!=null){
					answer = new ElementDTO();
					answer.setId( decToInt(answerTextMaster.getId()) );
					answer.setText(answerTextMaster.getAnsText());
					
					System.out.println("...2"+answerTextMaster.getAnsText());
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
		SurveyMaster surveyMaster = (SurveyMaster) surveyMasterService.fetch(BigDecimal.valueOf(surveyResponseDTO.getSurveyId()));
		
		
		for (SurveyQuestionDTO surveyQuestionDTO: surveyResponseDTO.getSurveyQuestions()){
			SurveyResponse surveyResponse = new SurveyResponse();
			
			QuestionAnswerMapping quesAnsMapping=(QuestionAnswerMapping) questionAnswerMappingService.fetch(BigDecimal.valueOf(surveyQuestionDTO.getQuesAnswerId()));
			System.out.println("quesAnsMapping : "+quesAnsMapping.getId());
			
			surveyResponse.setEmailId(email);
			surveyResponse.setIpAddress(ipAddress);
			
			surveyResponse.setQuesAnsMappingId(quesAnsMapping);
			surveyResponse.setSurveyId(surveyMaster);
			surveyResponse.setUserId(usermaster);
			
			System.out.println("surveyQuestionDTO.getAnsTypeId() : "+surveyQuestionDTO.getAnsTypeId());
			
			if(surveyQuestionDTO.getAnsTextList()!=null && (surveyQuestionDTO.getAnsTypeId()==1 || surveyQuestionDTO.getAnsTypeId()==2) ){
			for (String ansId: surveyQuestionDTO.getAnsTextList() ){
				
					AnswerTextMaster answerText = (AnswerTextMaster) answerTextMasterService.fetch(new BigDecimal(ansId.trim()));
					surveyResponse.setAnsTextId(answerText);
					surveyResponse.setAnsText(answerText.getAnsText());
					System.out.println("answerText.getAnsText() : "+answerText.getAnsText());
					surveyResponseService.add(surveyResponse);
				}
			}
			System.out.println("surveyResponse.getId() : "+surveyResponse.getId());
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
		List<ElementDTO> surveyTypes = new ArrayList<ElementDTO>();
		
		List<SurveyTypeMaster> surveyTypeMasters = surveyTypeMasterService.fetchAll();
		
		if(surveyTypeMasters==null || surveyTypeMasters.isEmpty()){
			throw new Exception("There are not survey type defined!");
		}
		
		for(SurveyTypeMaster surveyTypeMaster: surveyTypeMasters){
			ElementDTO element = new ElementDTO(); 
			element.setId( decToInt(surveyTypeMaster.getId()) );
			element.setText(surveyTypeMaster.getSurveyTypeText());
			
			surveyTypes.add(element);
		}
		
		return surveyTypes;
		
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
	
	
}

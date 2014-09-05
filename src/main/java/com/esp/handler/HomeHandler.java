package com.esp.handler;




import com.esp.entity.AnswerMaster;
import com.esp.entity.AnswerTextMaster;
import com.esp.entity.SurveyMaster;
import com.esp.entity.SurveyTypeMaster;
import com.esp.entity.UserMaster;
import com.esp.service.AnswerTextMasterService;
import com.esp.service.GenericService;
import com.esp.vo.FSAnswerDetailsVO;
import com.esp.vo.SurveyDetailsVO;
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
    

	public SurveyMaster mapToSurveymaster(UserMaster usermaster,SurveyTypeMaster surveyTypeMaster,SurveyDetailsVO detailsVO) throws ParseException            
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
    
    public AnswerMaster mapToAnswerMaster(FSAnswerDetailsVO ansDetailsVO) throws Exception    
    {
        Date date = null;
        Class noparams[] = {};
        
        Class[] paramAnsDesc = new Class[1];
        paramAnsDesc[0] = AnswerTextMaster.class;
        
        AnswerMaster ansMaster = new AnswerMaster(); 
        //ansMaster.setAnswerdescriptionmasterByAnsdescid1(ansDetailsVO.getAnsDesc1());
        
        List<String> descList = ansDetailsVO.getAnsDesc();
        System.out.println("----descList.size() :"+descList.size());	
        
        if (descList.size() >5) throw new Exception("Answers's list should not be greater than 5");
    	Class ansMasterClass = AnswerMaster.class;
    	
        for (int i =0; i<descList.size(); i++ ) {
            Method method = ansMasterClass.getDeclaredMethod("setAnsText"+(i+1), paramAnsDesc);
        	System.out.println("----descList.get(i) :"+descList.get(i));	
        	method.invoke(ansMaster,toAnsDesc(descList.get(i)) );
        	        	
		}
        
        return ansMaster;
        
    }
    
    
   private AnswerTextMaster toAnsDesc(String desc) throws ParseException    {
	   
	   System.out.println("----desc :"+desc);	
	   System.out.println("...."+answerTextMasterService);
	   List<AnswerTextMaster> ansDescList = answerTextMasterService.fetchByParam(desc);
	   
	   if (ansDescList==null || ansDescList.isEmpty()){
		   AnswerTextMaster ansDesc = new AnswerTextMaster();
		   ansDesc.setAnsText(desc);
		   answerTextMasterService.add(ansDesc);
		   ansDescList = answerTextMasterService.fetchByParam(desc);
		   
		   
	   }
	   
	   return ansDescList.get(0);
	   
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
	
}

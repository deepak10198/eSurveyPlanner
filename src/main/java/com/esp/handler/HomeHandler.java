package com.esp.handler;

import com.esp.dto.SurveyDetailsDto;
import com.esp.entity.Surveymaster;
import com.esp.entity.Surveytypemaster;
import com.esp.entity.Usermaster;
import com.esp.vo.SurveyDetailsVO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;


@Component
public class HomeHandler {

 
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    
    
    public Surveymaster mapToSurveymaster(Usermaster usermaster,Surveytypemaster surveytypemaster,SurveyDetailsVO detailsVO) throws ParseException            
    {
        Date date = null;
        Surveymaster surveymaster = new Surveymaster(); 
        surveymaster.setSurveyName(detailsVO.getSurveyname());
        surveymaster.setSurveyDesc(detailsVO.getDescription());       
        surveymaster.setCreatedbyname(usermaster.getFirstname()+" "+usermaster.getLastname());
        surveymaster.setCreateddate(new Date());
        date = dateFormat.parse(detailsVO.getSurveyend().trim());
        surveymaster.setEnddate(date);
        date = dateFormat.parse(detailsVO.getSurveystart());
        surveymaster.setStartdate(date);
        surveymaster.setLastmodifiedbyname(usermaster.getFirstname()+" "+usermaster.getLastname());
        surveymaster.setLastmodifieddate(new Date());
        surveymaster.setSurveycategory(null);
        surveymaster.setSurveytypemaster(surveytypemaster);
        surveymaster.setUsermasterByCreatedbyid(usermaster);
        surveymaster.setUsermasterByLastmodifiedbyid(usermaster);
        return surveymaster;
        
    }
    
	
	public String handleRequest(){
		
		
		return "home";
	   }
	

	
	public SurveyDetailsDto setDetails(ApplicationContext context, ModelMap model,HttpServletRequest request){
		
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
		
	}
        

	
	public String handleAllRequest(HttpServletRequest request){
		
		String page = request.getRequestURI();
		System.out.println("URI req "+ page);
		page = page.substring(page.lastIndexOf("/")+1, page.length());
		return page;
	}
	
}

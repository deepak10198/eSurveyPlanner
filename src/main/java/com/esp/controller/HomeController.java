package com.esp.controller;

import com.esp.entity.AnswerMaster;
import com.esp.entity.AnswerTypeMaster;
import com.esp.entity.SurveyMaster;
import com.esp.entity.SurveyTypeMaster;
import com.esp.entity.UserMaster;
import com.esp.handler.HomeHandler;
import com.esp.service.GenericService;
import com.esp.dto.FixedSurveyAnswerDetailsDTO;
import com.esp.dto.QuestionDTO;
import com.esp.dto.SurveyDetailsDTO;
import com.esp.dto.SurveyDTO;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

 
    
    @Autowired
    @Qualifier("UserMasterService") 
    private GenericService userMasterService;
    
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
    private HomeHandler handler;
    
    //for testing
    Integer userId = 100;
    
    private Logger log = Logger.getLogger(this.getClass().getName());
    
   
       
    @RequestMapping(value = "")
    public ModelAndView Home(HttpServletRequest request, HttpServletResponse response) throws IOException {


        return new ModelAndView("home");

    }
    
    @RequestMapping(value = "/createSurvey")
    public ModelAndView createSurvey(HttpServletResponse response) throws IOException {
        log.info("*****************888Create Survey");
        return new ModelAndView("createSurvey");
    }
    
    @RequestMapping(value = "/submitSurveyMaster")
    public ModelAndView submitSurvey(ModelMap model,@ModelAttribute("surveyDetailsDTO") SurveyDetailsDTO surveyDetailsDTO) throws IOException {
        
        try {
            log.info("Going to save Survey Master Details -");
            log.info("submitting survey to database");
            log.info("name :" + surveyDetailsDTO.getSurveyname());
            log.info("desc :" + surveyDetailsDTO.getDescription());
            log.info("type ;" + surveyDetailsDTO.getType());
            
            UserMaster usermaster = (UserMaster) userMasterService.fetch(new BigDecimal(userId));
            SurveyTypeMaster surveytypemaster = (SurveyTypeMaster)surveyTypeMasterService.fetchByParam(surveyDetailsDTO.getType());
            SurveyMaster surveymaster = handler.mapToSurveymaster(usermaster, surveytypemaster, surveyDetailsDTO);
            surveyMasterService.add(surveymaster);            
            log.info("Survey Saved to database");
            
            log.info("getting answertype master list");
            
            List<AnswerTypeMaster> answermasters  = answerTypeMasterService.fetchAll();
            
            log.info("Answertype master list fetched");
            
            SurveyDTO surveyDTO = new SurveyDTO();
            surveyDTO.setSurveyId(Integer.parseInt(surveymaster.getId().toString()));
            surveyDTO.setSurveyName(surveymaster.getSurveyName());
            log.info("SurveyVO is set");
            
            model.addAttribute("surveyDTO", surveyDTO);
            model.addAttribute("answerTypeMaster", answermasters);
            return new ModelAndView("answerType");
            
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value = "/addQuestions")
    public ModelAndView addQuestions(ModelMap model,@ModelAttribute("surveyDTO") SurveyDTO surveyDTO,
    									@ModelAttribute("answerDetailsDTO") FixedSurveyAnswerDetailsDTO answerDetailsDTO) throws IOException {

    	try{
    		log.info("Going to save Answer Master details -");   
    		
    		log.info("Going answerDetailsDTO.getAnsType()-"+answerDetailsDTO.getAnsTextList());   
	    	
	    	AnswerMaster answerMaster = handler.mapToAnswerMaster(answerDetailsDTO);
	    	answerService.add(answerMaster); 
	    	
	        surveyDTO.setAnsId(Integer.parseInt(answerMaster.getId().toString()));
	        log.info("survey name"+surveyDTO.getSurveyName());
                log.info("survey id"+surveyDTO.getSurveyId());
                log.info("survey ans id"+surveyDTO.getAnsId());
                
                model.addAttribute("surveyDTO", surveyDTO);              
                
                
	        return new ModelAndView("addQuestions");
    	} catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value = "saveQuestions", method = RequestMethod.POST)
    public ModelAndView saveQuestions(@ModelAttribute("questionDTO") QuestionDTO questionDTO, @ModelAttribute("surveyDTO") SurveyDTO surveyDTO) {
        ModelAndView modelAndView = new ModelAndView();
        
        log.info("--->survey id :" + surveyDTO.getSurveyId());
        log.info("--->survey name :" + surveyDTO.getSurveyName());
        log.info("--->survey ans id:" + surveyDTO.getAnsId());
        log.info("-->survey ans type id :"+surveyDTO.getAnsTypeID());
        log.info("--->survey question list size :" + questionDTO.getQuestionText().size());
        
        for(String question :questionDTO.getQuestionText())
        {
            log.info("question :"+question);
        }
        
        
        
        modelAndView.setViewName("home");
        return modelAndView;
    }//
    

}

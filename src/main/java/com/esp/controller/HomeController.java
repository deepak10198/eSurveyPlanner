package com.esp.controller;

import com.esp.entity.AnswerMaster;
import com.esp.entity.AnswerTypeMaster;
import com.esp.entity.SurveyMaster;
import com.esp.entity.SurveyTypeMaster;
import com.esp.entity.UserMaster;
import com.esp.handler.HomeHandler;
import com.esp.service.AnswerMasterService;
import com.esp.service.GenericService;
import com.esp.service.SurveyMasterService;
import com.esp.service.SurveyTypeMasterService;
import com.esp.service.UserMasterService;
import com.esp.vo.FSAnswerDetailsVO;
import com.esp.vo.QuestionVO;
import com.esp.vo.SurveyDetailsVO;
import com.esp.vo.SurveyVO;
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

    //ApplicationContext context = new ClassPathXmlApplicationContext("**/eSurvey.xml");
    @Autowired
    private ApplicationContext context;
    
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

//        log.info("Home page has been called");
//        HomeHandler homeHandler = new HomeHandler();
//
//        String page = homeHandler.handleRequest();
//        System.out.println("test2");
        return new ModelAndView("home");
        //return new ModelAndView("home");
        //return "home";
    }
    
    @RequestMapping(value = "/createSurvey")
    public ModelAndView createSurvey(HttpServletResponse response) throws IOException {
        System.out.println("*****************888Create Survey");
        return new ModelAndView("createSurvey");
    }
    
    @RequestMapping(value = "/submitSurveyMaster")
    public ModelAndView submitSurvey(HttpServletRequest request, HttpServletResponse response, ModelMap model, 
    		@ModelAttribute("surveyDetailsForm") SurveyDetailsVO surveyDetailsVO) throws IOException {
        
        try {
            log.info("Going to save Survey Master Details -");
            log.info("submitting survey to database");
            log.info("name :" + surveyDetailsVO.getSurveyname());
            log.info("desc :" + surveyDetailsVO.getDescription());
            log.info("type ;" + surveyDetailsVO.getType());
            
            UserMaster usermaster = (UserMaster) userMasterService.fetch(userId);
            SurveyTypeMaster surveytypemaster = (SurveyTypeMaster)surveyTypeMasterService.fetchByParam(surveyDetailsVO.getType());
            SurveyMaster surveymaster = handler.mapToSurveymaster(usermaster, surveytypemaster, surveyDetailsVO);
            surveyMasterService.add(surveymaster);            
            log.info("Survey Saved to database");
            
            log.info("getting answertype master list");
            
            List<AnswerTypeMaster> answermasters  = answerTypeMasterService.fetchAll();
            
            log.info("Answertype master list fetched");
            
            SurveyVO survey = new SurveyVO();
            survey.setSurveyId(surveymaster.getSurveyId());
            survey.setSurveyName(surveymaster.getSurveyName());
            log.info("SurveyVO is set");
            
            model.addAttribute("surveyDetails", survey);
            model.addAttribute("answermasters", answermasters);
            return new ModelAndView("answerType");
            
        } catch (ParseException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value = "/addQuestions")
    public ModelAndView addQuestions(HttpServletRequest request, HttpServletResponse response, ModelMap model,
    									@ModelAttribute("surveyVO") SurveyVO survey,
    									@ModelAttribute("answerDetailsForm") FSAnswerDetailsVO answerDetailsVO) throws IOException {

    	try{
    		log.info("Going to save Answer Master details -");
    	
	    	
	    	AnswerMaster answerMaster = handler.mapToAnswerMaster(answerDetailsVO);
	    	answerService.add(answerMaster);
	    	
	    	survey.setSurveyId(request.getParameter("surveyid"));
	        survey.setSurveyName(request.getParameter("surveyname"));
	        survey.setAnsId((BigDecimal)answerMaster.getId());
	        model.addAttribute("surveyDetails", survey);
	    	
	        return new ModelAndView("addQuestions");
    	} catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value = "saveQuestions", method = RequestMethod.POST)
    public ModelAndView saveQuestions(@ModelAttribute("questionVo") QuestionVO questionVO, @ModelAttribute("surveyVO") SurveyVO surveyVO) {
        ModelAndView modelAndView = new ModelAndView();
        
        log.info("--->survey id :" + surveyVO.getSurveyId());
        log.info("--->survey name :" + surveyVO.getSurveyName());
        log.info("--->survey ans id:" + surveyVO.getAnsId());
        log.info("--->survey question list size :" + questionVO.getQuestionText().size());
        
        
        
        modelAndView.setViewName("home");
        return modelAndView;
    }//
    
    /*@RequestMapping(value = "viewSurvey", method = RequestMethod.GET)
    public ModelAndView viewSurveys(Principal principal, ModelMap model) {
        
        List<Surveymaster> surveyList = surveyMasterService.listSurveys(userId);
        model.addAttribute("surveyList", surveyList);        
        return new ModelAndView("viewSurvey");
    }*/

    /*@RequestMapping(method = RequestMethod.GET)
     public ModelAndView moveToURL(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
     log.info("Other Req");
     HomeHandler homeHandler = new HomeHandler();
		
     String page = homeHandler.handleAllRequest(request);
     System.out.println("Going to page "+page);
     return new ModelAndView(page);
     //return new ModelAndView("home");
     //return "home";
     }
     */
}

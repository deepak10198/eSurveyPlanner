package com.esp.controller;

import com.esp.entity.Surveymaster;
import com.esp.entity.Surveytypemaster;
import com.esp.entity.Usermaster;
import com.esp.handler.HomeHandler;
import com.esp.service.SurveyMasterService;
import com.esp.service.SurveyTypeMasterService;
import com.esp.service.UserMasterService;
import com.esp.service.impl.UserMasterServiceImpl;
import com.esp.vo.SurveyDetailsVO;
import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserMasterService userMasterService;

    @Autowired
    private SurveyMasterService surveyMasterService;

    @Autowired
    private SurveyTypeMasterService surveyTypeMasterService;

    //for testing
    Integer userId = 100;

    private Logger log = Logger.getLogger(this.getClass().getName());

    private HomeHandler handler = new HomeHandler();

    /*@Autowired
     ServletContext contex1t;*/
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

        return new ModelAndView("createSurvey");
    }

    @RequestMapping(value = "/submitSurvey")
    public ModelAndView submitSurvey(HttpServletRequest request, HttpServletResponse response, ModelMap model, @ModelAttribute("surveyDetailsVO") SurveyDetailsVO surveyDetailsVO) throws IOException {

        
        try {
            log.info("Start of submitSurvey Method");
            log.info("submitting survey to database");
            log.info("name :" + surveyDetailsVO.getSurveyname());
            log.info("desc :" + surveyDetailsVO.getDescription());
            log.info("type ;" + surveyDetailsVO.getType());

            Usermaster usermaster = userMasterService.getUser(userId);
            Surveytypemaster surveytypemaster = surveyTypeMasterService.getSurveyTypeMaster(surveyDetailsVO.getType());
            Surveymaster surveymaster = handler.mapToSurveymaster(usermaster, surveytypemaster, surveyDetailsVO);
            surveyMasterService.addSurvey(surveymaster);            
            log.info("Survey Saved to database");
            model.addAttribute("surveyDetailsVO", surveyDetailsVO);
            return new ModelAndView("thanks");

        } catch (ParseException ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
    
    @RequestMapping(value = "viewSurvey",method =RequestMethod.GET)
    public ModelAndView viewSurveys(Principal principal,ModelMap model)
    {
        
        List<Surveymaster> surveyList = surveyMasterService.listSurveys(userId);
        model.addAttribute("surveyList", surveyList);        
        return new ModelAndView("viewSurvey");
    }
    
    
 
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

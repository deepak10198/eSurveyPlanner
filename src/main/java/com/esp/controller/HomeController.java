package com.esp.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.esp.dto.SurveyDetailsDto;
import com.esp.handler.HomeHandler;

import org.apache.log4j.Logger;

@Controller
public class HomeController {

	//ApplicationContext context = new ClassPathXmlApplicationContext("**/eSurvey.xml");
	
	@Autowired
	private ApplicationContext context;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/*@Autowired
	ServletContext contex1t;*/
	
	@RequestMapping(value="/")
	public ModelAndView Home(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		log.info("Home page has been called");
		HomeHandler homeHandler = new HomeHandler();
		
		String page = homeHandler.handleRequest();
		System.out.println("test2");
		return new ModelAndView(page);
		//return new ModelAndView("home");
		//return "home";
	}
	
	@RequestMapping(value="/createSurvey")
	public ModelAndView createSurvey(HttpServletResponse response) throws IOException{
		return new ModelAndView("createSurvey");
	}
	
	@RequestMapping(value="/submitSurvey")
	public ModelAndView submitSurvey(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException{
		
		HomeHandler homeHandler = new HomeHandler();
		SurveyDetailsDto surveyDetails = homeHandler.setDetails(context,model, request);
		surveyDetails.setDescriotion("Updated "+surveyDetails.getDescriotion());
		String page = homeHandler.submitSurvey(request);
		
		
		return new ModelAndView(page);
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

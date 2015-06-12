package com.esp.handler;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class ExceptionControllerAdvice{
	
	private Logger log = Logger.getLogger(this.getClass().getName());

	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception e,HttpServletRequest req, HttpServletResponse res)
	{
	
		 
		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());

		mav.addObject("type", e.getClass().getTypeName());
		mav.addObject("trace", e.getStackTrace());
		mav.addObject("path", req.getContextPath());
		
		log.error(e.getMessage());
		
		e.printStackTrace();
 
        return mav;
	}

	/*@ExceptionHandler(NullPointerException.class)
	public ModelAndView nullPointerException(Exception e)
	{
		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());

		mav.addObject("type", e.getClass().getTypeName());
		mav.addObject("trace", e.getStackTrace());
		e.printStackTrace();
      
 
        return mav;
	}

	@ExceptionHandler(ParseException.class)
	public ModelAndView parseException(Exception e)
	{
		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());

		mav.addObject("type", e.getClass().getTypeName());
		mav.addObject("trace", e.getStackTrace());
      
		e.printStackTrace();
        return mav;
	}
	@ExceptionHandler(IOException.class)
	public ModelAndView ioException(Exception e)
	{
		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());

		mav.addObject("type", e.getClass().getTypeName());
		mav.addObject("trace", e.getStackTrace());
		e.printStackTrace();
 
        return mav;
	}
	@ExceptionHandler(MessagingException.class)
	public ModelAndView messagingException(Exception e)
	{
		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());

		mav.addObject("type", e.getClass().getTypeName());
		mav.addObject("trace", e.getStackTrace());
		e.printStackTrace();
 
        return mav;
	}
	@ExceptionHandler(AddressException.class)
	public ModelAndView addressException(Exception e)
	{
		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());

		mav.addObject("type", e.getClass().getTypeName());
		mav.addObject("trace", e.getStackTrace());
		e.printStackTrace();
 
        return mav;
	}
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handlerNotFoundException(Exception e)
	{
		ModelAndView mav = new ModelAndView("error");
		e.printStackTrace();
      
 
        return mav;
	}*/
	}
	
	


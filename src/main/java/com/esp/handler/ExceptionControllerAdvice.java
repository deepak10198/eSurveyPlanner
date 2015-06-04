package com.esp.handler;

import java.text.ParseException;

import java.io.IOException;
import com.esp.handler.ResourceNotFoundException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class ExceptionControllerAdvice{
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception e,HttpServletRequest req, HttpServletResponse res)
	{
	
		
		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());
		mav.addObject("type", e.getClass().getTypeName());
		mav.addObject("trace", e.getStackTrace());
		mav.addObject("path", req.getContextPath());
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
	
	


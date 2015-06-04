/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.esp.dao.DAO;

import com.esp.entity.SurveyResponse;
import com.esp.entity.UserMaster;
import com.esp.entity.UserSurveyUrlMapping;

import java.math.BigDecimal;

/**
 *
 * @author ---------
 *  */
@Transactional
@Service(value="UserSurveyURLMappingService")
public class UserSurveyURLMappingService implements GenericService<UserSurveyUrlMapping> {
    
	@Autowired
	@Qualifier("UserSurveyURLMappingDAO")
	private DAO userSurveyURLMappingDAO;
	
	@Override
	public void add(UserSurveyUrlMapping userU) {
		userSurveyURLMappingDAO.save(userU);
		
	}

	@Override
	public UserSurveyUrlMapping fetch(BigDecimal id) {
		 return (UserSurveyUrlMapping)userSurveyURLMappingDAO.findUnique(UserSurveyUrlMapping.class, "id", id);
	}

	@Override
	public List<UserSurveyUrlMapping> fetchByParam(Object obj) {
            
		return  userSurveyURLMappingDAO.findFielEq(UserSurveyUrlMapping.class, "userid.id", obj);
	}
	
	@Override
	public List<UserSurveyUrlMapping> fetchByMultipleParam(Object obj1,Object obj2) {
            
            
		return  userSurveyURLMappingDAO.findUniqueMultiple(UserSurveyUrlMapping.class, "userid.id", "surveyid.id", obj1, obj2);
            
          
	}

	@Override
	public List<UserSurveyUrlMapping> fetchAll() {
		return  userSurveyURLMappingDAO.findAll(UserSurveyUrlMapping.class);
	}

	@Override
	public void update(UserSurveyUrlMapping t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal count(String query) {
		
		return userSurveyURLMappingDAO.Count(query);
	}

	@Override
	public Boolean delete(BigDecimal id) {
		
		return userSurveyURLMappingDAO.delete(UserSurveyUrlMapping.class, "id", id);
	}

	@Override
	public Boolean deleteObj(Object obj) {
		return userSurveyURLMappingDAO.delete(UserSurveyUrlMapping.class, "surveyid.id" , obj);
	}

	@Override
	public List<UserSurveyUrlMapping> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}

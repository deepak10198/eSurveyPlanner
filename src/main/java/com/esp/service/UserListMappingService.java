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
import com.esp.entity.SurveyQuestionMapping;
import com.esp.entity.UserListMapping;
import java.math.BigDecimal;

/**
 *
 * @author -----
 */
@Transactional
@Service(value="UserListMappingService")
public class UserListMappingService implements GenericService<UserListMapping> {
    
	@Autowired
	@Qualifier("UserListMappingDAO")
	private DAO userListMappingDAO;
	
	@Override
	public void add(UserListMapping userMapping) {
		
		userListMappingDAO.save(userMapping);
		
	}

	@Override
	public UserListMapping fetch(BigDecimal id) {
		 return (UserListMapping)userListMappingDAO.findUnique(UserListMapping.class, "id", id);
	}

	@Override
	public List<UserListMapping> fetchByParam(Object obj) {
            
            
		return  userListMappingDAO.findFielEq(UserListMapping.class, "userlistId.id", obj);
            
          
	}

	@Override
	public List<UserListMapping> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserListMapping t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal count(String query) {
		return userListMappingDAO.Count(query);
	}

	@Override
	public Boolean delete(BigDecimal id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteObj(Object obj) {
	
		return userListMappingDAO.delete(UserListMapping.class, "userlistId.id", obj);
		
	}

	@Override
	public List<UserListMapping> fetchByMultipleParam(Object obj1, Object obj2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserListMapping> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
		

	
    
    
}

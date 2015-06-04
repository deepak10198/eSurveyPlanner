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
import com.esp.entity.UserMaster;
import com.esp.entity.UserSurveyUrlMapping;

import java.math.BigDecimal;

/**
 *
 * @author Rakesh.K
 */
@Transactional
@Service(value="UserMasterService")
public class UserMasterService implements GenericService<UserMaster> {
    
	@Autowired
	@Qualifier("UserMasterDAO")
	private DAO userMasterDAO;
	
	@Override
	public void add(UserMaster userMaster) {
		userMasterDAO.save(userMaster);
		
	}

	@Override
	public UserMaster fetch(BigDecimal id) {
		 return (UserMaster)userMasterDAO.findUnique(UserMaster.class, "id", id);
	}

	@Override
	public List<UserMaster> fetchByParam(Object obj) {
            
            
		return  userMasterDAO.findFielEq(UserMaster.class, "loginId", obj);
            
          
	}

	@Override
	public List<UserMaster> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UserMaster t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal count(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(BigDecimal id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteObj(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserMaster> fetchByMultipleParam(Object obj1,
			Object obj2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserMaster> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}

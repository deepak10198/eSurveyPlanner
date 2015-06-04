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
import com.esp.entity.SurveyMaster;
import com.esp.entity.UserList;

import java.math.BigDecimal;

/**
 *
 * @author ---------
 *  */
@Transactional
@Service(value="UserListService")
public class UserListService implements GenericService<UserList> {
    
	@Autowired
	@Qualifier("UserListDAO")
	private DAO userlistDAO;
	
	@Override
	public void add(UserList userl) {
		userlistDAO.save(userl);
		
	}

	@Override
	public UserList fetch(BigDecimal id) {
		 return (UserList)userlistDAO.findUnique(UserList.class, "id", id);
	}

	@Override
	public List<UserList> fetchByParam(Object obj) {
            
            
		// TODO Auto-generated method stub
		//return  userlistDAO.findByUserName(UserList.class, "loginId", obj);
            
            return null;
	}

	@Override
	public List<UserList> fetchAll() {
		return  userlistDAO.findAll(UserList.class);
	}

	@Override
	public void update(UserList t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal count(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(BigDecimal id) {
		
		return userlistDAO.delete(UserList.class, "id", id);
	}

	@Override
	public Boolean deleteObj(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserList> fetchByMultipleParam(Object obj1, Object obj2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserList> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}

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
import com.esp.entity.AnswerTypeMaster;
import com.esp.entity.UserMaster;

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
	public UserMaster fetch(int id) {
		 return (UserMaster)userMasterDAO.findUnique(UserMaster.class, "id", id);
	}

	@Override
	public List<UserMaster> fetchByParam(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserMaster> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
}

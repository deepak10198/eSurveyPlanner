/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.service.impl;

import com.esp.dao.UserMasterDAO;
import com.esp.entity.Usermaster;
import com.esp.service.UserMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.K
 */
@Service
public class UserMasterServiceImpl implements UserMasterService {
    
    @Autowired
    private UserMasterDAO userMasterDAO;
    
    @Transactional
    @Override    
    public void addUserMaster(Usermaster usermaster) {
        
        userMasterDAO.save(usermaster);
        
    }

    @Transactional
    @Override
    public Usermaster getUser(Integer id) {
        
        return userMasterDAO.findUnique(Usermaster.class, "userid", id);
        
        
    }
    
}

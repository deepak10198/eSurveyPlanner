/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.service;

import com.esp.entity.UserMaster;

/**
 *
 * @author Rakesh.K
 */
public interface UserMasterService {
    
    public void addUserMaster(UserMaster usermaster);
    
    public UserMaster getUser(Integer id);
    
    
}

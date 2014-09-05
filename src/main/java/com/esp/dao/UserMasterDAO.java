/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.dao;

import org.springframework.stereotype.Repository;
import com.esp.entity.UserMaster;

/**
 *
 * @author Rakesh.K
 */

@Repository(value="UserMasterDAO")  
public class UserMasterDAO extends GenericDAO<UserMaster> {
    
}
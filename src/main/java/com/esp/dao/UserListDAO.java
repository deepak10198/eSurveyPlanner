/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.dao;

import org.springframework.stereotype.Repository;
import com.esp.entity.UserList;

/**
 *
 * @author ------
 */

@Repository(value="UserListDAO")  
public class UserListDAO extends GenericDAO<UserList> {
    
}
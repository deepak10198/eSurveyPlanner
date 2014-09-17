/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.service;

import com.esp.dao.DAO;
import com.esp.entity.UserRoles;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.K
 */
@Service(value="UserRolesService")
@Transactional
public class UserRolesService implements GenericService<UserRoles>{

    @Autowired
    @Qualifier("UserRolesDAO")        
    DAO userRolesDAO;
    
    @Override
    public void add(UserRoles t) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        userRolesDAO.save(t);
    }

    @Override
    public UserRoles fetch(int id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return (UserRoles) userRolesDAO.findUnique(UserRoles.class, "id", id);
    }
    

    @Override
    public List<UserRoles> fetchByParam(Object obj) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return userRolesDAO.findFielEq(UserRoles.class, "userId.id", obj);
    }

    @Override
    public List<UserRoles> fetchAll() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return userRolesDAO.findAll(UserRoles.class);
    }
    
}

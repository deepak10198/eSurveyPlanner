/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.service;

import com.esp.dao.DAO;
import com.esp.entity.AnswerTypeMaster;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.K
 */
@Service(value ="AnswerTypeMasterService")
@Transactional
public class AnswerTypeMasterService implements GenericService<AnswerTypeMaster> {

    @Autowired
    @Qualifier("AnswerTypeMasterDAO")
    private DAO answerTypeMasterDAO;

    @Override
    public void add(AnswerTypeMaster t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerTypeMaster> fetch(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerTypeMaster> fetchByParam(String ansDesc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerTypeMaster> fetchAll() {

        return answerTypeMasterDAO.findAll(AnswerTypeMaster.class);
    }

}

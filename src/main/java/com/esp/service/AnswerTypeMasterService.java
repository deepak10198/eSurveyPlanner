/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.service;

import com.esp.dao.DAO;
import com.esp.entity.AnswerTypeMaster;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.K
 */
@Transactional
@Service(value ="AnswerTypeMasterService")
public class AnswerTypeMasterService implements GenericService<AnswerTypeMaster> {

    @Autowired
    @Qualifier("AnswerTypeMasterDAO")
    private DAO answerTypeMasterDAO;

    @Override
    public void add(AnswerTypeMaster t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AnswerTypeMaster fetch(BigDecimal id) {
    	 return (AnswerTypeMaster) answerTypeMasterDAO.findUnique(AnswerTypeMaster.class, "id", id);
    }

    @Override
    public List<AnswerTypeMaster> fetchByParam(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnswerTypeMaster> fetchAll() {

        return answerTypeMasterDAO.findAll(AnswerTypeMaster.class);
    }

	@Override
	public void update(AnswerTypeMaster t) {
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
	public List<AnswerTypeMaster> fetchByMultipleParam(Object obj1, Object obj2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerTypeMaster> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

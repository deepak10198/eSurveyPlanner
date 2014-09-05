package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esp.dao.DAO;
import com.esp.entity.AnswerTextMaster;

@Service(value="AnswerTextMasterService")
public class AnswerTextMasterService implements GenericService<AnswerTextMaster> 
{

	@Autowired
	@Qualifier("AnswerDescMasterDAO")
	DAO ansDescMasterDAO ;
	
	@Transactional
	@Override
	public void add(AnswerTextMaster ansDescMaster){
		ansDescMasterDAO.save(ansDescMaster);
		
	}

	@Transactional
	@Override
	public List<AnswerTextMaster> fetch(int ansDescID) {
		return ansDescMasterDAO.findFielEq(AnswerTextMaster.class, "ansdescid",ansDescID );
		
	}
	
	@Transactional
	@Override
	public List<AnswerTextMaster> fetchByParam(String ansDesc) {
		return ansDescMasterDAO.findFielEq(AnswerTextMaster.class, "ansdescription",ansDesc );
		
	}
    @Transactional
    @Override
    public List<AnswerTextMaster> fetchAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}

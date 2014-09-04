package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esp.dao.DAO;
import com.esp.entity.Answerdescriptionmaster;

@Service(value="AnswerDescMasterService")
public class AnswerDescMasterService implements GenericService<Answerdescriptionmaster> 
{

	@Autowired
	@Qualifier("AnswerDescMasterDAO")
	DAO ansDescMasterDAO ;
	
	@Transactional
	@Override
	public void add(Answerdescriptionmaster ansDescMaster){
		ansDescMasterDAO.save(ansDescMaster);
		
	}

	@Transactional
	@Override
	public List<Answerdescriptionmaster> fetch(int ansDescID) {
		return ansDescMasterDAO.findFielEq(Answerdescriptionmaster.class, "ansdescid",ansDescID );
		
	}
	
	@Transactional
	@Override
	public List<Answerdescriptionmaster> fetchByParam(String ansDesc) {
		return ansDescMasterDAO.findFielEq(Answerdescriptionmaster.class, "ansdescription",ansDesc );
		
	}
    @Transactional
    @Override
    public List<Answerdescriptionmaster> fetchAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}

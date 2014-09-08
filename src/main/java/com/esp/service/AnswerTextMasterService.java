package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.esp.dao.DAO;
import com.esp.entity.AnswerTextMaster;
import java.math.BigDecimal;

@Transactional
@Service(value="AnswerTextMasterService")
public class AnswerTextMasterService implements GenericService<AnswerTextMaster> 
{

	@Autowired
	@Qualifier("AnswerTextMasterDAO")
	DAO ansTextMasterDAO ;
	
	@Transactional
	@Override
	public void add(AnswerTextMaster ansTextMaster){
		ansTextMasterDAO.save(ansTextMaster);
		
	}

	@Transactional
	@Override
	public AnswerTextMaster fetch(int id) {
		return (AnswerTextMaster) ansTextMasterDAO.findFielEq(AnswerTextMaster.class, "id",id );
		
	}
	
	@Transactional
	@Override
	public List<AnswerTextMaster> fetchByParam(Object ansText) {
		return ansTextMasterDAO.findFielEq(AnswerTextMaster.class, "ansText", ansText );
		
	}
    @Transactional
    @Override
    public List<AnswerTextMaster> fetchAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
}

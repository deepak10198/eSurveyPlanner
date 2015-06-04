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
	
	@Override
	public void add(AnswerTextMaster ansTextMaster){
		ansTextMasterDAO.save(ansTextMaster);
		
	}

	@Override
	public AnswerTextMaster fetch(BigDecimal id) {
		return (AnswerTextMaster) ansTextMasterDAO.findUnique(AnswerTextMaster.class, "id",id );
		
	}
	
	@Override
	public List<AnswerTextMaster> fetchByParam(Object ansText) {
		return ansTextMasterDAO.findFielEq(AnswerTextMaster.class, "ansText", ansText );
		
	}
    
	@Override
    public List<AnswerTextMaster> fetchAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void update(AnswerTextMaster t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal count(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(BigDecimal id) {
		
		return ansTextMasterDAO.delete(AnswerTextMaster.class, "id", id);
	}

	
	@Override
	public Boolean deleteObj(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerTextMaster> fetchByMultipleParam(Object obj1, Object obj2) {
		return ansTextMasterDAO.findUniqueMultiple(AnswerTextMaster.class, "ansText", "otherInfo", obj1, obj2);
	}

	@Override
	public List<AnswerTextMaster> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}

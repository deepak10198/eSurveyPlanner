package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esp.dao.DAO;
import com.esp.entity.AnswerMaster;
import java.math.BigDecimal;


@Transactional
@Service(value="AnswerMasterService")
public class AnswerMasterService implements GenericService<AnswerMaster> {
	

	@Autowired
	@Qualifier("AnswerMasterDAO") 
	DAO answerMasterDAO;
	

	@Override
	public void add(AnswerMaster ansMaster) {
		answerMasterDAO.save(ansMaster);
		
	}

	@Override
	public AnswerMaster fetch(BigDecimal id) {
		return (AnswerMaster) answerMasterDAO.findUnique(AnswerMaster.class, "id", id);
	}

	@Override
	public List<AnswerMaster> fetchByParam(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerMaster> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AnswerMaster t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal count(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(BigDecimal id) {
		return answerMasterDAO.delete(AnswerMaster.class, "id", id);
	}

	

	@Override
	public Boolean deleteObj(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerMaster> fetchByMultipleParam(Object obj1, Object obj2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerMaster> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
}

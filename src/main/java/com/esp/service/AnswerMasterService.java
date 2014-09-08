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
		return (AnswerMaster) answerMasterDAO.findFielEq(AnswerMaster.class, "id", id);
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
}

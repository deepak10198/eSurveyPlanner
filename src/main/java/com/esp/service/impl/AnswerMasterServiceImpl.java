package com.esp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esp.dao.AnswerMasterDAO;
import com.esp.dao.DAO;
import com.esp.dao.GenericDAO;
//import com.esp.dao.impl.AnswerMasterDAOImpl;
import com.esp.entity.Answermaster;
import com.esp.service.AnswerMasterService;

@Service
@Transactional
public class AnswerMasterServiceImpl implements AnswerMasterService {

	
	@Autowired
	@Qualifier("AnswerMasterDAO") 
	DAO answerMasterDAO;
	
	@Override
	public void addAnswerMaster(Answermaster answerMaster) {
		// TODO Auto-generated method stub
		answerMasterDAO.save(answerMaster);
		
	}

	@Override
	public List<Answermaster> listAnswerMaster(int ansId) {
		// TODO Auto-generated method stub
		return answerMasterDAO.findFielEq(Answermaster.class, "ansid", ansId);
		
	}

}

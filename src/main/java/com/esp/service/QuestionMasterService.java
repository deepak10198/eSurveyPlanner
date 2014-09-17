package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esp.dao.DAO;
import com.esp.entity.AnswerTextMaster;
import com.esp.entity.QuestionMaster;
import java.math.BigDecimal;

@Transactional
@Service(value="QuestionMasterService")
public class QuestionMasterService implements GenericService<QuestionMaster> {

	@Autowired
	@Qualifier("QuestionMasterDAO") 
	DAO questionMasterDAO;
	
	@Override
	public void add(QuestionMaster questionMaster) {
		questionMasterDAO.save(questionMaster);
		
	}

	@Override
	public QuestionMaster fetch(BigDecimal id) {
		return (QuestionMaster) questionMasterDAO.findUnique(QuestionMaster.class, "id", id);
	}

	@Override
	public List<QuestionMaster> fetchByParam(Object quesText) {
		// TODO Auto-generated method stub
		
		return questionMasterDAO.findFielEq(QuestionMaster.class, "questionText", quesText);
	}

	@Override
	public List<QuestionMaster> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

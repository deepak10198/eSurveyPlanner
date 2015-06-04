package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esp.dao.DAO;
import com.esp.entity.QuestionAnswerMapping;
import java.math.BigDecimal;

@Transactional
@Service(value="QuestionAnswerMappingService")
public class QuestionAnswerMappingService implements GenericService<QuestionAnswerMapping> {

	@Autowired
	@Qualifier("QuestionAnswerMappingDAO")
	DAO questionAnswerMappingDAO;
	
	
	@Override
	public void add(QuestionAnswerMapping questionAnswerMapping) {
		questionAnswerMappingDAO.save(questionAnswerMapping);
		
	}

	@Override
	public QuestionAnswerMapping fetch(BigDecimal id) {
		// TODO Auto-generated method stub
		return (QuestionAnswerMapping) questionAnswerMappingDAO.findUnique(QuestionAnswerMapping.class, "id", id);
	}

	@Override
	public List<QuestionAnswerMapping> fetchByParam(Object obj) {
		
		return (List<QuestionAnswerMapping>) questionAnswerMappingDAO.findFielEq(QuestionAnswerMapping.class, "questionId.id", obj);
	}

	@Override
	public List<QuestionAnswerMapping> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(QuestionAnswerMapping t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal count(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(BigDecimal id) {
		return questionAnswerMappingDAO.delete(QuestionAnswerMapping.class, "id", id);
	}

	

	@Override
	public Boolean deleteObj(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionAnswerMapping> fetchByMultipleParam(Object obj1,
			Object obj2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionAnswerMapping> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

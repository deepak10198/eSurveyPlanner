package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esp.dao.DAO;
import com.esp.entity.SurveyQuestionMapping;

@Transactional
@Service(value="SurveyQuestionMappingService")
public class SurveyQuestionMappingService implements GenericService<SurveyQuestionMapping> {

	
	@Autowired
	@Qualifier("SurveyQuestionMappingDAO") 
	DAO surveyQuestionMappingDAO;
	
	@Override
	public void add(SurveyQuestionMapping surveyQuestionMapping) {
		surveyQuestionMappingDAO.save(surveyQuestionMapping);
		
	}

	@Override
	public SurveyQuestionMapping fetch(int id) {
		// TODO Auto-generated method stub
		return  (SurveyQuestionMapping) surveyQuestionMappingDAO.findUnique(SurveyQuestionMapping.class, "id", id);
	}

	@Override
	public List<SurveyQuestionMapping> fetchByParam(Object obj) {
		// TODO Auto-generated method stub
		return surveyQuestionMappingDAO.findFielEq(SurveyQuestionMapping.class, "surveyId", obj);
	}

	@Override
	public List<SurveyQuestionMapping> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

}

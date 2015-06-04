package com.esp.service;

import java.math.BigDecimal;
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
	public SurveyQuestionMapping fetch(BigDecimal id) {
		// TODO Auto-generated method stub
		return  (SurveyQuestionMapping) surveyQuestionMappingDAO.findUnique(SurveyQuestionMapping.class, "id", id);
	}

	/*@Override
	public List<SurveyQuestionMapping> fetchByParam(Object obj) {
		// TODO Auto-generated method stub
		return surveyQuestionMappingDAO.findFielEq(SurveyQuestionMapping.class, "surveyId", obj );
	}
*/
	
	@Override
	public List<SurveyQuestionMapping> fetchByParam(Object obj) {
		// TODO Auto-generated method stub
		return surveyQuestionMappingDAO.findFielEq(SurveyQuestionMapping.class, "surveyId.id", obj );
	}

	
	@Override
	public List<SurveyQuestionMapping> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SurveyQuestionMapping surveyQuestionMapping) {
		surveyQuestionMappingDAO.update(surveyQuestionMapping);
		
	}

	@Override
	public BigDecimal count(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteObj(Object obj) {
		
		return surveyQuestionMappingDAO.delete(SurveyQuestionMapping.class, "surveyId.id", obj);
	}

	@Override
	public Boolean delete(BigDecimal id) {
		
		return surveyQuestionMappingDAO.delete(SurveyQuestionMapping.class, "id", id);
	}

	@Override
	public List<SurveyQuestionMapping> fetchByMultipleParam(Object obj1,Object obj2) {
		return (List<SurveyQuestionMapping>)surveyQuestionMappingDAO.findUniqueMultiple(SurveyQuestionMapping.class, "questionAnsId.id","surveyId.id",obj1,obj2);
	}

	@Override
	public List<SurveyQuestionMapping> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}

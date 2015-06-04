/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.service;

import com.esp.dao.DAO;
import com.esp.entity.QuestionAnswerMapping;
import com.esp.entity.SurveyQuestionMapping;
import com.esp.entity.SurveyResponse;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.K
 */
@Service(value = "SurveyResponseService")
@Transactional
public class SurveyResponseService implements GenericService<SurveyResponse> {

    @Autowired
    @Qualifier("SurveyResponseDAO")
    DAO<SurveyResponse> surveyResponseDAO;

    @Override
    public void add(SurveyResponse t) {
       
        surveyResponseDAO.save(t);
    
    }
    
    @Override
    public SurveyResponse fetch(BigDecimal id) {
        
        return surveyResponseDAO.findUnique(SurveyResponse.class, "id", id);
        
    }
    
    @Override
    public BigDecimal count(String query)
    {
    	return surveyResponseDAO.Count(query);
    }
    
    @Override
    public List<SurveyResponse> fetchUser(Object obj) {
        
        return  (List<SurveyResponse>) surveyResponseDAO.findFielEq(SurveyResponse.class, "surveyId.id", obj);
        
    }


    @Override
    public List<SurveyResponse> fetchByParam(Object obj) {
        
    	return (List<SurveyResponse>) surveyResponseDAO.findFielEq(SurveyResponse.class, "quesAnsMappingId", obj);
    }

    @Override
    public List<SurveyResponse> fetchAll() {
        
        return surveyResponseDAO.findAll(SurveyResponse.class);
    }

	@Override
	public void update(SurveyResponse t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean delete(BigDecimal id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteObj(Object obj) {
		
		return surveyResponseDAO.delete(SurveyResponse.class, "surveyId.id" , obj);
		
	}

	@Override
	public List<SurveyResponse> fetchByMultipleParam(Object obj1, Object obj2) {
		
		return (List<SurveyResponse>) surveyResponseDAO.findUniqueMultiple(SurveyResponse.class, "ansTextId.id","quesAnsMappingId.id", obj1,obj2);
		
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esp.dao.DAO;
import com.esp.entity.AnswerTypeMaster;
import com.esp.entity.SurveyTypeMaster;
import java.math.BigDecimal;

/**
 *
 * @author Rakesh.k
 */
@Transactional
@Service(value="SurveyTypeMasterService")
public class SurveyTypeMasterService implements GenericService<SurveyTypeMaster> {
    
    @Autowired
    @Qualifier("SurveyTypeMasterDAO")
    private DAO surveyTypeMasterDAO;
	
	@Override
	public void add(SurveyTypeMaster t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SurveyTypeMaster fetch(BigDecimal id) {
		return (SurveyTypeMaster) surveyTypeMasterDAO.findUnique(SurveyTypeMaster.class, "id", id);
	}

	@Override
	public List<SurveyTypeMaster> fetchByParam(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyTypeMaster> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}
    
}

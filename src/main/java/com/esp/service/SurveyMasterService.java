/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.service;

import com.esp.dao.DAO;
import com.esp.entity.AnswerTextMaster;
import com.esp.entity.SurveyMaster;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.k
 */
@Transactional
@Service(value="SurveyMasterService")
public class SurveyMasterService implements GenericService<SurveyMaster> {
    
    @Autowired
    @Qualifier("SurveyMasterDAO")
    private DAO surveyMasterDAO;

	@Override
	public void add(SurveyMaster surveyMaster) {
        surveyMasterDAO.save(surveyMaster);
		
	}

	@Override
	public SurveyMaster fetch(int id) {

        return (SurveyMaster) surveyMasterDAO.findFielEq(SurveyMaster.class, "surveyId", id);
	}

	@Override
	public List<SurveyMaster> fetchByParam(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyMaster> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}
    
}

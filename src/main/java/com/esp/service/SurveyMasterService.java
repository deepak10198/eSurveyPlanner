/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.service;

import com.esp.dao.DAO;
import com.esp.entity.SurveyMaster;
import java.math.BigDecimal;
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
	public void update(SurveyMaster surveyMaster){
		
		surveyMasterDAO.update(surveyMaster);
		
	}
	/*@Override
	public void delete(BigDecimal id){
		surveyMasterDAO.delete(surveyMaster.class, "id", id);
	}
    */
	@Override
	public SurveyMaster fetch(BigDecimal id) {

        return (SurveyMaster) surveyMasterDAO.findUnique(SurveyMaster.class, "id", id);
	}

	@Override
	public List<SurveyMaster> fetchByParam(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyMaster> fetchAll() {
		// TODO Auto-generated method stub
		return  surveyMasterDAO.findAll(SurveyMaster.class);
	}
	
	public Boolean delete(BigDecimal id){
		
		return surveyMasterDAO.delete(SurveyMaster.class, "id", id);
	}

	@Override
	public BigDecimal count(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Boolean deleteObj(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyMaster> fetchByMultipleParam(Object obj1, Object obj2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyMaster> fetchUser(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

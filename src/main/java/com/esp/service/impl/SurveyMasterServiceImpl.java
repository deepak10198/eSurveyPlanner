/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.service.impl;

import com.esp.service.SurveyMasterService;
import com.esp.dao.SurveyMasterDAO;
import com.esp.entity.Surveymaster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.k
 */
@Service
public class SurveyMasterServiceImpl implements SurveyMasterService {

    @Autowired
    private SurveyMasterDAO surveyMasterDAO;//= new SurveyMasterDAOImpl();

    @Transactional
    public void addSurvey(Surveymaster surveyMaster) {
        surveyMasterDAO.save(surveyMaster);
    }

}

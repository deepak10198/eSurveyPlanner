/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.service.impl;

import com.esp.dao.DAO;
import com.esp.dao.SurveyMasterDAO;
import com.esp.entity.Surveymaster;
import com.esp.service.SurveyMasterService;
import com.esp.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rakesh.k
 */
@Service
public class SurveyMasterServiceImpl implements SurveyMasterService {

    @Autowired
    @Qualifier("SurveyMasterDAO")
    private DAO surveyMasterDAO;

    @Transactional
    @Override
    public void addSurvey(Surveymaster surveyMaster) {
        surveyMasterDAO.save(surveyMaster);
    }

    @Transactional
    @Override
    public List<Surveymaster> listSurveys(Integer userID) {

        return surveyMasterDAO.findFielEq(Surveymaster.class, "usermasterByCreatedbyid.userid", userID);
    }

}

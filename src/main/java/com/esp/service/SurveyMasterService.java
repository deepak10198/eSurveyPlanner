/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.service;

import com.esp.entity.SurveyMaster;
import java.util.List;

/**
 *
 * @author Rakesh.k
 */
public interface SurveyMasterService {
    
    public void addSurvey(SurveyMaster surveyMaster);
    
    public List<SurveyMaster> listSurveys(Integer userID);
    
}

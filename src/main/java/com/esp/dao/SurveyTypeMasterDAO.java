/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.dao;

import org.springframework.stereotype.Repository;

//import com.esp.dao.impl.SurveyTypeMasterDAO;
import com.esp.entity.Surveymaster;
import com.esp.entity.Surveytypemaster;

/**
 *
 * @author Rakesh.k
 */
@Repository(value="SurveyTypeMasterDAO")
public class SurveyTypeMasterDAO extends GenericDAO<Surveytypemaster> {
    
}
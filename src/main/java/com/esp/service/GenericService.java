package com.esp.service;

import java.math.BigDecimal;
import java.util.List;

import com.esp.entity.SurveyResponse;
import com.esp.entity.UserSurveyUrlMapping;


public interface GenericService<T> {
	
public void add(T t);

public T fetch(BigDecimal id);

public List<T> fetchByParam(Object obj); 

public List<T> fetchAll();

public void update(T t);

public BigDecimal count(String query);

public Boolean delete(BigDecimal id);

public Boolean deleteObj(Object obj);

public List<T> fetchByMultipleParam(Object obj1, Object obj2);

public List<T> fetchUser(Object obj);



}

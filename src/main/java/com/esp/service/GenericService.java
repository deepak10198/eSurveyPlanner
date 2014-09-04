package com.esp.service;

import java.util.List;


public interface GenericService<T> {
	
public void add(T t);

public List<T> fetch(int id); 

//public List<T> fetchByParam(Object obj);

public List<T> fetchByParam(String ansDesc); 

public List<T> fetchAll(); 


}

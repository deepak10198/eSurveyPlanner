package com.esp.service;

import java.util.List;


public interface GenericService<T> {
	
public void add(T t);

public T fetch(int id); 

public List<T> fetchByParam(Object obj); 

public List<T> fetchAll(); 





}

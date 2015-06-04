/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.dao;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Rakesh.K
 * @param <T>
 */
public interface DAO<T> {

    public void save(T t);

    public void update(T t);

    public List<T> findAll(Class<T> entity);

    public boolean delete(T t);

    public boolean delete(Class<T> type, String propertyName, Object value);

    public T findUnique(Class<T> entity, String propertyName, Object value);

    public List<T> findFielEq(Class<T> entity, String propertyName, Object value);

    public T find(Class<T> entity, String propertyName, Object value);

	public BigDecimal Count(String query);

	public List<T> findUniqueMultiple(Class<T> entity, String property1, String Property2, Object value1, Object value2);

    
    
}

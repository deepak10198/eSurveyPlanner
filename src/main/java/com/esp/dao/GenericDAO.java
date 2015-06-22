/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.dao;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Rakesh.K
 */
public abstract class GenericDAO<T> implements DAO<T> {

    private static SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(T t) {

        sessionFactory.getCurrentSession().save(t);

    }
   
    @Override
    public void update(T t) {
    	        sessionFactory.getCurrentSession().update(t);
    }

    @Override
    public List<T> findAll(Class<T> entity) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        return criteria.list();
    }

    @Override
    public boolean delete(T t) {
        if (t != null) {
            sessionFactory.getCurrentSession().delete(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Class<T> type, String propertyName, Object value) {

        T t = findUnique(type, propertyName, value);
        if (t != null) {
            sessionFactory.getCurrentSession().delete(t);
            return true;
        }
        return false;
    }

    @Override
    public T findUnique(Class<T> entity, String propertyName, Object value) {
        T t = null;
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        criteria.add(Restrictions.eq(propertyName, value));
        t = (T) criteria.list().get(0);
        return t;
    }
    
    @Override
    public List<T> findUniqueMultiple(Class<T> entity, String property1, String Property2, Object value1, Object value2){
    	
    	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entity);
    	Criterion condition1 = Restrictions.eq(property1, value1);
        Criterion condition2 = Restrictions.eq(Property2, value2);
        LogicalExpression andExp = Restrictions.and(condition1, condition2);
        System.out.println("\n "+andExp);
         //criteria.add(andExp);
       criteria.addOrder(Order.asc("id"));
         System.out.println("\n "+ criteria.add(andExp));
        return criteria.list();
    }
    
    @Override
    public BigDecimal Count(String query) {
    	
    	
    	BigDecimal t;
        SQLQuery qry = sessionFactory.getCurrentSession().createSQLQuery(query);
        t =	(BigDecimal) qry.list().get(0);
        return t;
    }
    
   
    @Override
    public List<T> findFielEq(Class<T> entity, String propertyName, Object value) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entity);
        criteria.add(Restrictions.eq(propertyName, value));
        criteria.addOrder(Order.asc("id"));
        return criteria.list();
    }

    @Override
    public T find(Class<T> entity, String propertyName, Object value) {
        System.out.println(">>find");
        T t = null;
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(entity);
        System.out.println(">>criteria :" + criteria);
        criteria.add(Restrictions.eq(propertyName, value));
        System.out.println("-----");
        t = (T) criteria.list().get(0);
        System.out.println(">>object list :" + criteria.list().size());
        System.out.println(">>t is " + t);
        //sessionFactory.getCurrentSession().close();
        if (session != null) {
            session.close();
        }

        return t;

    }
}

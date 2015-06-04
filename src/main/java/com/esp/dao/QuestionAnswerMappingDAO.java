package com.esp.dao;

import org.springframework.stereotype.Repository;

import com.esp.entity.QuestionAnswerMapping;

@Repository(value="QuestionAnswerMappingDAO")
public class QuestionAnswerMappingDAO extends GenericDAO<QuestionAnswerMapping> {

   /* public QuestionAnswerMapping findMapping(QuestionAnswerMapping entity, String propertyName, Object value) {
    	QuestionAnswerMapping t = null;
        Criteria criteria = super.sessionFactory.getCurrentSession().createCriteria( "select "  );
        criteria.add(Restrictions.eq(propertyName, new BigDecimal((Integer)value)));
        t = (QuestionAnswerMapping) criteria.list().get(0);
        return t;
    }
	*/
}

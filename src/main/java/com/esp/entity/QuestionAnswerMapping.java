/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rakesh.K
 */
@Entity
@Table(name = "QUESTION_ANSWER_MAPPING", catalog = "", schema = "SURVEY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"QUESTION_ID", "ANSWER_ID", "ANS_TYPE_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionAnswerMapping.findAll", query = "SELECT q FROM QuestionAnswerMapping q"),
    @NamedQuery(name = "QuestionAnswerMapping.findById", query = "SELECT q FROM QuestionAnswerMapping q WHERE q.id = :id"),
    @NamedQuery(name = "QuestionAnswerMapping.findByCreatedDate", query = "SELECT q FROM QuestionAnswerMapping q WHERE q.createdDate = :createdDate"),
    @NamedQuery(name = "QuestionAnswerMapping.findByLastModifiedDate", query = "SELECT q FROM QuestionAnswerMapping q WHERE q.lastModifiedDate = :lastModifiedDate")})
public class QuestionAnswerMapping implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ID")
    @ManyToOne
    private AnswerMaster answerId;
    @JoinColumn(name = "ANS_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTypeMaster ansTypeId;
    @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ID")
    @ManyToOne
    private QuestionMaster questionId;
    @JoinColumn(name = "LAST_MODIFIED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster lastModifiedById;
    @JoinColumn(name = "CREATED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster createdById;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionAnsId")
    private Set<SurveyQuestionMapping> surveyQuestionMappingSet;

    public QuestionAnswerMapping() {
    }

    public QuestionAnswerMapping(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public AnswerMaster getAnswerId() {
        return answerId;
    }

    public void setAnswerId(AnswerMaster answerId) {
        this.answerId = answerId;
    }

    public AnswerTypeMaster getAnsTypeId() {
        return ansTypeId;
    }

    public void setAnsTypeId(AnswerTypeMaster ansTypeId) {
        this.ansTypeId = ansTypeId;
    }

    public QuestionMaster getQuestionId() {
        return questionId;
    }

    public void setQuestionId(QuestionMaster questionId) {
        this.questionId = questionId;
    }

    public UserMaster getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(UserMaster lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public UserMaster getCreatedById() {
        return createdById;
    }

    public void setCreatedById(UserMaster createdById) {
        this.createdById = createdById;
    }

    @XmlTransient
    public Set<SurveyQuestionMapping> getSurveyQuestionMappingSet() {
        return surveyQuestionMappingSet;
    }

    public void setSurveyQuestionMappingSet(Set<SurveyQuestionMapping> surveyQuestionMappingSet) {
        this.surveyQuestionMappingSet = surveyQuestionMappingSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionAnswerMapping)) {
            return false;
        }
        QuestionAnswerMapping other = (QuestionAnswerMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.QuestionAnswerMapping[ id=" + id + " ]";
    }
    
}

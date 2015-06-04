/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rakesh.K
 */
@Entity
@Table(name = "SURVEY_QUESTION_MAPPING", catalog = "", schema = "SURVEY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SURVEY_ID", "QUESTION_ANS_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyQuestionMapping.findAll", query = "SELECT s FROM SurveyQuestionMapping s"),
    @NamedQuery(name = "SurveyQuestionMapping.findById", query = "SELECT s FROM SurveyQuestionMapping s WHERE s.id = :id"),
    @NamedQuery(name = "SurveyQuestionMapping.findByCreationDate", query = "SELECT s FROM SurveyQuestionMapping s WHERE s.creationDate = :creationDate"),
    @NamedQuery(name = "SurveyQuestionMapping.findByLastModifiedDate", query = "SELECT s FROM SurveyQuestionMapping s WHERE s.lastModifiedDate = :lastModifiedDate")})
public class SurveyQuestionMapping implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id

    @SequenceGenerator(name = "SEQ_SURVEY_QUESTION_MAPPING", sequenceName = "SEQ_SURVEY_QUESTION_MAPPING")
    @GeneratedValue(generator = "SEQ_SURVEY_QUESTION_MAPPING", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @JoinColumn(name = "QUESTION_ANS_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private QuestionAnswerMapping questionAnsId;
    @JoinColumn(name = "SURVEY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private SurveyMaster surveyId;
    @JoinColumn(name = "LAST_MODIFIED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster lastModifiedById;
    @JoinColumn(name = "CREATED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster createdById;
    @Column(name = "MANDATORY")
    private String mandatory;

    public SurveyQuestionMapping() {
    }

    public SurveyQuestionMapping(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public QuestionAnswerMapping getQuestionAnsId() {
        return questionAnsId;
    }

    public void setQuestionAnsId(QuestionAnswerMapping questionAnsId) {
        this.questionAnsId = questionAnsId;
    }

    public SurveyMaster getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(SurveyMaster surveyId) {
        this.surveyId = surveyId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SurveyQuestionMapping)) {
            return false;
        }
        SurveyQuestionMapping other = (SurveyQuestionMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.SurveyQuestionMapping[ id=" + id + " ]";
    }

}

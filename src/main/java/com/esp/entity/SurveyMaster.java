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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "SURVEY_MASTER", catalog = "", schema = "SURVEY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"SURVEY_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyMaster.findAll", query = "SELECT s FROM SurveyMaster s"),
    @NamedQuery(name = "SurveyMaster.findById", query = "SELECT s FROM SurveyMaster s WHERE s.id = :id"),
    @NamedQuery(name = "SurveyMaster.findBySurveyCategory", query = "SELECT s FROM SurveyMaster s WHERE s.surveyCategory = :surveyCategory"),
    @NamedQuery(name = "SurveyMaster.findByCreatedDate", query = "SELECT s FROM SurveyMaster s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "SurveyMaster.findByLastModifiedDate", query = "SELECT s FROM SurveyMaster s WHERE s.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "SurveyMaster.findByStartDate", query = "SELECT s FROM SurveyMaster s WHERE s.startDate = :startDate"),
    @NamedQuery(name = "SurveyMaster.findByEndDate", query = "SELECT s FROM SurveyMaster s WHERE s.endDate = :endDate"),
    @NamedQuery(name = "SurveyMaster.findBySurveyName", query = "SELECT s FROM SurveyMaster s WHERE s.surveyName = :surveyName"),
    @NamedQuery(name = "SurveyMaster.findBySurveyDesc", query = "SELECT s FROM SurveyMaster s WHERE s.surveyDesc = :surveyDesc"),
    @NamedQuery(name = "SurveyMaster.findBySurveyId", query = "SELECT s FROM SurveyMaster s WHERE s.surveyId = :surveyId")})
public class SurveyMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name ="SEQ_SURVEY_MASTER" ,sequenceName ="SEQ_SURVEY_MASTER")
    @GeneratedValue(generator = "SEQ_SURVEY_MASTER",strategy =GenerationType.SEQUENCE )
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "SURVEY_CATEGORY", length = 200)
    private String surveyCategory;
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "SURVEY_NAME", length = 200)
    private String surveyName;
    @Column(name = "SURVEY_DESC", length = 250)
    private String surveyDesc;
    @Column(name = "SURVEY_ID", length = 20)
    private String surveyId;
    @JoinColumn(name = "SURVEY_TYPE_ID", referencedColumnName = "ID")
    @ManyToOne
    private SurveyTypeMaster surveyTypeId;
    @JoinColumn(name = "LAST_MODIFIED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster lastModifiedById;
    @JoinColumn(name = "CREATED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster createdById;
    @OneToMany(mappedBy = "surveyId")
    private Set<SurveyResponse> surveyResponseSet;

    public SurveyMaster() {
    }

    public SurveyMaster(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSurveyCategory() {
        return surveyCategory;
    }

    public void setSurveyCategory(String surveyCategory) {
        this.surveyCategory = surveyCategory;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName;
    }

    public String getSurveyDesc() {
        return surveyDesc;
    }

    public void setSurveyDesc(String surveyDesc) {
        this.surveyDesc = surveyDesc;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public SurveyTypeMaster getSurveyTypeId() {
        return surveyTypeId;
    }

    public void setSurveyTypeId(SurveyTypeMaster surveyTypeId) {
        this.surveyTypeId = surveyTypeId;
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
    public Set<SurveyResponse> getSurveyResponseSet() {
        return surveyResponseSet;
    }

    public void setSurveyResponseSet(Set<SurveyResponse> surveyResponseSet) {
        this.surveyResponseSet = surveyResponseSet;
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
        if (!(object instanceof SurveyMaster)) {
            return false;
        }
        SurveyMaster other = (SurveyMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.SurveyMaster[ id=" + id + " ]";
    }
    
}

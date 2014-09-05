/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rakesh.K
 */
@Entity
@Table(name = "SURVEY_TYPE_MASTER", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyTypeMaster.findAll", query = "SELECT s FROM SurveyTypeMaster s"),
    @NamedQuery(name = "SurveyTypeMaster.findById", query = "SELECT s FROM SurveyTypeMaster s WHERE s.id = :id"),
    @NamedQuery(name = "SurveyTypeMaster.findBySurveyTypeText", query = "SELECT s FROM SurveyTypeMaster s WHERE s.surveyTypeText = :surveyTypeText")})
public class SurveyTypeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "SURVEY_TYPE_TEXT", length = 200)
    private String surveyTypeText;
    @OneToMany(mappedBy = "surveyTypeId")
    private Set<SurveyMaster> surveyMasterSet;

    public SurveyTypeMaster() {
    }

    public SurveyTypeMaster(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSurveyTypeText() {
        return surveyTypeText;
    }

    public void setSurveyTypeText(String surveyTypeText) {
        this.surveyTypeText = surveyTypeText;
    }

    @XmlTransient
    public Set<SurveyMaster> getSurveyMasterSet() {
        return surveyMasterSet;
    }

    public void setSurveyMasterSet(Set<SurveyMaster> surveyMasterSet) {
        this.surveyMasterSet = surveyMasterSet;
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
        if (!(object instanceof SurveyTypeMaster)) {
            return false;
        }
        SurveyTypeMaster other = (SurveyTypeMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.SurveyTypeMaster[ id=" + id + " ]";
    }
    
}

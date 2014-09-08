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
@Table(name = "USER_SURVEY_URL_MAPPING", catalog = "", schema = "SURVEY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USERID", "SURVEYID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserSurveyUrlMapping.findAll", query = "SELECT u FROM UserSurveyUrlMapping u"),
    @NamedQuery(name = "UserSurveyUrlMapping.findByUrlstring", query = "SELECT u FROM UserSurveyUrlMapping u WHERE u.urlstring = :urlstring"),
    @NamedQuery(name = "UserSurveyUrlMapping.findByCreationDate", query = "SELECT u FROM UserSurveyUrlMapping u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "UserSurveyUrlMapping.findByLastModifiedDate", query = "SELECT u FROM UserSurveyUrlMapping u WHERE u.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "UserSurveyUrlMapping.findById", query = "SELECT u FROM UserSurveyUrlMapping u WHERE u.id = :id")})
public class UserSurveyUrlMapping implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String urlstring;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @SequenceGenerator(name ="SEQ_USER_SURVEY_URL_MAPPING" ,sequenceName ="SEQ_USER_SURVEY_URL_MAPPING")
    @GeneratedValue(generator = "SEQ_USER_SURVEY_URL_MAPPING",strategy =GenerationType.SEQUENCE )
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @JoinColumn(name = "SURVEYID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private SurveyMaster surveyid;
    @JoinColumn(name = "USERID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster userid;
    @JoinColumn(name = "CREATED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster createdById;
    @JoinColumn(name = "LAST_MODIFIED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster lastModifiedById;

    public UserSurveyUrlMapping() {
    }

    public UserSurveyUrlMapping(BigDecimal id) {
        this.id = id;
    }

    public UserSurveyUrlMapping(BigDecimal id, String urlstring) {
        this.id = id;
        this.urlstring = urlstring;
    }

    public String getUrlstring() {
        return urlstring;
    }

    public void setUrlstring(String urlstring) {
        this.urlstring = urlstring;
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

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public SurveyMaster getSurveyid() {
        return surveyid;
    }

    public void setSurveyid(SurveyMaster surveyid) {
        this.surveyid = surveyid;
    }

    public UserMaster getUserid() {
        return userid;
    }

    public void setUserid(UserMaster userid) {
        this.userid = userid;
    }

    public UserMaster getCreatedById() {
        return createdById;
    }

    public void setCreatedById(UserMaster createdById) {
        this.createdById = createdById;
    }

    public UserMaster getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(UserMaster lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
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
        if (!(object instanceof UserSurveyUrlMapping)) {
            return false;
        }
        UserSurveyUrlMapping other = (UserSurveyUrlMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.UserSurveyUrlMapping[ id=" + id + " ]";
    }
    
}

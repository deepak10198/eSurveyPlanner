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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rakesh.K
 */
@Entity
@Table(name = "QUESTION_MASTER", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QuestionMaster.findAll", query = "SELECT q FROM QuestionMaster q"),
    @NamedQuery(name = "QuestionMaster.findById", query = "SELECT q FROM QuestionMaster q WHERE q.id = :id"),
    @NamedQuery(name = "QuestionMaster.findByQuestionText", query = "SELECT q FROM QuestionMaster q WHERE q.questionText = :questionText"),
    @NamedQuery(name = "QuestionMaster.findByCreationDate", query = "SELECT q FROM QuestionMaster q WHERE q.creationDate = :creationDate"),
    @NamedQuery(name = "QuestionMaster.findByLastModifiedDate", query = "SELECT q FROM QuestionMaster q WHERE q.lastModifiedDate = :lastModifiedDate")})
public class QuestionMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "QUESTION_TEXT", nullable = false, length = 200)
    private String questionText;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @OneToMany(mappedBy = "questionId")
    private Set<QuestionAnswerMapping> questionAnswerMappingSet;
    @JoinColumn(name = "CREATED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster createdById;
    @JoinColumn(name = "LAST_MODIFIED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster lastModifiedById;

    public QuestionMaster() {
    }

    public QuestionMaster(BigDecimal id) {
        this.id = id;
    }

    public QuestionMaster(BigDecimal id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
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

    @XmlTransient
    public Set<QuestionAnswerMapping> getQuestionAnswerMappingSet() {
        return questionAnswerMappingSet;
    }

    public void setQuestionAnswerMappingSet(Set<QuestionAnswerMapping> questionAnswerMappingSet) {
        this.questionAnswerMappingSet = questionAnswerMappingSet;
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
        if (!(object instanceof QuestionMaster)) {
            return false;
        }
        QuestionMaster other = (QuestionMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.QuestionMaster[ id=" + id + " ]";
    }
    
}

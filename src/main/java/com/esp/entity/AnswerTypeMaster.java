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
@Table(name = "ANSWER_TYPE_MASTER", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnswerTypeMaster.findAll", query = "SELECT a FROM AnswerTypeMaster a"),
    @NamedQuery(name = "AnswerTypeMaster.findById", query = "SELECT a FROM AnswerTypeMaster a WHERE a.id = :id"),
    @NamedQuery(name = "AnswerTypeMaster.findByAnsTypeText", query = "SELECT a FROM AnswerTypeMaster a WHERE a.ansTypeText = :ansTypeText")})
public class AnswerTypeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "ANS_TYPE_TEXT", length = 100)
    private String ansTypeText;
    @OneToMany(mappedBy = "ansTypeId")
    private Set<QuestionAnswerMapping> questionAnswerMappingSet;

    public AnswerTypeMaster() {
    }

    public AnswerTypeMaster(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAnsTypeText() {
        return ansTypeText;
    }

    public void setAnsTypeText(String ansTypeText) {
        this.ansTypeText = ansTypeText;
    }

    @XmlTransient
    public Set<QuestionAnswerMapping> getQuestionAnswerMappingSet() {
        return questionAnswerMappingSet;
    }

    public void setQuestionAnswerMappingSet(Set<QuestionAnswerMapping> questionAnswerMappingSet) {
        this.questionAnswerMappingSet = questionAnswerMappingSet;
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
        if (!(object instanceof AnswerTypeMaster)) {
            return false;
        }
        AnswerTypeMaster other = (AnswerTypeMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.AnswerTypeMaster[ id=" + id + " ]";
    }
    
}

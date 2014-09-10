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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rakesh.K
 */
@Entity
@Table(name = "ANSWER_TEXT_MASTER", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnswerTextMaster.findAll", query = "SELECT a FROM AnswerTextMaster a"),
    @NamedQuery(name = "AnswerTextMaster.findById", query = "SELECT a FROM AnswerTextMaster a WHERE a.id = :id"),
    @NamedQuery(name = "AnswerTextMaster.findByAnsText", query = "SELECT a FROM AnswerTextMaster a WHERE a.ansText = :ansText"),
    @NamedQuery(name = "AnswerTextMaster.findByCreationDate", query = "SELECT a FROM AnswerTextMaster a WHERE a.creationDate = :creationDate"),
    @NamedQuery(name = "AnswerTextMaster.findByLastModifiedDate", query = "SELECT a FROM AnswerTextMaster a WHERE a.lastModifiedDate = :lastModifiedDate")})
public class AnswerTextMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name ="SEQ_ANSWER_TEXT_MASTER" ,sequenceName ="SEQ_ANSWER_TEXT_MASTER")
    @GeneratedValue(generator = "SEQ_ANSWER_TEXT_MASTER",strategy =GenerationType.SEQUENCE )
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "ANS_TEXT", nullable = false, length = 20)
    private String ansText;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @JoinColumn(name = "CREATED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster createdById;
    @JoinColumn(name = "LAST_MODIFIED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster lastModifiedById;
    @OneToMany(mappedBy = "ansText9")
    private Set<AnswerMaster> answerMasterSet;
    @OneToMany(mappedBy = "ansText6")
    private Set<AnswerMaster> answerMasterSet1;
    @OneToMany(mappedBy = "ansText1")
    private Set<AnswerMaster> answerMasterSet2;
    @OneToMany(mappedBy = "ansText8")
    private Set<AnswerMaster> answerMasterSet3;
    @OneToMany(mappedBy = "ansText7")
    private Set<AnswerMaster> answerMasterSet4;
    @OneToMany(mappedBy = "ansText10")
    private Set<AnswerMaster> answerMasterSet5;
    @OneToMany(mappedBy = "ansText5")
    private Set<AnswerMaster> answerMasterSet6;
    @OneToMany(mappedBy = "ansText4")
    private Set<AnswerMaster> answerMasterSet7;
    @OneToMany(mappedBy = "ansText3")
    private Set<AnswerMaster> answerMasterSet8;
    @OneToMany(mappedBy = "ansText2")
    private Set<AnswerMaster> answerMasterSet9;
    @OneToMany(mappedBy = "ansTextId")
    private Set<SurveyResponse> surveyResponseSet;

    public AnswerTextMaster() {
    }

    public AnswerTextMaster(BigDecimal id) {
        this.id = id;
    }

    public AnswerTextMaster(BigDecimal id, String ansText) {
        this.id = id;
        this.ansText = ansText;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAnsText() {
        return ansText;
    }

    public void setAnsText(String ansText) {
        this.ansText = ansText;
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

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet() {
        return answerMasterSet;
    }

    public void setAnswerMasterSet(Set<AnswerMaster> answerMasterSet) {
        this.answerMasterSet = answerMasterSet;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet1() {
        return answerMasterSet1;
    }

    public void setAnswerMasterSet1(Set<AnswerMaster> answerMasterSet1) {
        this.answerMasterSet1 = answerMasterSet1;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet2() {
        return answerMasterSet2;
    }

    public void setAnswerMasterSet2(Set<AnswerMaster> answerMasterSet2) {
        this.answerMasterSet2 = answerMasterSet2;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet3() {
        return answerMasterSet3;
    }

    public void setAnswerMasterSet3(Set<AnswerMaster> answerMasterSet3) {
        this.answerMasterSet3 = answerMasterSet3;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet4() {
        return answerMasterSet4;
    }

    public void setAnswerMasterSet4(Set<AnswerMaster> answerMasterSet4) {
        this.answerMasterSet4 = answerMasterSet4;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet5() {
        return answerMasterSet5;
    }

    public void setAnswerMasterSet5(Set<AnswerMaster> answerMasterSet5) {
        this.answerMasterSet5 = answerMasterSet5;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet6() {
        return answerMasterSet6;
    }

    public void setAnswerMasterSet6(Set<AnswerMaster> answerMasterSet6) {
        this.answerMasterSet6 = answerMasterSet6;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet7() {
        return answerMasterSet7;
    }

    public void setAnswerMasterSet7(Set<AnswerMaster> answerMasterSet7) {
        this.answerMasterSet7 = answerMasterSet7;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet8() {
        return answerMasterSet8;
    }

    public void setAnswerMasterSet8(Set<AnswerMaster> answerMasterSet8) {
        this.answerMasterSet8 = answerMasterSet8;
    }

    @XmlTransient
    public Set<AnswerMaster> getAnswerMasterSet9() {
        return answerMasterSet9;
    }

    public void setAnswerMasterSet9(Set<AnswerMaster> answerMasterSet9) {
        this.answerMasterSet9 = answerMasterSet9;
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
        if (!(object instanceof AnswerTextMaster)) {
            return false;
        }
        AnswerTextMaster other = (AnswerTextMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.AnswerTextMaster[ id=" + id + " ]";
    }
    
}

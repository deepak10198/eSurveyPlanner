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
@Table(name = "ANSWER_MASTER", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnswerMaster.findAll", query = "SELECT a FROM AnswerMaster a"),
    @NamedQuery(name = "AnswerMaster.findById", query = "SELECT a FROM AnswerMaster a WHERE a.id = :id"),
    @NamedQuery(name = "AnswerMaster.findByCreationDate", query = "SELECT a FROM AnswerMaster a WHERE a.creationDate = :creationDate"),
    @NamedQuery(name = "AnswerMaster.findByLastModifiedDate", query = "SELECT a FROM AnswerMaster a WHERE a.lastModifiedDate = :lastModifiedDate")})
public class AnswerMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @JoinColumn(name = "ANS_TEXT5", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText5;
    @JoinColumn(name = "ANS_TEXT6", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText6;
    @JoinColumn(name = "ANS_TEXT1", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText1;
    @JoinColumn(name = "ANS_TEXT9", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText9;
    @JoinColumn(name = "ANS_TEXT8", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText8;
    @JoinColumn(name = "LAST_MODIFIED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster lastModifiedById;
    @JoinColumn(name = "ANS_TEXT7", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText7;
    @JoinColumn(name = "ANS_TEXT10", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText10;
    @JoinColumn(name = "ANS_TEXT4", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText4;
    @JoinColumn(name = "ANS_TEXT3", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText3;
    @JoinColumn(name = "ANS_TEXT2", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansText2;
    @JoinColumn(name = "CREATED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster createdById;
    @OneToMany(mappedBy = "answerId")
    private Set<QuestionAnswerMapping> questionAnswerMappingSet;

    public AnswerMaster() {
    }

    public AnswerMaster(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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

    public AnswerTextMaster getAnsText5() {
        return ansText5;
    }

    public void setAnsText5(AnswerTextMaster ansText5) {
        this.ansText5 = ansText5;
    }

    public AnswerTextMaster getAnsText6() {
        return ansText6;
    }

    public void setAnsText6(AnswerTextMaster ansText6) {
        this.ansText6 = ansText6;
    }

    public AnswerTextMaster getAnsText1() {
        return ansText1;
    }

    public void setAnsText1(AnswerTextMaster ansText1) {
        this.ansText1 = ansText1;
    }

    public AnswerTextMaster getAnsText9() {
        return ansText9;
    }

    public void setAnsText9(AnswerTextMaster ansText9) {
        this.ansText9 = ansText9;
    }

    public AnswerTextMaster getAnsText8() {
        return ansText8;
    }

    public void setAnsText8(AnswerTextMaster ansText8) {
        this.ansText8 = ansText8;
    }

    public UserMaster getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(UserMaster lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public AnswerTextMaster getAnsText7() {
        return ansText7;
    }

    public void setAnsText7(AnswerTextMaster ansText7) {
        this.ansText7 = ansText7;
    }

    public AnswerTextMaster getAnsText10() {
        return ansText10;
    }

    public void setAnsText10(AnswerTextMaster ansText10) {
        this.ansText10 = ansText10;
    }

    public AnswerTextMaster getAnsText4() {
        return ansText4;
    }

    public void setAnsText4(AnswerTextMaster ansText4) {
        this.ansText4 = ansText4;
    }

    public AnswerTextMaster getAnsText3() {
        return ansText3;
    }

    public void setAnsText3(AnswerTextMaster ansText3) {
        this.ansText3 = ansText3;
    }

    public AnswerTextMaster getAnsText2() {
        return ansText2;
    }

    public void setAnsText2(AnswerTextMaster ansText2) {
        this.ansText2 = ansText2;
    }

    public UserMaster getCreatedById() {
        return createdById;
    }

    public void setCreatedById(UserMaster createdById) {
        this.createdById = createdById;
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
        if (!(object instanceof AnswerMaster)) {
            return false;
        }
        AnswerMaster other = (AnswerMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.AnswerMaster[ id=" + id + " ]";
    }
    
}

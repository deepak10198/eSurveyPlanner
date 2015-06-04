/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rakesh.K
 */
@Entity
@Table(name = "SURVEY_RESPONSE", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SurveyResponse.findAll", query = "SELECT s FROM SurveyResponse s"),
    @NamedQuery(name = "SurveyResponse.findById", query = "SELECT s FROM SurveyResponse s WHERE s.id = :id"),
    @NamedQuery(name = "SurveyResponse.findByAnsText", query = "SELECT s FROM SurveyResponse s WHERE s.ansText = :ansText"),
    @NamedQuery(name = "SurveyResponse.findByIpAddress", query = "SELECT s FROM SurveyResponse s WHERE s.ipAddress = :ipAddress"),
    @NamedQuery(name = "SurveyResponse.findByEmailId", query = "SELECT s FROM SurveyResponse s WHERE s.emailId = :emailId")})
public class SurveyResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
     @SequenceGenerator(name ="SEQ_SURVEY_RESPONSE" ,sequenceName ="SEQ_SURVEY_RESPONSE")
    @GeneratedValue(generator = "SEQ_SURVEY_RESPONSE",strategy =GenerationType.SEQUENCE )
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "ANS_TEXT", length = 200)
    private String ansText;
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    @Column(name = "EMAIL_ID", length = 200)
    private String emailId;
    @JoinColumn(name = "ANS_TEXT_ID", referencedColumnName = "ID")
    @ManyToOne
    private AnswerTextMaster ansTextId;
    @JoinColumn(name = "QUES_ANS_MAPPING_ID", referencedColumnName = "ID")
    @ManyToOne
    private QuestionAnswerMapping quesAnsMappingId;
    @JoinColumn(name = "SURVEY_ID", referencedColumnName = "ID")
    @ManyToOne
    private SurveyMaster surveyId;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster userId;

    public SurveyResponse() {
    }

    public SurveyResponse(BigDecimal id) {
        this.id = id;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public AnswerTextMaster getAnsTextId() {
        return ansTextId;
    }

    public void setAnsTextId(AnswerTextMaster ansTextId) {
        this.ansTextId = ansTextId;
    }

    public QuestionAnswerMapping getQuesAnsMappingId() {
        return quesAnsMappingId;
    }

    public void setQuesAnsMappingId(QuestionAnswerMapping quesAnsMappingId) {
        this.quesAnsMappingId = quesAnsMappingId;
    }

    public SurveyMaster getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(SurveyMaster surveyId) {
        this.surveyId = surveyId;
    }

    public UserMaster getUserId() {
        return userId;
    }

    public void setUserId(UserMaster userId) {
        this.userId = userId;
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
        if (!(object instanceof SurveyResponse)) {
            return false;
        }
        SurveyResponse other = (SurveyResponse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.SurveyResponse[ id=" + id + " ]";
    }
    
}

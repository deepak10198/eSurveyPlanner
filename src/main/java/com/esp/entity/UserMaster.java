/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "USER_MASTER", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserMaster.findAll", query = "SELECT u FROM UserMaster u"),
    @NamedQuery(name = "UserMaster.findById", query = "SELECT u FROM UserMaster u WHERE u.id = :id"),
    @NamedQuery(name = "UserMaster.findByLoginId", query = "SELECT u FROM UserMaster u WHERE u.loginId = :loginId"),
    @NamedQuery(name = "UserMaster.findByUserType", query = "SELECT u FROM UserMaster u WHERE u.userType = :userType"),
    @NamedQuery(name = "UserMaster.findByFirstName", query = "SELECT u FROM UserMaster u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "UserMaster.findByMiddleName", query = "SELECT u FROM UserMaster u WHERE u.middleName = :middleName"),
    @NamedQuery(name = "UserMaster.findByLastName", query = "SELECT u FROM UserMaster u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "UserMaster.findByDob", query = "SELECT u FROM UserMaster u WHERE u.dob = :dob"),
    @NamedQuery(name = "UserMaster.findByGender", query = "SELECT u FROM UserMaster u WHERE u.gender = :gender"),
    @NamedQuery(name = "UserMaster.findByMaritalStatus", query = "SELECT u FROM UserMaster u WHERE u.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "UserMaster.findByCreationDate", query = "SELECT u FROM UserMaster u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "UserMaster.findByLastModifiedDate", query = "SELECT u FROM UserMaster u WHERE u.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "UserMaster.findByEmail", query = "SELECT u FROM UserMaster u WHERE u.email = :email"),
    @NamedQuery(name = "UserMaster.findByLastModifiedById", query = "SELECT u FROM UserMaster u WHERE u.lastModifiedById = :lastModifiedById"),
    @NamedQuery(name = "UserMaster.findByCreatedById", query = "SELECT u FROM UserMaster u WHERE u.createdById = :createdById")})
public class UserMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "LOGIN_ID", nullable = false, length = 50)
    private String loginId;
    @Column(name = "USER_TYPE", length = 50)
    private String userType;
    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;
    @Column(name = "MIDDLE_NAME", length = 20)
    private String middleName;
    @Column(name = "LAST_NAME", length = 50)
    private String lastName;
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(length = 50)
    private String gender;
    @Column(name = "MARITAL_STATUS", length = 50)
    private String maritalStatus;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @Column(length = 200)
    private String email;
    @Column(name = "LAST_MODIFIED_BY_ID")
    private BigInteger lastModifiedById;
    @Column(name = "CREATED_BY_ID")
    private BigInteger createdById;
    @OneToMany(mappedBy = "createdById")
    private Set<AnswerTextMaster> answerTextMasterSet;
    @OneToMany(mappedBy = "lastModifiedById")
    private Set<AnswerTextMaster> answerTextMasterSet1;
    @OneToMany(mappedBy = "lastModifiedById")
    private Set<AnswerMaster> answerMasterSet;
    @OneToMany(mappedBy = "createdById")
    private Set<AnswerMaster> answerMasterSet1;
    @OneToMany(mappedBy = "userid")
    private Set<UserSurveyUrlMapping> userSurveyUrlMappingSet;
    @OneToMany(mappedBy = "createdById")
    private Set<UserSurveyUrlMapping> userSurveyUrlMappingSet1;
    @OneToMany(mappedBy = "lastModifiedById")
    private Set<UserSurveyUrlMapping> userSurveyUrlMappingSet2;
    @OneToMany(mappedBy = "lastModifiedById")
    private Set<QuestionAnswerMapping> questionAnswerMappingSet;
    @OneToMany(mappedBy = "createdById")
    private Set<QuestionAnswerMapping> questionAnswerMappingSet1;
    @OneToMany(mappedBy = "createdById")
    private Set<QuestionMaster> questionMasterSet;
    @OneToMany(mappedBy = "lastModifiedById")
    private Set<QuestionMaster> questionMasterSet1;
    @OneToMany(mappedBy = "createdById")
    private Set<UserList> userListSet;
    @OneToMany(mappedBy = "userId")
    private Set<UserList> userListSet1;
    @OneToMany(mappedBy = "lastModifiedById")
    private Set<UserList> userListSet2;
    @OneToMany(mappedBy = "lastModifiedById")
    private Set<SurveyMaster> surveyMasterSet;
    @OneToMany(mappedBy = "createdById")
    private Set<SurveyMaster> surveyMasterSet1;
    @OneToMany(mappedBy = "lastModifiedById")
    private Set<SurveyQuestionMapping> surveyQuestionMappingSet;
    @OneToMany(mappedBy = "createdById")
    private Set<SurveyQuestionMapping> surveyQuestionMappingSet1;

    public UserMaster() {
    }

    public UserMaster(BigDecimal id) {
        this.id = id;
    }

    public UserMaster(BigDecimal id, String loginId) {
        this.id = id;
        this.loginId = loginId;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getLastModifiedById() {
        return lastModifiedById;
    }

    public void setLastModifiedById(BigInteger lastModifiedById) {
        this.lastModifiedById = lastModifiedById;
    }

    public BigInteger getCreatedById() {
        return createdById;
    }

    public void setCreatedById(BigInteger createdById) {
        this.createdById = createdById;
    }

    @XmlTransient
    public Set<AnswerTextMaster> getAnswerTextMasterSet() {
        return answerTextMasterSet;
    }

    public void setAnswerTextMasterSet(Set<AnswerTextMaster> answerTextMasterSet) {
        this.answerTextMasterSet = answerTextMasterSet;
    }

    @XmlTransient
    public Set<AnswerTextMaster> getAnswerTextMasterSet1() {
        return answerTextMasterSet1;
    }

    public void setAnswerTextMasterSet1(Set<AnswerTextMaster> answerTextMasterSet1) {
        this.answerTextMasterSet1 = answerTextMasterSet1;
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
    public Set<UserSurveyUrlMapping> getUserSurveyUrlMappingSet() {
        return userSurveyUrlMappingSet;
    }

    public void setUserSurveyUrlMappingSet(Set<UserSurveyUrlMapping> userSurveyUrlMappingSet) {
        this.userSurveyUrlMappingSet = userSurveyUrlMappingSet;
    }

    @XmlTransient
    public Set<UserSurveyUrlMapping> getUserSurveyUrlMappingSet1() {
        return userSurveyUrlMappingSet1;
    }

    public void setUserSurveyUrlMappingSet1(Set<UserSurveyUrlMapping> userSurveyUrlMappingSet1) {
        this.userSurveyUrlMappingSet1 = userSurveyUrlMappingSet1;
    }

    @XmlTransient
    public Set<UserSurveyUrlMapping> getUserSurveyUrlMappingSet2() {
        return userSurveyUrlMappingSet2;
    }

    public void setUserSurveyUrlMappingSet2(Set<UserSurveyUrlMapping> userSurveyUrlMappingSet2) {
        this.userSurveyUrlMappingSet2 = userSurveyUrlMappingSet2;
    }

    @XmlTransient
    public Set<QuestionAnswerMapping> getQuestionAnswerMappingSet() {
        return questionAnswerMappingSet;
    }

    public void setQuestionAnswerMappingSet(Set<QuestionAnswerMapping> questionAnswerMappingSet) {
        this.questionAnswerMappingSet = questionAnswerMappingSet;
    }

    @XmlTransient
    public Set<QuestionAnswerMapping> getQuestionAnswerMappingSet1() {
        return questionAnswerMappingSet1;
    }

    public void setQuestionAnswerMappingSet1(Set<QuestionAnswerMapping> questionAnswerMappingSet1) {
        this.questionAnswerMappingSet1 = questionAnswerMappingSet1;
    }

    @XmlTransient
    public Set<QuestionMaster> getQuestionMasterSet() {
        return questionMasterSet;
    }

    public void setQuestionMasterSet(Set<QuestionMaster> questionMasterSet) {
        this.questionMasterSet = questionMasterSet;
    }

    @XmlTransient
    public Set<QuestionMaster> getQuestionMasterSet1() {
        return questionMasterSet1;
    }

    public void setQuestionMasterSet1(Set<QuestionMaster> questionMasterSet1) {
        this.questionMasterSet1 = questionMasterSet1;
    }

    @XmlTransient
    public Set<UserList> getUserListSet() {
        return userListSet;
    }

    public void setUserListSet(Set<UserList> userListSet) {
        this.userListSet = userListSet;
    }

    @XmlTransient
    public Set<UserList> getUserListSet1() {
        return userListSet1;
    }

    public void setUserListSet1(Set<UserList> userListSet1) {
        this.userListSet1 = userListSet1;
    }

    @XmlTransient
    public Set<UserList> getUserListSet2() {
        return userListSet2;
    }

    public void setUserListSet2(Set<UserList> userListSet2) {
        this.userListSet2 = userListSet2;
    }

    @XmlTransient
    public Set<SurveyMaster> getSurveyMasterSet() {
        return surveyMasterSet;
    }

    public void setSurveyMasterSet(Set<SurveyMaster> surveyMasterSet) {
        this.surveyMasterSet = surveyMasterSet;
    }

    @XmlTransient
    public Set<SurveyMaster> getSurveyMasterSet1() {
        return surveyMasterSet1;
    }

    public void setSurveyMasterSet1(Set<SurveyMaster> surveyMasterSet1) {
        this.surveyMasterSet1 = surveyMasterSet1;
    }

    @XmlTransient
    public Set<SurveyQuestionMapping> getSurveyQuestionMappingSet() {
        return surveyQuestionMappingSet;
    }

    public void setSurveyQuestionMappingSet(Set<SurveyQuestionMapping> surveyQuestionMappingSet) {
        this.surveyQuestionMappingSet = surveyQuestionMappingSet;
    }

    @XmlTransient
    public Set<SurveyQuestionMapping> getSurveyQuestionMappingSet1() {
        return surveyQuestionMappingSet1;
    }

    public void setSurveyQuestionMappingSet1(Set<SurveyQuestionMapping> surveyQuestionMappingSet1) {
        this.surveyQuestionMappingSet1 = surveyQuestionMappingSet1;
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
        if (!(object instanceof UserMaster)) {
            return false;
        }
        UserMaster other = (UserMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.UserMaster[ id=" + id + " ]";
    }
    
}

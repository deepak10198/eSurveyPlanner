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
    @SequenceGenerator(name ="SEQ_USER_MASTER" ,sequenceName ="SEQ_USER_MASTER")
    @GeneratedValue(generator = "SEQ_USER_MASTER",strategy =GenerationType.SEQUENCE )
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
    @Column(name="password")
    private String password;
    
    @JoinColumn(name = "ORG_ID", referencedColumnName = "ID")
    @ManyToOne
    private Organisation orgId;
    @OneToMany(mappedBy = "userId")
    private Set<UserRoles> userRolesSet;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    
    public Organisation getOrgId() {
        return orgId;
    }

    public void setOrgId(Organisation orgId) {
        this.orgId = orgId;
    }

    @XmlTransient
    public Set<UserRoles> getUserRolesSet() {
        return userRolesSet;
    }

    public void setUserRolesSet(Set<UserRoles> userRolesSet) {
        this.userRolesSet = userRolesSet;
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

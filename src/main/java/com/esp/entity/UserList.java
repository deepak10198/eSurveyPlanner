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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Rakesh.K
 */
@Entity 
@Table(name = "USER_LIST", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserList.findAll", query = "SELECT u FROM UserList u"),
    @NamedQuery(name = "UserList.findById", query = "SELECT u FROM UserList u WHERE u.id = :id"),
    @NamedQuery(name = "UserList.findByUserListName", query = "SELECT u FROM UserList u WHERE u.userListName = :userListName"),
    @NamedQuery(name = "UserList.findByCreationDate", query = "SELECT u FROM UserList u WHERE u.creationDate = :creationDate"),
    @NamedQuery(name = "UserList.findByLastModifiedDate", query = "SELECT u FROM UserList u WHERE u.lastModifiedDate = :lastModifiedDate")})
public class UserList implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "USER_LIST_NAME", length = 20)
    private String userListName;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @JoinColumn(name = "ORG_ID", referencedColumnName = "ID")
    @ManyToOne
    private Organisation orgId;
    @JoinColumn(name = "CREATED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster createdById;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster userId;
    @JoinColumn(name = "LAST_MODIFIED_BY_ID", referencedColumnName = "ID")
    @ManyToOne
    private UserMaster lastModifiedById;

    public UserList() {
    }

    public UserList(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUserListName() {
        return userListName;
    }

    public void setUserListName(String userListName) {
        this.userListName = userListName;
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

    public Organisation getOrgId() {
        return orgId;
    }

    public void setOrgId(Organisation orgId) {
        this.orgId = orgId;
    }

    public UserMaster getCreatedById() {
        return createdById;
    }

    public void setCreatedById(UserMaster createdById) {
        this.createdById = createdById;
    }

    public UserMaster getUserId() {
        return userId;
    }

    public void setUserId(UserMaster userId) {
        this.userId = userId;
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
        if (!(object instanceof UserList)) {
            return false;
        }
        UserList other = (UserList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.UserList[ id=" + id + " ]";
    }
    
}

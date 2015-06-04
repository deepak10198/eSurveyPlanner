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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ----
 */
@Entity 
@Table(name = "USER_LIST_MAPPING", catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserListMapping.findAll", query = "SELECT l FROM UserListMapping l"),
    @NamedQuery(name = "UserListMapping.findById", query = "SELECT l FROM UserListMapping l WHERE l.id = :id"),
    @NamedQuery(name = "UserListMapping.findByLastModifiedDate", query = "SELECT l FROM UserListMapping l WHERE l.lastModifiedDate = :lastModifiedDate")})
public class UserListMapping implements Serializable {
	
	private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name ="SEQ_USER_LIST_MAPPING" ,sequenceName ="SEQ_USER_LIST_MAPPING")
    @GeneratedValue(generator = "SEQ_USER_LIST_MAPPING",strategy =GenerationType.SEQUENCE )
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private UserMaster userId;
    @JoinColumn(name = "USER_LIST_ID", referencedColumnName = "ID",nullable = false)
    @ManyToOne(optional = false)
    private UserList userlistId;
    
    
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
    

    public UserMaster getUserId() {
        return userId;
    }

    public void setUserId(UserMaster userId) {
        this.userId = userId;
    }
    
    public UserList getUserListId(){
    	return userlistId;
    }
    public void setUserListId(UserList userlistId)
    {
    	this.userlistId = userlistId;
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
        UserListMapping other = (UserListMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.UserListMapping[ id=" + id + " ]";
    }
    
	
}
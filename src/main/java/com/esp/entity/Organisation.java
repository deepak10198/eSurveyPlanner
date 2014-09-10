/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esp.entity ;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rakesh.K
 */
@Entity
@Table(catalog = "", schema = "SURVEY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organisation.findAll", query = "SELECT o FROM Organisation o"),
    @NamedQuery(name = "Organisation.findById", query = "SELECT o FROM Organisation o WHERE o.id = :id"),
    @NamedQuery(name = "Organisation.findByOrgName", query = "SELECT o FROM Organisation o WHERE o.orgName = :orgName"),
    @NamedQuery(name = "Organisation.findByOrgRegId", query = "SELECT o FROM Organisation o WHERE o.orgRegId = :orgRegId")})
public class Organisation implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "SEQ_ORGANISATION", sequenceName = "SEQ_ORGANISATION")
    @GeneratedValue(generator = "SEQ_ORGANISATION", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(nullable = false, precision = 22)
    private BigDecimal id;
    @Column(name = "ORG_NAME", length = 200)
    private String orgName;
    @Column(name = "ORG_REG_ID", length = 100)
    private String orgRegId;
    @OneToMany(mappedBy = "orgId")
    private Set<UserList> userListSet;

    public Organisation() {
    }

    public Organisation(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgRegId() {
        return orgRegId;
    }

    public void setOrgRegId(String orgRegId) {
        this.orgRegId = orgRegId;
    }

    @XmlTransient
    public Set<UserList> getUserListSet() {
        return userListSet;
    }

    public void setUserListSet(Set<UserList> userListSet) {
        this.userListSet = userListSet;
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
        if (!(object instanceof Organisation)) {
            return false;
        }
        Organisation other = (Organisation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.esp.entity.Organisation[ id=" + id + " ]";
    }

}

package com.ku.people.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authority_name")
    private String authorityName;
    @ManyToMany
    @JoinTable(
            name = "role_authority_links",
            joinColumns = {@JoinColumn(name = "authority_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;

    public Authority() {
    }

    public Authority(Long id, String authorityName, Set<Role> roles) {
        this.id = id;
        this.authorityName = authorityName;
        this.roles = roles;
    }

    public Authority(Long id, String authorityName) {
        this.id = id;
        this.authorityName = authorityName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Authority aThat = (Authority) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {
                return false;
            }
        } else if (!getId().equals(aThat.getId())) {
            return false;
        }

        if (getAuthorityName() == null) {
            return aThat.getAuthorityName() == null;
        } else return getAuthorityName().equals(aThat.getAuthorityName());
    }

    @Override
    public int hashCode() {
        int result = 1, prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getAuthorityName() != null ? getAuthorityName().hashCode() : 0);
        return result;
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", authorityName = ").append(getAuthorityName())
                .append(" }")
                .toString();
    }
}

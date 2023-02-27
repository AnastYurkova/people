package com.ku.people.entity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role_name")
    private String name;
    @ManyToMany
    @JoinTable(
            name = "user_role_links",
            joinColumns = {@JoinColumn(name = "role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<User> users;
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Authority> authorities;

    public Role() {
    }

    public Role(Long id, String name, Set<User> users, Set<Authority> authorities) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.authorities = authorities;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(Long id) {
        this.id = id;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Role aThat = (Role) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {
                return false;
            }
        } else if (!getId().equals(aThat.getId())) {
            return false;
        }

        if (getName() == null) {
            return aThat.getName() == null;
        } else return getName().equals(aThat.getName());
    }

    @Override
    public int hashCode() {
        int result = 1, prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", name = ").append(getName())
                .append(" }")
                .toString();
    }
}

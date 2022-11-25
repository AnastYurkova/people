package com.ku.people.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Role> roles;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Detail> details;

    public User() {
    }

    public User(Long id, String username, String password, String surname, String name, Set<Role> roles, Set<Detail> details) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.surname = surname;
        this.roles = roles;
        this.details = details;
    }

    public User(Long id, String username, String password, String surname, String name) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.surname = surname;
    }

    public User(Long id) {
        this.id = id;
    }

    public Set<Detail> getDetails() {
        return details;
    }

    public void setDetails(Set<Detail> details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
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
        User aThat = (User) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {
                return false;
            }
        } else if (!getId().equals(aThat.getId())) {
            return false;
        }

        if (getUsername() == null) {
            if (aThat.getUsername() != null) {
                return false;
            }
        } else if (!getUsername().equals(aThat.getUsername())) {
            return false;
        }

        if (getPassword() == null) {
            if (aThat.getPassword() != null) {
                return false;
            }
        } else if (!getPassword().equals(aThat.getPassword())) {
            return false;
        }

        if (getSurname() == null) {
            if (aThat.getSurname() != null) {
                return false;
            }
        } else if (!getSurname().equals(aThat.getSurname())) {
            return false;
        }

        if (getName() == null) {
            if (aThat.getName() != null) {
                return false;
            }
        } else if (!getName().equals(aThat.getName())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1, prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = prime * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = prime * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = prime * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", username = ").append(getUsername())
                .append(", password = ").append(getPassword())
                .append(", surname = ").append(getSurname())
                .append(", name = ").append(getName())
                .append(" }")
                .toString();
    }
}

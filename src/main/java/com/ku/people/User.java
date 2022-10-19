package com.ku.people;

import java.util.List;
import java.lang.StringBuilder;
import java.util.stream.Collectors;

public class User {
    private Long id;
    private String username;
    private String password;
    private String surname;
    private String name;
    private List<Role> roles;
    private List<Detail> details;


    public User() {
    }

    public User(Long id, String username, String password, String surname, String name, List<Role> roles, List<Detail> details) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.username = username;
        this.surname = surname;
        this.roles = roles;
        this.details = details;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;

        if (getId() == null) {
            if (user.getId() != null) {
                return false;
            }
        } else if (!getId().equals(user.getId())) {
            return false;
        }

        if (getUsername() == null) {
            if (user.getUsername() != null) {
                return false;
            }
        } else if (!getUsername().equals(user.getUsername())) {
            return false;
        }

        if (getPassword() == null) {
            if (user.getPassword() != null) {
                return false;
            }
        } else if (!getPassword().equals(user.getPassword())) {
            return false;
        }

        if (getSurname() == null) {
            if (user.getSurname() != null) {
                return false;
            }
        } else if (!getSurname().equals(user.getSurname())) {
            return false;
        }

        for (int i=0; i < getRoles().size(); i++) {
            Role role = getRoles().get(i);
            if (role.getId()==null){
                if (user.getRoles().get(i).getId()!=null){
                    return false;
                }
            } else if(!role.getId().equals(user.getRoles().get(i).getId())){
                return false;
            }
        }
        for (int i=0; i < getDetails().size(); i++) {
            Detail detail= getDetails().get(i);
            if (detail.getId()==null){
                if (user.getDetails().get(i).getId()!=null){
                    return false;
                }
            } else if(!detail.getId().equals(user.getDetails().get(i).getId())){
                return false;
            }
        }

        return getName() == null ? user.getName() == null : getName().equals(user.getName());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        for (int i=0; i < getRoles().size(); i++) {
            Role role =getRoles().get(i);
            result = 31 * result + (role.getId() != null ? role.getId().hashCode() : 0);
            }
        for (int i=0; i < getDetails().size(); i++) {
            Detail detail =getDetails().get(i);
            result = 31 * result + (detail.getId() != null ? detail.getId().hashCode() : 0);
        }
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append(" { Id = ").append(getId())
                .append(", Username = ").append(getUsername()).append(", Password = ").append(getPassword())
                .append(", Surname = ").append(getSurname()).append(", Name = ").append(getName())
                .append("} contains {Role id =").append(getRoles().stream().map(role -> role.getId()).collect(Collectors.toList()))
                .append("}, {Details = ").append(getDetails().stream().map(detail-> detail.getId()).collect(Collectors.toList()))
                .append("}").toString();
    }
}

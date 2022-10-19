package com.ku.people;

import java.util.List;
import java.util.stream.Collectors;

public class Authority {
    private Long id;
    private String name;

    private List<Role> roles;

    public Authority() {
    }

    public Authority(Long id, String name, List<Role> roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
        Authority authority = (Authority) o;

        if (getId() == null) {
            if (authority.getId() != null) {
                return false;
            }
        } else if (!getId().equals(authority.getId())) {
            return false;
        }
        for (int i=0; i < getRoles().size(); i++) {
            Role role= getRoles().get(i);
            if (role.getId()==null){
                if (authority.getRoles().get(i).getId()!=null){
                    return false;
                }
            } else if(!role.getId().equals(authority.getRoles().get(i).getId())){
                return false;
            }
        }
        return getName() == null ? authority.getName() == null : getName().equals(authority.getName());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result =  31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        for (int i=0; i < getRoles().size(); i++) {
            Role role =getRoles().get(i);
            result = 31 * result + (role.getId() != null ? role.getId().hashCode() : 0);
        }
        return result;
    }

    public String toString()
    {
        return new StringBuilder(getClass().getSimpleName()).append(" { Id = ").append(getId()).append(", Name = ").append(getName())
                .append("} contains {Role id =").append(getRoles().stream().map(role -> role.getId()).collect(Collectors.toList()))
                .append("}").toString();
    }



}

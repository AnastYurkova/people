package com.ku.people;

import java.util.List;
import java.util.stream.Collectors;

public class Role {
    private Long id;
    private String name;

    private List<User> users;
    private List<Authority> authorities;

    public Role() {
    }

    public Role(Long id, String name, List<User> users, List<Authority> authorities) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.authorities = authorities;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
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
        Role role = (Role) o;

        if (getId() == null) {
            if (role.getId() != null) {
                return false;
            }
        } else if (!getId().equals(role.getId())) {
            return false;
        }

        for (int i = 0; i < getUsers().size(); i++) {
            User user = getUsers().get(i);
            if (role.getId() == null) {
                if (role.getUsers().get(i).getId() != null) {
                    return false;
                }
            } else if (!user.getId().equals(role.getUsers().get(i).getId())) {
                return false;
            }
        }
        for (int i = 0; i < getAuthorities().size(); i++) {
            Authority authority = getAuthorities().get(i);
            if (authority.getId() == null) {
                if (role.getAuthorities().get(i).getId() != null) {
                    return false;
                }
            } else if (!authority.getId().equals(role.getAuthorities().get(i).getId())) {
                return false;
            }
        }


        return getName() == null ? role.getName() == null : getName().equals(role.getName());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        for (int i = 0; i < getUsers().size(); i++) {
            User user = getUsers().get(i);
            result = 31 * result + (user.getId() != null ? user.getId().hashCode() : 0);
        }
        for (int i = 0; i < getAuthorities().size(); i++) {
            Authority authority = getAuthorities().get(i);
            result = 31 * result + (authority.getId() != null ? authority.getId().hashCode() : 0);
        }
        return result;
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName()).append(" { Id = ").append(getId()).append(", Name = ").append(getName())
                .append("} contains {Users = ").append(getUsers().stream().map(user -> user.getId()).collect(Collectors.toList()))
                .append("} {Authorities = ").append(getAuthorities().stream().map(authority -> authority.getId()).collect(Collectors.toList()))
                .append("}").toString();
    }
}

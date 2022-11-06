package com.ku.people;

import java.util.List;

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

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(Long id) {
        this.id = id;
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
            if (aThat.getName() != null) {
                return false;
            }
        } else if (!getName().equals(aThat.getName())) {
            return false;
        }

        if (getUsers() == null && aThat.getUsers() != null) {
            return false;
        } else if (aThat.getUsers() == null && getUsers() != null) {
            return false;
        } else if (aThat.getUsers() != null && getUsers() != null) {
            if (getUsers().size() != aThat.getUsers().size()) {
                return false;
            }
            for (int i = 0; i < getUsers().size(); i++) {
                User user = getUsers().get(i);
                if (aThat.getId() == null) {
                    if (aThat.getUsers().get(i).getId() != null) {
                        return false;
                    }
                } else if (!user.getId().equals(aThat.getUsers().get(i).getId())) {
                    return false;
                }
            }

        }

        if (getAuthorities() == null && aThat.getAuthorities() != null) {
            return false;
        } else if (aThat.getAuthorities() == null && getAuthorities() != null) {
            return false;
        } else if (aThat.getAuthorities() != null && getAuthorities() != null) {
            if (getAuthorities().size() != aThat.getAuthorities().size()) {
                return false;
            }
            for (int i = 0; i < getAuthorities().size(); i++) {
                Authority authority = getAuthorities().get(i);
                if (authority.getId() == null) {
                    if (aThat.getAuthorities().get(i).getId() != null) {
                        return false;
                    }
                } else if (!authority.getId().equals(aThat.getAuthorities().get(i).getId())) {
                    return false;
                }
            }

        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 1, prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getName() != null ? getName().hashCode() : 0);
        if (getUsers() != null) {
            for (int i = 0; i < getUsers().size(); i++) {
                User user = getUsers().get(i);
                result = prime * result + (user.getId() != null ? user.getId().hashCode() : 0);
            }
        }
        if (getAuthorities() != null) {
            for (int i = 0; i < getAuthorities().size(); i++) {
                Authority authority = getAuthorities().get(i);
                result = prime * result + (authority.getId() != null ? authority.getId().hashCode() : 0);
            }
        }
        return result;
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", name = ").append(getName())
                .append("} contains {User id = ")
                .append(getUsers() == null
                        ? List.of()
                        : getUsers().stream()
                            .map(User::getId)
                            .toList())
                .append("} {Authorities id = ")
                .append(getAuthorities() == null
                        ? List.of()
                        : getAuthorities().stream()
                            .map(Authority::getId)
                            .toList())
                .append("}")
                .toString();
    }
}

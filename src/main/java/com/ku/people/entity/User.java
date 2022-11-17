package com.ku.people.entity;

import java.util.List;

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

        if (getRoles() == null && aThat.getRoles() != null) {
            return false;
        } else if (aThat.getRoles() == null && getRoles() != null) {
            return false;
        } else if (aThat.getRoles() != null && getRoles() != null) {
            if (getRoles().size() != aThat.getRoles().size()) {
                return false;
            }

            for (int i = 0; i < getRoles().size(); i++) {
                Role role = getRoles().get(i);
                if (role.getId() == null) {
                    if (aThat.getRoles().get(i).getId() != null) {
                        return false;
                    }
                } else if (!role.getId().equals(aThat.getRoles().get(i).getId())) {
                    return false;
                }
            }
        }

        if (getDetails() == null && aThat.getDetails() != null) {
            return false;
        } else if (aThat.getDetails() == null && getDetails() != null) {
            return false;
        } else if (aThat.getDetails() != null && getDetails() != null) {
            if (getDetails().size() != aThat.getDetails().size()) {
                return false;
            }
            for (int i = 0; i < getDetails().size(); i++) {
                Detail detail = getDetails().get(i);
                if (detail.getId() == null) {
                    if (aThat.getDetails().get(i).getId() != null) {
                        return false;
                    }
                } else if (!detail.getId().equals(aThat.getDetails().get(i).getId())) {
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
        result = prime * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = prime * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = prime * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = prime * result + (getName() != null ? getName().hashCode() : 0);
        if (getRoles() != null) {
            for (int i = 0; i < getRoles().size(); i++) {
                Role role = getRoles().get(i);
                result = prime * result + (role.getId() != null ? role.getId().hashCode() : 0);
            }
        }
        if (getDetails() != null) {
            for (int i = 0; i < getDetails().size(); i++) {
                Detail detail = getDetails().get(i);
                result = prime * result + (detail.getId() != null ? detail.getId().hashCode() : 0);
            }
        }
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
                .append("} contains {Role id = ")
                .append(getRoles() ==  null
                        ? List.of()
                        : getRoles().stream()
                            .map(Role::getId)
                            .toList())
                .append("}, {details = ")
                .append(getDetails() ==  null
                        ? List.of()
                        : getDetails().stream()
                            .map(Detail::getId)
                            .toList())
                .append("}")
                .toString();
    }
}

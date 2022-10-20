package com.ku.people;

import java.util.List;
import java.util.stream.Collectors;

public class Authority {
    private Long id;
    private String authorityName;

    private List<Role> roles;

    public Authority() {
    }

    public Authority(Long id, String authorityName, List<Role> roles) {
        this.id = id;
        this.authorityName = authorityName;
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

        if (getRoles() == null && aThat.getRoles() != null) {
            return false;
        } else if (aThat.getRoles() == null && getRoles() != null) {
            return false;
        } else if (aThat.getRoles() != null && getRoles() != null) {
            for (int i = 0; i < getRoles().size(); i++) {
                if (getRoles().size() != aThat.getRoles().size()) {
                    return false;
                }
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
        return getAuthorityName() == null ? aThat.getAuthorityName() == null : getAuthorityName().equals(aThat.getAuthorityName());
    }

    @Override
    public int hashCode() {
        int result = 31;
        int prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getAuthorityName() != null ? getAuthorityName().hashCode() : 0);
        if (getRoles() != null) {
            for (int i = 0; i < getRoles().size(); i++) {
                Role role = getRoles().get(i);
                result = prime * result + (role.getId() != null ? role.getId().hashCode() : 0);
            }
        }
            return result;
        }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", authorityName = ").append(getAuthorityName())
                .append("} contains {Role id =")
                .append(getRoles().stream()
                        .map(Role::getId)
                        .toList())
                .append("}")
                .toString();
    }


}

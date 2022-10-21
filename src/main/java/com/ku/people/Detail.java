package com.ku.people;

public class Detail {
    private Long id;
    private String type;
    private User user;
    private Relationship relationship;

    public Detail() {
    }

    public Detail(Long id, String type, User user, Relationship relationship) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.relationship = relationship;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
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
        Detail aThat = (Detail) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {
                return false;
            }
        } else if (!getId().equals(aThat.getId())) {
            return false;
        }

        if (getType() == null) {
            if (aThat.getType() != null) {
                return false;
            }
        } else if (!getType().equals(aThat.getType())) {
            return false;
        }

        if (getUser() == null) {
            if (aThat.getUser() != null) {
                return false;
            }
        } else if (!getUser().equals(aThat.getUser())) {
            return false;
        }
        return getRelationship() == null ? aThat.getRelationship() == null : getRelationship().equals(aThat.getRelationship());
    }

    @Override
    public int hashCode() {
        int result = 1, prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getType() != null ? getType().hashCode() : 0);
        result = prime * result + (getUser() != null ? getUser().hashCode() : 0);
        result = prime * result + (getRelationship() != null ? getRelationship().hashCode() : 0);
        return result;
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", type = ").append(getType())
                .append(", userId= ").append(getUser() == null ? null : getRelationship().getId())
                .append(", relationshipId = ")
                .append(getRelationship() == null ? null : getRelationship().getId())
                .append("}")
                .toString();
    }
}


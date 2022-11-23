package com.ku.people.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "details")
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "relationship_type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relationship_id")
    private Relationship relationship;

    public Detail() {
    }

    public Detail(Long id, String type, User user, Relationship relationship) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.relationship = relationship;
    }

    public Detail(Long id) {
        this.id = id;
    }

    public Detail(String type, Relationship relationship) {
        this.type = type;
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
        return getUser() == null ? aThat.getUser() == null : getUser().equals(aThat.getUser());
    }

    @Override
    public int hashCode() {
        int result = 1, prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getType() != null ? getType().hashCode() : 0);
        result = prime * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", type = ").append(getType())
                .append(", userId = ").append(getUser() == null ? null : getUser().getId())
                .append(", relationshipId = ").append(getRelationship() == null ? null : getRelationship().getId())
                .append(" }")
                .toString();
    }
}


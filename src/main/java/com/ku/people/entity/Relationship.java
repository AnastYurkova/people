package com.ku.people.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table( name = "relationships")
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_at_utc")
    private LocalDate createdAtUtc;
    @Column(name = "relationship_status")
    @Enumerated(EnumType.STRING)
    private RelationshipStatus status;
    @OneToMany(mappedBy="relationship", fetch = FetchType.LAZY)
    private Set<Detail> details;

    public Relationship() {
    }

    public Relationship(Long id, LocalDate createdAtUtc, RelationshipStatus status, Set<Detail> details) {
        this.id = id;
        this.createdAtUtc = createdAtUtc;
        this.status = status;
        this.details = details;
    }

    public Relationship(Long id, LocalDate createdAtUtc, RelationshipStatus status) {
        this.id = id;
        this.createdAtUtc = createdAtUtc;
        this.status = status;
    }

    public Relationship(Long id) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAtUtc() {
        return createdAtUtc;
    }

    public void setCreatedAtUtc(LocalDate createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }

    public RelationshipStatus getStatus() {
        return status;
    }

    public void setStatus(RelationshipStatus status) {
        this.status = status;
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
        Relationship aThat = (Relationship) obj;

        if (getId() == null) {
            if (aThat.getId() != null) {
                return false;
            }
        } else if (!getId().equals(aThat.getId())) {
            return false;
        }

        if (getCreatedAtUtc() == null) {
            if (aThat.getCreatedAtUtc() != null) {
                return false;
            }
        } else if (!getCreatedAtUtc().equals(aThat.getCreatedAtUtc())) {
            return false;
        }

        if (getStatus() == null) {
            return aThat.getStatus() == null;
        } else return getStatus().equals(aThat.getStatus());
    }

    @Override
    public int hashCode() {
        int result = 1, prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getCreatedAtUtc() != null ? getCreatedAtUtc().hashCode() : 0);
        result = prime * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", createdAtUtc = ").append(getCreatedAtUtc())
                .append(", status = ").append(getStatus())
                .append(" }")
                .toString();
    }
}

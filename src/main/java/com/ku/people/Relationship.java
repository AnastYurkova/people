package com.ku.people;

import java.time.LocalDate;
import java.util.List;

public class Relationship {
    private Long id;
    private LocalDate createdAtUtc;
    private String status;
    private List<Detail> details;

    public Relationship() {
    }

    public Relationship(Long id, LocalDate createdAtUtc, String status, List<Detail> details) {
        this.id = id;
        this.createdAtUtc = createdAtUtc;
        this.status = status;
        this.details = details;
    }

    public Relationship(Long id) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAtUtc() {
        return createdAtUtc;
    }

    public void setCreatedAtUtc(LocalDate createdAtUtc) {
        this.createdAtUtc = createdAtUtc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
            if (aThat.getStatus() != null) {
                return false;
            }
        } else if (!getStatus().equals(aThat.getStatus())) {
            return false;
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
        result = prime * result + (getCreatedAtUtc() != null ? getCreatedAtUtc().hashCode() : 0);
        result = prime * result + (getStatus() != null ? getStatus().hashCode() : 0);
        if (getDetails() != null) {
            for (int i = 0; i < getDetails().size(); i++) {
                Detail detail = getDetails().get(i);
                result = prime * result + (detail.getId() != null ? detail.getId().hashCode() : 0);
            }
        }
        return result;
    }

    public String toString() {
        return new StringBuilder(getClass().getSimpleName())
                .append(" { id = ").append(getId())
                .append(", createdAtUtc = ").append(getCreatedAtUtc())
                .append(", status = ").append(getStatus())
                .append("}, {Details id = ")
                .append(getDetails() == null
                        ? List.of()
                        : getDetails().stream()
                            .map(Detail::getId)
                            .toList())
                .append("}")
                .toString();
    }
}

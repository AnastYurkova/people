package com.ku.people;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Relationship {
    private Long id;
    private LocalDate createdAtUTC;
    private String status;
    private List<Detail> details;

    public Relationship() {
    }

    public Relationship(Long id, LocalDate createdAtUTC, String status, List<Detail> details) {
        this.id = id;
        this.createdAtUTC = createdAtUTC;
        this.status = status;
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

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAtUTC() {
        return createdAtUTC;
    }

    public void setCreatedAtUTC(LocalDate createdAtUTC) {
        this.createdAtUTC = createdAtUTC;
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

        if (getCreatedAtUTC() == null) {
            if (aThat.getCreatedAtUTC() != null) {
                return false;
            }
        } else if (!getCreatedAtUTC().equals(aThat.getCreatedAtUTC())) {
            return false;
        }

        if (getDetails() == null && aThat.getDetails() != null) {
            return false;
        } else if (aThat.getDetails() == null && getDetails() != null) {
            return false;
        } else if (aThat.getDetails() != null && getDetails() != null) {
            for (int i = 0; i < getDetails().size(); i++) {
                if (getDetails().size() != aThat.getDetails().size()) {
                    return false;
                }
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
        return getStatus() == null ? aThat.getStatus() == null : getStatus().equals(aThat.getStatus());
    }

    @Override
    public int hashCode() {
        int result = 31;
        int prime = 31;
        result = prime * result + (getId() != null ? getId().hashCode() : 0);
        result = prime * result + (getCreatedAtUTC() != null ? getCreatedAtUTC().hashCode() : 0);
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
                .append(", createdAtUTC = ").append(getCreatedAtUTC())
                .append(", status = ").append(getStatus())
                .append("}, {Details id = ").append(getDetails().stream()
                        .map(Detail::getId)
                        .toList())
                .append("}")
                .toString();
    }
}

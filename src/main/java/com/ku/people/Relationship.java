package com.ku.people;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Relationship {
    private Long id;
    private LocalDate createdAtUTC;
    private String status;
    private List <Detail> details;

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
        Relationship relationship = (Relationship) o;

        if (getId() == null) {
            if (relationship.getId() != null) {
                return false;
            }
        } else if (!getId().equals(relationship.getId())) {
            return false;
        }

        if (getCreatedAtUTC() == null) {
            if (relationship.getCreatedAtUTC() != null) {
                return false;
            }
        } else if (!getCreatedAtUTC().equals(relationship.getCreatedAtUTC())) {
            return false;
        }

        for (int i=0; i < getDetails().size(); i++) {
            Detail detail= getDetails().get(i);
            if (detail.getId()==null){
                if (relationship.getDetails().get(i).getId()!=null){
                    return false;
                }
            } else if(!detail.getId().equals(relationship.getDetails().get(i).getId())){
                return false;
            }
        }

        return getStatus() == null ? relationship.getStatus() == null : getStatus().equals(relationship.getStatus());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result =  31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getCreatedAtUTC() != null ? getCreatedAtUTC().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        for (int i=0; i < getDetails().size(); i++) {
            Detail detail =getDetails().get(i);
            result = 31 * result + (detail.getId() != null ? detail.getId().hashCode() : 0);
        }
        return result;
    }

    public String toString()
    {
        return new StringBuilder(getClass().getSimpleName()).append(" { Id = ").append(getId())
                .append(", CreatedAtUTC = ").append(getCreatedAtUTC()).append(", Status = ").append(getStatus())
                .append("}, {Details = ").append(getDetails().stream().map(detail-> detail.getId()).collect(Collectors.toList()))
                .append("}").toString();
    }
}

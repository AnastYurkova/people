package com.ku.people;

import java.time.LocalDate;

public class Relationship {
    private Long id;
    private LocalDate createdAtUTC;
    private String status;

    public Relationship() {
    }

    public Relationship(Long id, LocalDate createdAtUTC, String status) {
        this.id = id;
        this.createdAtUTC = createdAtUTC;
        this.status = status;
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

        return getStatus() == null ? relationship.getStatus() == null : getStatus().equals(relationship.getStatus());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result =  31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getCreatedAtUTC() != null ? getCreatedAtUTC().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }

    public String toString()
    {
        return getClass().getSimpleName()+" "+"{"+ "id = " +getId()+", "+ "createdAtUTC = " +getCreatedAtUTC()+ ", " +"status = " +getStatus()+" }";
    }



}

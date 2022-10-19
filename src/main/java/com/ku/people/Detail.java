package com.ku.people;

import java.util.List;
import java.util.stream.Collectors;

public class Detail {
    private Long id;
    private String type;
    private User user;
    private Relationship relationship;

    private List<Relationship> relationships;

    public Detail() {
    }

    public Detail(Long id, String type, User user, Relationship relationship, List<Relationship> relationships) {
        this.id = id;
        this.type = type;
        this.user = user;
        this.relationship = relationship;
        this.relationships = relationships;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
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
        Detail detail = (Detail) o;

        if (getId() == null) {
            if (detail.getId() != null) {
                return false;
            }
        } else if (!getId().equals(detail.getId())) {
            return false;
        }

        if (getType() == null) {
            if (detail.getType() != null) {
                return false;
            }
        } else if (!getType().equals(detail.getType())) {
            return false;
        }

        if (getUser() == null) {
            if (detail.getUser() != null) {
                return false;
            }
        } else if (!getUser().equals(detail.getUser())) {
            return false;
        }

        for (int i=0; i < getRelationships().size(); i++) {
            Relationship relationship= getRelationships().get(i);
            if (relationship.getId()==null){
                if (detail.getRelationships().get(i).getId()!=null){
                    return false;
                }
            } else if(!relationship.getId().equals(detail.getRelationships().get(i).getId())){
                return false;
            }
        }
        return getRelationship() == null ? detail.getRelationship() == null : getRelationship().equals(detail.getRelationship());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result =  31 * result + (getId() != null ? getId().hashCode() : 0);
        result =  31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getRelationship() != null ? getRelationship().hashCode() : 0);
        for (int i=0; i < getRelationships().size(); i++) {
            Relationship relationship =getRelationships().get(i);
            result = 31 * result + (relationship.getId() != null ? relationship.getId().hashCode() : 0);
        }
        return result;
    }

    public String toString()
    {
        return new StringBuilder(getClass().getSimpleName()).append(" { Id = ").append(getId()).append(", Type = ").append(getType())
                .append("} contains User = ").append(getUser()).append("}} contains {Relationship id =")
                .append(getRelationships().stream().map(relationship -> relationship.getId()).collect(Collectors.toList()))
                .append("}").toString();
    }
}


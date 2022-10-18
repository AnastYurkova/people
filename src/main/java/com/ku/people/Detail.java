package com.ku.people;

public class Detail {
    private Long id;
    private String type;
    private Long userId;
    private Long relationshipId;

    public Detail() {
    }

    public Detail(Long id, String type, Long userId, Long relationshipId) {
        this.id = id;
        this.type = type;
        this.userId = userId;
        this.relationshipId = relationshipId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
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

        if (getUserId() == null) {
            if (detail.getUserId() != null) {
                return false;
            }
        } else if (!getUserId().equals(detail.getUserId())) {
            return false;
        }

        return getRelationshipId() == null ? detail.getRelationshipId() == null : getRelationshipId().equals(detail.getRelationshipId());
    }

    @Override
    public int hashCode() {
        int result = 31;
        result =  31 * result + (getId() != null ? getId().hashCode() : 0);
        result =  31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getRelationshipId() != null ? getRelationshipId().hashCode() : 0);
        return result;
    }

    public String toString()
    {
        return getClass().getSimpleName()+" "+"{"+ "id = " +getId()+" "+ "type = " +getType()+ ", " +"userId = " +getUserId()+ ", "+"relationshipId = " +getRelationshipId()+"}";
    }


}


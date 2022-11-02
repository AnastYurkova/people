package com.ku.people;

public enum RelationshipType {
    HUSBAND ("husband"),
    WIFE ("wife"),
    SON ("son"),
    DAUGHTER ("daughter"),
    FATHER ("father"),
    GRANDMOTHER ("grandmother"),
    GRANDFATHER ("grandfather"),
    MOTHER ("mother");

    private final String relationshipType;

    RelationshipType (String relationshipType){
        this.relationshipType = relationshipType;
    }

    public String getRelationshipType() {
        return relationshipType;
    }
}

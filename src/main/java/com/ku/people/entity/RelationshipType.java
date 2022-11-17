package com.ku.people.entity;

public enum RelationshipType {
    HUSBAND ("husband"),
    WIFE ("wife"),
    SON ("son"),
    DAUGHTER ("daughter"),
    FATHER ("father"),
    GRANDMOTHER ("grandmother"),
    GRANDFATHER ("grandfather"),
    MOTHER ("mother");

    private final String value;

    RelationshipType (String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

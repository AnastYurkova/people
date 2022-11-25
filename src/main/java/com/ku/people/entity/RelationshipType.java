package com.ku.people.entity;

public enum RelationshipType {
    HUSBAND ("HUSBAND"),
    WIFE ("WIFE"),
    SON ("SON"),
    DAUGHTER ("DAUGHTER"),
    FATHER ("FATHER"),
    GRANDMOTHER ("GRANDMOTHER"),
    GRANDFATHER ("GRANDFATHER"),
    MOTHER ("MOTHER");

    private final String value;

    RelationshipType (String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

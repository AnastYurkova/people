package com.ku.people.entity;

public enum RelationshipStatus {
    SAD ("SAD"),
    OK ("OK"),
    HAPPY ("HAPPY");
    private final String value;

    RelationshipStatus (String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

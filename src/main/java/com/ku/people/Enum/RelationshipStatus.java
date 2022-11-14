package com.ku.people.Enum;

public enum RelationshipStatus {
    SAD ("sad"),
    OK ("ok"),
    HAPPY ("happy");
    private final String value;

    RelationshipStatus (String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

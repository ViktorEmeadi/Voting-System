package com.example.Voting.System.data.model;

public enum VoteCategory {
    PRESIDENCY("PRESIDENCY"), GOVERNORSHIP("GOVERNORSHIP"),
    HOUSE_OF_REPS("HOUSE_OF_REPS"), SENATE("SENATE");
    private final String name;

    VoteCategory(String name) {
        this.name = name;
    }
}

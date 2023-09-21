package com.example.Voting.System.data.model;

import lombok.Getter;

@Getter
public enum Party {
    PDP("PDP"),
    LP("LP"),
    APC("APC");
    private final String name;

    Party(String name) {
        this.name = name;
    }
}

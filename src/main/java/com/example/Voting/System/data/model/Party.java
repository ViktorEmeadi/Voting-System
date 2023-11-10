package com.example.Voting.System.data.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


public enum Party {
    APC("APC"), PDP("PDP"), LP("LP");
    private final String name;

    Party(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

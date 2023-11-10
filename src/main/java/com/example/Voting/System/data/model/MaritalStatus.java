package com.example.Voting.System.data.model;

import lombok.Getter;

@Getter
public enum MaritalStatus {
    SINGLE("SINGLE"), MARRIED("MARRIED"), DIVORCED("DIVORCED"), COMPLICATED("COMPLICATED");
    private final String name;

    MaritalStatus(String name) {
        this.name = name;
    }
}

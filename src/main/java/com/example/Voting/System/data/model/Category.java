package com.example.Voting.System.data.model;

import lombok.Getter;

@Getter
public enum Category {
    GOVERNOR("GOVERNOR"),
    PRESIDENT("PRESIDENT");
    private final String name;

    Category(String name) {
        this.name = name;
    }
}

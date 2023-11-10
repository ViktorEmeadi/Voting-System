package com.example.Voting.System.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Location {
    private String country;
    private String state;
    private String city;
    private String street;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

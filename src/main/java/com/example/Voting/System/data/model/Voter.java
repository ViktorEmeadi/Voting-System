package com.example.Voting.System.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voter {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private String location;
    @Column(unique = true)
    private String bvn;
    private LocalDate dateOfBirth;
    private Integer age;
    private String password;
    private boolean votedForPresident;
    private boolean votedForGovernor;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

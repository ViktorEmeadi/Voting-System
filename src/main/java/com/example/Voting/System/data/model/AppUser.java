package com.example.Voting.System.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String password;
    private String phoneNumber;
    private MaritalStatus maritalStatus;
    private String email;
    private String BVN;
    @OneToOne
    private Location location;
    private boolean hasVotedForPresident;
    private boolean hasVotedForGovernor;
    private boolean hasVotedForSenate;
    private boolean hasVotedForHouseOfReps;

}

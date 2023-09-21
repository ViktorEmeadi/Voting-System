package com.example.Voting.System.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoterRequest {
    @NotBlank(message = "Firstname cannot be blank")
    private String firstName;
    @NotBlank(message = "Lastname cannot be blank")
    private String lastName;
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;
    @NotBlank(message = "Location cannot be blank")
    private String location;
    @NotBlank(message = "BVN cannot be blank")
    private String bvn;
    @NotBlank(message = "Date of Birth cannot be blank")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    private boolean votedForPresident;
    private boolean votedForGovernor;
}

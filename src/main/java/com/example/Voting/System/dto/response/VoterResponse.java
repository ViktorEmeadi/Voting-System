package com.example.Voting.System.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoterResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String location;
    private boolean votedForPresident;
    private boolean votedForGovernor;
    private Long id;
    private String bvn;
    private Integer age;
}

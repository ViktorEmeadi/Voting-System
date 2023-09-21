package com.example.Voting.System.dto.response;

import com.example.Voting.System.data.model.Voter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private String name;
    private String email;
    private String location;
    private String password;
    private Long id;
}

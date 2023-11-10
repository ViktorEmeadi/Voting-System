package com.example.Voting.System.dto.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateBVNResponse {
    private String status;
    private String message;
    private Data data;

}

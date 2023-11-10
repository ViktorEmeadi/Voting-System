package com.example.Voting.System.dto.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreateAccountRequest {
    private String BVN;
    private String password;
}

package com.example.Voting.System.dto.request;

import lombok.Data;

@Data
public class CastVoteRequest {
    private String party;
    private String voteCategory;
    private String BVN;
    private String password;
}

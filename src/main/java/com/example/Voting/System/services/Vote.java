package com.example.Voting.System.services;

import com.example.Voting.System.dto.request.VoteRequest;
import com.example.Voting.System.dto.request.VoterRequest;
import com.example.Voting.System.dto.response.VoteResponse;

public interface Vote {
    VoteResponse castVote(VoterRequest request);
}

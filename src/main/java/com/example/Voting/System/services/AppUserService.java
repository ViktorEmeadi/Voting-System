package com.example.Voting.System.services;

import com.example.Voting.System.dto.request.CastVoteRequest;
import com.example.Voting.System.dto.request.CreateAccountRequest;
import com.example.Voting.System.dto.response.CastVoteResponse;
import com.example.Voting.System.dto.response.CreateAccountResponse;
import com.example.Voting.System.dto.response.ViewResultResponse;
import org.hibernate.annotations.Cascade;

public interface AppUserService {
    CreateAccountResponse createAccount(CreateAccountRequest request);
    CastVoteResponse castVoteForPresidency(CastVoteRequest request);
    CastVoteResponse castVoteForGovernorship(CastVoteRequest request);
    CastVoteResponse castVoteForSenate(CastVoteRequest request);
    CastVoteResponse castVoteForHouseOfReps(CastVoteRequest request);
    ViewResultResponse viewPresidentialResult();
    ViewResultResponse viewGovernorshipResult();
    ViewResultResponse viewSenateResult();
    ViewResultResponse viewHouseOfRepsResult();
}

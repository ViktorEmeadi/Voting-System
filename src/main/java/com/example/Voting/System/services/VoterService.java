package com.example.Voting.System.services;

import com.example.Voting.System.dto.request.VoteRequest;
import com.example.Voting.System.dto.request.VoterRequest;
import com.example.Voting.System.dto.response.ResultResponse;
import com.example.Voting.System.dto.response.VoterResponse;

import java.util.List;

public interface VoterService {
    VoterResponse register(VoterRequest request);
    VoterResponse login(VoterRequest request);
    VoterResponse editVoterDetails(VoterRequest request);
    VoterResponse findVoterByBvn(VoterRequest request);
    List<VoterResponse> viewAllVoters(VoterRequest request);
    VoterResponse castVote(VoteRequest request);
    ResultResponse viewResultPresidential();
    ResultResponse viewResultGovernorship();

}

package com.example.Voting.System.services;

import com.example.Voting.System.dto.request.CandidateRequest;
import com.example.Voting.System.dto.response.CandidateResponse;

import java.util.List;

public interface CandidateService {
    CandidateResponse addCandidate(CandidateRequest request);
    CandidateResponse editCandidate(CandidateRequest request);
    CandidateResponse getCandidateByPartyAndCategory(CandidateRequest request);
    List<CandidateResponse> viewAllCandidates(CandidateRequest request);
    void removeCandidate(CandidateRequest request);
}

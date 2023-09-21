package com.example.Voting.System.services;

import com.example.Voting.System.dto.request.AdminRequest;
import com.example.Voting.System.dto.request.CandidateRequest;
import com.example.Voting.System.dto.response.AdminResponse;
import com.example.Voting.System.dto.response.CandidateResponse;

public interface AdminService {
    AdminResponse register(AdminRequest request);
    AdminResponse login(AdminRequest request);
    CandidateResponse setCandidate(CandidateRequest request);
    CandidateResponse editCandidate(CandidateRequest request);
    void removeCandidate(CandidateRequest request);
}

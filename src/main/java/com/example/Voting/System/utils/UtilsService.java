package com.example.Voting.System.utils;

import com.example.Voting.System.data.model.AppUser;
import com.example.Voting.System.data.model.Candidate;
import com.example.Voting.System.data.model.Party;
import com.example.Voting.System.data.model.VoteCategory;
import com.example.Voting.System.data.repositories.CandidateRepository;
import com.example.Voting.System.data.repositories.LocationRepository;
import com.example.Voting.System.data.repositories.UserRepository;
import com.example.Voting.System.dto.request.CastVoteRequest;
import com.example.Voting.System.exceptions.PartyException;
import com.example.Voting.System.exceptions.UserException;
import com.example.Voting.System.services.AppUserService;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.Optional;

@Component
public class UtilsService {
    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final LocationRepository locationRepository;

    public UtilsService(UserRepository userRepository, CandidateRepository candidateRepository, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.candidateRepository = candidateRepository;
        this.locationRepository = locationRepository;
    }
    public Candidate checkCandidateValidity(CastVoteRequest request){
        boolean partyIsValid = EnumSet.allOf(Party.class)
                .stream()
                .anyMatch(party -> party.getName().equals(request.getParty()));
        if(!partyIsValid) throw new PartyException("Party not found");
        Optional<Candidate> candidate = candidateRepository.findByVoteCategoryAndParty(VoteCategory.valueOf(request.getVoteCategory()),
                Party.valueOf(request.getParty()));
        if(candidate.isEmpty()) throw new PartyException("Candidate not found");
        return candidate.get();
    }
    public AppUser validateUserCredentials(CastVoteRequest request){
        Optional<AppUser> user = userRepository.findByBvn(request.getBVN());
        if(user.isEmpty()) throw new UserException("User not found");
        AppUser foundUser = user.get();
        if(!user.get().getPassword().equals(request.getPassword())) throw new UserException("Incorrect user details");
        return user.get();
    }
}

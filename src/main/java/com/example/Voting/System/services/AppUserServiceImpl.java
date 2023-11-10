

package com.example.Voting.System.services;

import com.example.Voting.System.data.model.*;
import com.example.Voting.System.data.repositories.CandidateRepository;
import com.example.Voting.System.data.repositories.LocationRepository;
import com.example.Voting.System.data.repositories.UserRepository;
import com.example.Voting.System.dto.request.CastVoteRequest;
import com.example.Voting.System.dto.request.CreateAccountRequest;
import com.example.Voting.System.dto.response.CastVoteResponse;
import com.example.Voting.System.dto.response.CreateAccountResponse;
import com.example.Voting.System.dto.response.ValidateBVNResponse;
import com.example.Voting.System.dto.response.ViewResultResponse;
import com.example.Voting.System.exceptions.BVNException;
import com.example.Voting.System.exceptions.CastVoteException;
import com.example.Voting.System.exceptions.UserException;
import com.example.Voting.System.utils.UtilsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.Voting.System.data.model.VoteCategory.*;

@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{
    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final LocationRepository locationRepository;
    private final UtilsService utils;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest request) {
        if (userRepository.existsByBvn(request.getBVN())){
            throw new UserException("User with BVN already registered", 400);
        }
        ValidateBVNResponse validateBVNResponse = validateBVN(request.getBVN());
        if (validateBVNResponse.getStatus().equals("Error")){
            throw new BVNException("BVN Error", 400);
        }
        AppUser user = buildAppUser(request, validateBVNResponse);
        userRepository.save(user);
        return new CreateAccountResponse("Account has been created");
    }

    @Override
    public CastVoteResponse castVoteForPresidency(CastVoteRequest request) {
        AppUser user = validateUserCredentials(request);
        if (user.isHasVotedForPresident()){
            throw new CastVoteException("You have casted your vote already", 400);
        }
        checkCategory(request, PRESIDENCY);
        Candidate candidate = utils.checkCandidateValidity(request);
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        user.setHasVotedForPresident(true);
        userRepository.save(user);
        candidateRepository.save(candidate);
        return castVoteResponse();
    }

    private static void checkCategory(CastVoteRequest request, VoteCategory category) {
        if(!VoteCategory.valueOf(request.getVoteCategory().toUpperCase()).equals(category)) {
            throw new CastVoteException("Invalid vote category", 400);
        }
    }

    private CastVoteResponse castVoteResponse(){
        return new CastVoteResponse("You have successfully casted your vote");
    }

    @Override
    public CastVoteResponse castVoteForGovernorship(CastVoteRequest request) {
        AppUser user = validateUserCredentials(request);
        if (user.isHasVotedForGovernor()){
            throw new CastVoteException("You have casted your vote already", 400);
        }
        checkCategory(request, VoteCategory.GOVERNORSHIP);
        Candidate candidate = utils.checkCandidateValidity(request);
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        user.setHasVotedForGovernor(true);
        userRepository.save(user);
        candidateRepository.save(candidate);
        return castVoteResponse();
    }

    @Override
    public CastVoteResponse castVoteForSenate(CastVoteRequest request) {
        AppUser user = validateUserCredentials(request);
        if (user.isHasVotedForSenate()){
            throw new CastVoteException("You have casted your vote already", 400);
        }
        checkCategory(request, VoteCategory.SENATE);
        Candidate candidate = utils.checkCandidateValidity(request);
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        user.setHasVotedForSenate(true);
        return castVoteResponse();
    }

    @Override
    public CastVoteResponse castVoteForHouseOfReps(CastVoteRequest request) {
        AppUser user = validateUserCredentials(request);
        if (user.isHasVotedForHouseOfReps()){
            throw new CastVoteException("You have casted your vote already",400);
        }
        checkCategory(request, VoteCategory.HOUSE_OF_REPS);
        Candidate candidate = utils.checkCandidateValidity(request);
        candidate.setVoteCount(candidate.getVoteCount() + 1);
        user.setHasVotedForHouseOfReps(true);
        return castVoteResponse();
    }

    @Override
    public ViewResultResponse viewPresidentialResult() {
        return viewResult(PRESIDENCY);
    }

    private ViewResultResponse viewResult(VoteCategory category) {
        Long total = 0L;
        Optional<List<Candidate>> candidateList = candidateRepository.findCandidateByVoteCategory(category);
        if(candidateList.isEmpty()) throw new CastVoteException("Candidate not found", 400);

        for (Candidate candidate : candidateList.get()){
            if (candidate.getVoteCategory() == category) total += candidate.getVoteCount();
        }

        List<Candidate> candidates = candidateList.get();
        Map<String, String> result = new HashMap<>();
        for(Candidate candidate : candidates){
            result.put(candidate.getParty().toString(),
                    String.valueOf(candidate.getVoteCount() /total * 100));
        }
        return new ViewResultResponse(result);
    }

    @Override
    public ViewResultResponse viewGovernorshipResult() {
        return viewResult(GOVERNORSHIP);
    }

    @Override
    public ViewResultResponse viewSenateResult() {
        return viewResult(SENATE);
    }

    @Override
    public ViewResultResponse viewHouseOfRepsResult() {
        return viewResult(HOUSE_OF_REPS);
    }
    private ValidateBVNResponse validateBVN(String bvn) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (bvn == null || bvn.isEmpty()) {
                throw new IllegalArgumentException("BVN is empty or null");
            }
            return objectMapper.readValue(bvn, ValidateBVNResponse.class);
        } catch (BVNException | JsonProcessingException e) {
            e.printStackTrace();
            return createErrorResponse();
        }
    }

    private ValidateBVNResponse createErrorResponse() {
        ValidateBVNResponse errorResponse = new ValidateBVNResponse();
        errorResponse.setStatus("Error");
        errorResponse.setMessage("BVN validation failed");
        return errorResponse;
    }
    private AppUser buildAppUser(CreateAccountRequest request, ValidateBVNResponse response){
        Location location = new Location();
        location.setCountry(response.getData().getCountry());
        location.setState(response.getData().getState());
        location.setCity(response.getData().getCity());
        location.setStreet(response.getData().getStreet());
        locationRepository.save(location);

        return AppUser.builder()
                .BVN(response.getData().getBVN())
                .email(response.getData().getEmail())
                .firstName(response.getData().getFirstName())
                .lastName(response.getData().getLastName())
                .gender(Gender.valueOf(response.getData().getGender()))
                .email(response.getData().getEmail())
                .phoneNumber(response.getData().getPhoneNumber())
                .location(location)
                .maritalStatus(MaritalStatus.valueOf(response.getData().getMaritalStatus()))
                .build();
    }
    public AppUser validateUserCredentials(CastVoteRequest request){
        Optional<AppUser> user = userRepository.findByBvn(request.getBVN());
        if (user.isEmpty()) throw new UserException("Incorrect details", 400);
        AppUser foundUser = user.get();
        if(!foundUser.getPassword().equals(request.getPassword())){
            throw new UserException("Incorrect details", 400);
        }
        return foundUser;
    }
}

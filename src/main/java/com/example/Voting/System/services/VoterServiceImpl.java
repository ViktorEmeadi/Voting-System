package com.example.Voting.System.services;

import com.example.Voting.System.data.model.Voter;
import com.example.Voting.System.data.repositories.VoterRepository;
import com.example.Voting.System.dto.request.VoteRequest;
import com.example.Voting.System.dto.request.VoterRequest;
import com.example.Voting.System.dto.response.ResultResponse;
import com.example.Voting.System.dto.response.VoterResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoterServiceImpl implements VoterService{
    private final VoterRepository repository;

    public VoterServiceImpl(VoterRepository repository) {
        this.repository = repository;
    }

    @Override
    public VoterResponse register(VoterRequest request) {
        Integer age = Period.between(request.getDateOfBirth(), LocalDate.now()).getYears();
        Optional<Voter> foundVoter = repository.findByEmailAndBvn(request.getEmail(), request.getBvn());
        VoterResponse response = new VoterResponse();
        if (foundVoter.isEmpty()) {
            Voter voter = Voter.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .location(request.getLocation())
                    .dateOfBirth(request.getDateOfBirth())
                    .password(request.getPassword())
                    .age(age)
                    .bvn(request.getBvn())
                    .build();
            Voter registeredVoter = repository.save(voter);
            BeanUtils.copyProperties(registeredVoter, response);
        }else throw new RuntimeException("BVN and email address already in use");
        return response;
    }

    @Override
    public VoterResponse login(VoterRequest request) {
        Optional<Voter> foundVoter = repository.findVoterByPasswordAndEmail(request.getPassword(), request.getEmail());
        VoterResponse response = new VoterResponse();
        if(foundVoter.isPresent()){
            BeanUtils.copyProperties(foundVoter.get(), response);
        }else throw new RuntimeException("Voter not registered");
        return response;
    }

    @Override
    public VoterResponse editVoterDetails(VoterRequest request) {
        VoterResponse response = new VoterResponse();
        Optional<Voter> foundVoter = repository.findByBvn(request.getBvn());
        if(foundVoter.isPresent()){
            Voter voter = foundVoter.get();
            voter = Voter.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getPassword())
                    .password(request.getPassword())
                    .build();
            BeanUtils.copyProperties(voter, response);
        }else throw new RuntimeException("BVN not found");
        return response;
    }

    @Override
    public VoterResponse findVoterByBvn(VoterRequest request) {
        Optional<Voter> foundVoter = repository.findByBvn(request.getBvn());
        VoterResponse response = new VoterResponse();
        if (foundVoter.isPresent()){
            BeanUtils.copyProperties(foundVoter.get(), response);
        }else throw new RuntimeException("BVN not recognized");
        return response;
    }

    @Override
    public List<VoterResponse> viewAllVoters(VoterRequest request) {
        List<Voter> voters = repository.findAll();
        return voters.stream()
                .map(voter -> VoterResponse.builder()
                        .firstName(voter.getFirstName())
                        .lastName(voter.getLastName())
                        .email(voter.getEmail())
                        .phoneNumber(voter.getPhoneNumber())
                        .location(voter.getLocation())
                        .bvn(voter.getBvn())
                        .age(voter.getAge())
                        .build())
                .toList();
    }

    @Override
    public VoterResponse castVote(VoteRequest request) {
        return null;
    }

    @Override
    public ResultResponse viewResultPresidential() {
        return null;
    }

    @Override
    public ResultResponse viewResultGovernorship() {
        return null;
    }
}

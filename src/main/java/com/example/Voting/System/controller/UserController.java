package com.example.Voting.System.controller;

import com.example.Voting.System.dto.request.CastVoteRequest;
import com.example.Voting.System.dto.request.CreateAccountRequest;
import com.example.Voting.System.dto.response.CastVoteResponse;
import com.example.Voting.System.dto.response.CreateAccountResponse;
import com.example.Voting.System.services.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/voting-system")
@RequiredArgsConstructor
public class UserController {
    private final AppUserService userService;
    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request){
        CreateAccountResponse response = userService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/presidency/vote")
    public ResponseEntity<?> castVotePresidency(@RequestBody CastVoteRequest request){
        CastVoteResponse response = userService.castVoteForPresidency(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/governorship/vote")
    public ResponseEntity<?> castVoteGorvernorship(@RequestBody CastVoteRequest request){
        CastVoteResponse response = userService.castVoteForGovernorship(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/senate/vote")
    public ResponseEntity<?> castVoteSenate(@RequestBody CastVoteRequest request){
        CastVoteResponse response = userService.castVoteForSenate(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/house-of-reps/vote")
    public ResponseEntity<?> castVoteHouseOfReps(@RequestBody CastVoteRequest request){
        CastVoteResponse response = userService.castVoteForHouseOfReps(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/presidential/result")
    public ResponseEntity<?> viewPresidentialResult(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.viewPresidentialResult());
    }
    @GetMapping("/governorship/result")
    public ResponseEntity<?> viewGovernorshipResult(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.viewGovernorshipResult());
    }
    @GetMapping("/senate/result")
    public ResponseEntity<?> viewSenateResult(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.viewSenateResult());
    }
    @GetMapping("house-of-reps/result")
    public ResponseEntity<?> viewHouseOfRepsResult(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.viewHouseOfRepsResult());
    }

}

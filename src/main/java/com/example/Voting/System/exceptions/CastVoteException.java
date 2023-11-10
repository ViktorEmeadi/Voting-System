package com.example.Voting.System.exceptions;

public class CastVoteException extends VoteException{
    public CastVoteException(String message, int statusCode){
        super(message, statusCode);
    }
}

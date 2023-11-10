package com.example.Voting.System.exceptions;

public class PartyException extends VoteException{
    public PartyException(String message, int statusCode){
        super(message, statusCode);
    }
}

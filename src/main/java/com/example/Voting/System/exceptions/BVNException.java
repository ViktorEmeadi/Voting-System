package com.example.Voting.System.exceptions;

public class BVNException extends VoteException{
    public BVNException(String message, int statusCode){
        super(message, statusCode);
    }
}

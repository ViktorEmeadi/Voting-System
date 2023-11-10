package com.example.Voting.System.exceptions;

public class VoteException extends RuntimeException{
    private final int statusCode;

    public VoteException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}

package com.example.Voting.System.exceptions;

import org.apache.catalina.User;

public class UserException extends VoteException{
    public UserException(String message, int statusCode){
        super(message, statusCode);
    }
}

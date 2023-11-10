package com.example.Voting.System.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.UnknownHostException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(VoteException.class)
    public ResponseEntity<?> handleVoteException(VoteException voteException){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(voteException.getMessage())
                .timestamp(LocalDateTime.now())
                .status(false)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnknownHostException.class)
    public ResponseEntity<?> handleUnknownException(UnknownHostException unknownHostException){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(unknownHostException.getMessage())
                .timestamp(LocalDateTime.now())
                .status(false)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler (JsonProcessingException.class)
    public ResponseEntity<?> handleJasonProcessingException(JsonProcessingException jsonProcessingException){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(jsonProcessingException.getMessage())
                .timestamp(LocalDateTime.now())
                .status(false)
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}

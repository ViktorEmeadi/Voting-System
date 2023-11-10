package com.example.Voting.System.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class ErrorMessage {
    private String message;
    private LocalDateTime timestamp;
    private boolean status;
}

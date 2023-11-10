package com.example.Voting.System.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;
@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class ViewResultResponse {
    Map<String, String> result;
}

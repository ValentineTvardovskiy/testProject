package com.example.testproject.domain.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VoteResponse(Map<String, Integer> votes, Boolean isCorrect) {
}

package com.example.testproject.domain.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateQuestionRequest(String text, List<String> answers, QuestionType type, String correctAnswer) {
}

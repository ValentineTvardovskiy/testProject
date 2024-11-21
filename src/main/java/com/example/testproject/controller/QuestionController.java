package com.example.testproject.controller;

import com.example.testproject.domain.rest.CreateQuestionRequest;
import com.example.testproject.domain.rest.CreateQuestionResponse;
import com.example.testproject.domain.rest.QuestionResponse;
import com.example.testproject.domain.rest.VoteRequest;
import com.example.testproject.domain.rest.VoteResponse;
import com.example.testproject.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

  private final QuestionService questionService;

  @PostMapping
  public ResponseEntity<CreateQuestionResponse> addQuestion(@RequestBody CreateQuestionRequest request) {
    return questionService.storeQuestion(request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuestionResponse> getQuestion(@PathVariable Long id) {
    return questionService.getQuestion(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());
  }

  @PostMapping("/{id}/vote")
  public ResponseEntity<VoteResponse> vote(@PathVariable Long id, @RequestBody VoteRequest request) {
    return questionService.vote(id, request)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.badRequest().build());
  }
}

package com.example.testproject.domain.rest;

import com.example.testproject.domain.db.TriviaQuestion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TriviaQuestionResponse extends QuestionResponse {
  private String correctAnswer;

  public static TriviaQuestionResponse from(TriviaQuestion question) {
    TriviaQuestionResponse response = new TriviaQuestionResponse();
    response.setCommonFields(question);
    response.setCorrectAnswer(question.getCorrectAnswer());
    return response;
  }
}

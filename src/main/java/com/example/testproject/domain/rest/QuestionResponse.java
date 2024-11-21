package com.example.testproject.domain.rest;

import com.example.testproject.domain.db.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {
  private String text;
  private List<String> answers;
  private QuestionType type;

  protected void setCommonFields(Question question) {
    this.setText(question.getText());
    this.setAnswers(question.getAnswers());
    this.setType(question.getType());
  }

  public static QuestionResponse from(Question question) {
    QuestionResponse response = new QuestionResponse();
    response.setCommonFields(question);
    return response;
  }
}

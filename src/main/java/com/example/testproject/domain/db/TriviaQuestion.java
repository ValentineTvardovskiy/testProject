package com.example.testproject.domain.db;

import com.example.testproject.domain.rest.QuestionType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("TRIVIA")
@NoArgsConstructor
@Getter
public class TriviaQuestion extends Question {

  private String correctAnswer;

  public TriviaQuestion(String text, List<String> answers, String correctAnswer) {
    super(text, answers);
    this.correctAnswer = correctAnswer;
  }

  @Override
  public Boolean isCorrectAnswer(String answer) {
    return correctAnswer.equals(answer);
  }

  @Override
  public QuestionType getType() {
    return QuestionType.TRIVIA;
  }
}

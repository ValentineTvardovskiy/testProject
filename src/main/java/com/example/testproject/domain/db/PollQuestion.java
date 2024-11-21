package com.example.testproject.domain.db;

import com.example.testproject.domain.rest.QuestionType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("POLL")
@NoArgsConstructor
public class PollQuestion extends Question {

  public PollQuestion(String text, List<String> answers) {
    super(text, answers);
  }

  @Override
  public QuestionType getType() {
    return QuestionType.POLL;
  }
}

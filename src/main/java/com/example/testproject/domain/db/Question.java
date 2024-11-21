package com.example.testproject.domain.db;

import com.example.testproject.domain.rest.QuestionType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type")
@Entity
@Table(name = "question")
public abstract class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;

  @ElementCollection
  @CollectionTable(name = "question_answers", joinColumns = @JoinColumn(name = "question_id"))
  private List<String> answers;

  @ElementCollection
  @CollectionTable(name = "question_votes", joinColumns = @JoinColumn(name = "question_id"))
  private List<VoteEntry> votes;

  public Question(String text, List<String> answers) {
    this.text = text;
    this.answers = answers;
    this.votes = answers.stream()
        .distinct()
        .map(answer -> new VoteEntry(answer, 0))
        .collect(Collectors.toList());
  }

  public boolean validateAnswer(String answer) {
    return answers.contains(answer);
  }

  public Boolean isCorrectAnswer(String answer) {
    return null;
  }

  public void addVote(String answer) {
    votes.stream()
        .filter(vote -> vote.getAnswer().equals(answer))
        .findFirst()
        .ifPresent(vote -> vote.setVoteCount(vote.getVoteCount() + 1));
  }

  public abstract QuestionType getType();
}

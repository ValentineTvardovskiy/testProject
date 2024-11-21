package com.example.testproject.service;

import com.example.testproject.domain.db.PollQuestion;
import com.example.testproject.domain.db.Question;
import com.example.testproject.domain.db.TriviaQuestion;
import com.example.testproject.domain.db.VoteEntry;
import com.example.testproject.domain.rest.CreateQuestionRequest;
import com.example.testproject.domain.rest.CreateQuestionResponse;
import com.example.testproject.domain.rest.QuestionResponse;
import com.example.testproject.domain.rest.TriviaQuestionResponse;
import com.example.testproject.domain.rest.VoteRequest;
import com.example.testproject.domain.rest.VoteResponse;
import com.example.testproject.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

  private final QuestionRepository repository;

  public Optional<CreateQuestionResponse> storeQuestion(CreateQuestionRequest request) {
    return createQuestion(request)
        .map(repository::save)
        .map(Question::getId)
        .map(CreateQuestionResponse::new);
  }

  public Optional<QuestionResponse> getQuestion(Long questionId) {
    return repository.findById(questionId).map(this::mapToQuestionResponse);
  }

  public Optional<VoteResponse> vote(Long questionId, VoteRequest request) {
    return repository.findById(questionId)
        .filter(question -> question.validateAnswer(request.answer()))
        .map(question -> {
          question.addVote(request.answer());
          repository.save(question);
          return new VoteResponse(
              question.getVotes().stream().collect(Collectors.toMap(VoteEntry::getAnswer, VoteEntry::getVoteCount)),
              question.isCorrectAnswer(request.answer()));
        });
  }

  private Optional<Question> createQuestion(CreateQuestionRequest request) {
    return switch (request.type()) {
      case POLL -> Optional.of(new PollQuestion(request.text(), request.answers()));
      case TRIVIA -> {
        if (request.correctAnswer() == null || request.correctAnswer().isBlank()) {
          yield Optional.empty();
        }
        yield Optional.of(new TriviaQuestion(request.text(), request.answers(), request.correctAnswer()));
      }
    };
  }

  private QuestionResponse mapToQuestionResponse(Question question) {
    return switch (question.getType()) {
      case POLL -> QuestionResponse.from(question);
      case TRIVIA -> TriviaQuestionResponse.from((TriviaQuestion) question);
    };
  }
}

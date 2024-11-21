package com.example.testproject.domain.db;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VoteEntry {
    @Column(name = "answer")
    private String answer;

    @Column(name = "vote_count")
    private Integer voteCount;
}
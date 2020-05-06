package com.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quiz.enums.AnswerStatus;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "ANSWER")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUESTION_ID")
    private Long questionId;

    @Column(name = "ANSWER")
    private String answer;

    @Column(name = "SCORE")
    private int score;

    @Column(name = "STATUS")
    @Enumerated(EnumType.ORDINAL)
    private AnswerStatus answerStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Question theQuestion;

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                ", score=" + score +
                ", answerStatus=" + answerStatus +
                '}';
    }
}

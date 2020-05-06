package com.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "QUESTION")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUIZ_ID")
    private Long quizId;

    @Column(name = "QUESTION")
    private String question;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "QUIZ_ID", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private Quiz theQuiz;

    @OneToMany(mappedBy = "theQuestion")
    private List<Answer> answerList;

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", question='" + question + '\'' +
                ", answerList=" + answerList +
                '}';
    }
}

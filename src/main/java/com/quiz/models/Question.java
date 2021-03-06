package com.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
import lombok.*;

@Table(name = "QUESTION")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
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

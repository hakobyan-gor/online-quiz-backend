package com.quiz.models;

import javax.persistence.*;
import lombok.*;

@Table(name = "PASSED_QUIZ")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
public class PassedQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "QUIZ_ID")
    private Long quizId;

    @Column(name = "SCORE")
    private Long score;

}

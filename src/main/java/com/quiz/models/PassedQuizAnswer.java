package com.quiz.models;

import javax.persistence.*;
import lombok.*;

@Table(name = "PASSED_QUIZ_ANSWER")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class PassedQuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PASSED_QUIZ_ID")
    private Long passedQuizId;

    @Column(name = "ANSWER_ID")
    private Long answerId;

}

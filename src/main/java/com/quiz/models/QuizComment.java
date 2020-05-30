package com.quiz.models;

import javax.persistence.*;
import lombok.*;

@Table(name = "QUIZ_COMMENT")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class QuizComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "QUIZ_ID")
    private Long quizId;

    @Column(name = "ROOT_COMMENT_ID")
    private Long rootCommentId;

}


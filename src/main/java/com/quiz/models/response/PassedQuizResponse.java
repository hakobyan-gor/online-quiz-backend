package com.quiz.models.response;

import com.quiz.models.PassedQuizAnswer;
import com.quiz.models.PassedQuiz;
import com.quiz.models.Quiz;

import java.util.List;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PassedQuizResponse {

    private List<PassedQuizAnswer> passedQuizAnswerList;
    private PassedQuiz passedQuiz;
    private Quiz quiz;

}

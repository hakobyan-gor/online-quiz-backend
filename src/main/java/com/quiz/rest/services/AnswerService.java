package com.quiz.rest.services;

import com.quiz.models.response.ResponseModel;
import com.quiz.models.Answer;
import java.util.List;

public interface AnswerService {

    ResponseModel<List<Answer>> createAnswers(List<Answer> answerList);

    ResponseModel<Answer> createAnswer(Answer answer);

    List<Long> getCorrectAnswersIDsByQuestionId(Long quizId);

    List<Answer> getCorrectAnswersByQuestionId(Long questionId);

    ResponseModel<Answer> getAnswerById(Long id);

    ResponseModel<List<Answer>> getAnswersByQuestionId(Long questionId);

    ResponseModel<Answer> updateAnswer(Answer answer);

}

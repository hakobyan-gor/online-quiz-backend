package com.quiz.rest.services;

import com.quiz.models.Answer;
import com.quiz.models.response.ResponseModel;

import java.util.List;

public interface AnswerService {

    public ResponseModel<List<Answer>> createAnswers(List<Answer> answerList);

    public ResponseModel<Answer> createAnswer(Answer answer);

    public ResponseModel<Answer> getAnswerById(Long id);

    public ResponseModel<List<Answer>> getAnswersByQuestionId(Long questionId);

    public ResponseModel<Answer> updateAnswer(Answer answer);

}

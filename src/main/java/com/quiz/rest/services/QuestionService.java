package com.quiz.rest.services;

import com.quiz.models.response.ResponseModel;
import com.quiz.models.Question;

import java.util.List;

public interface QuestionService {

    ResponseModel<List<Question>> createQuestions(List<Question> questions);

    ResponseModel<Question> createQuestion(Question question);

    ResponseModel<Question> getQuestionById(Long id);

    ResponseModel<List<Question>> getQuestionsByQuizId(Long quizId);

    ResponseModel<Question> updateQuestion(Question question);
}

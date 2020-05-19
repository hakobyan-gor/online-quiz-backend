package com.quiz.rest.services;

import com.quiz.models.PassedQuiz;
import com.quiz.models.response.ResponseModel;

import java.util.List;

public interface PassedQuizService {

    PassedQuiz addPassedQuiz(PassedQuiz passedQuiz);

    void getPassedQuizById(Long passedQuizId);

    ResponseModel<List<PassedQuiz>> getPassedQuiz(Long userId);

    void getPassedQuizzesById(Long quizId, Long userId);
}

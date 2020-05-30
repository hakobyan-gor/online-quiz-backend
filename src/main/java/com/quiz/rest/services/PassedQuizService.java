package com.quiz.rest.services;

import com.quiz.models.response.ResponseModel;
import com.quiz.models.PassedQuiz;
import java.util.List;

public interface PassedQuizService {

    PassedQuiz addPassedQuiz(PassedQuiz passedQuiz);

    PassedQuiz getPassedQuizById(Long passedQuizId);

    ResponseModel<List<PassedQuiz>> getPassedQuizzesByUserId(Long userId);

    ResponseModel<List<PassedQuiz>> getPassedQuizzesByQuizIdAndUserId(Long quizId, Long userId);
}

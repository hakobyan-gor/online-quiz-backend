package com.quiz.rest.services;

import com.quiz.models.response.PassedQuizResponse;
import com.quiz.models.request.PassQuizRequest;
import com.quiz.models.response.ResponseModel;
import com.quiz.models.QuizCategory;
import com.quiz.models.Quiz;

import java.util.List;

public interface QuizService {

    ResponseModel<Quiz> createQuiz(Quiz quiz);

    ResponseModel<List<Quiz>> createQuizzes(List<Quiz> quiz);

    ResponseModel<List<Quiz>> getAllQuizzes();

    ResponseModel<List<QuizCategory>> getRootCategories();

    ResponseModel<Quiz> getQuizById(Long id);

    ResponseModel<Quiz> updateQuiz(Quiz quiz);

    ResponseModel<List<Quiz>> getQuizzesByCategoryId(Long id);

    ResponseModel<PassedQuizResponse> passQuiz(PassQuizRequest passQuizRequest);
}

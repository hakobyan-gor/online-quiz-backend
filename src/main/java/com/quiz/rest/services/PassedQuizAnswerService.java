package com.quiz.rest.services;

import com.quiz.models.PassedQuizAnswer;
import java.util.List;

public interface PassedQuizAnswerService {

    List<PassedQuizAnswer> addPassedQuizAnswers(List<PassedQuizAnswer> passedQuizAnswerList);

}

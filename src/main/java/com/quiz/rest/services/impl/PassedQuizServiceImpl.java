package com.quiz.rest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.rest.repositories.PassedQuizRepository;
import com.quiz.rest.services.PassedQuizService;
import org.springframework.stereotype.Service;
import com.quiz.models.PassedQuiz;

@Service
public class PassedQuizServiceImpl implements PassedQuizService {

    private final PassedQuizRepository passedQuizRepository;

    @Autowired
    public PassedQuizServiceImpl(PassedQuizRepository passedQuizRepository) {
        this.passedQuizRepository = passedQuizRepository;
    }

    @Override
    public PassedQuiz addPassedQuiz(PassedQuiz passedQuiz) {
        return passedQuizRepository.save(passedQuiz);
    }

}

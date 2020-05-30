package com.quiz.rest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.rest.repositories.PassedQuizAnswerRepository;
import com.quiz.rest.services.PassedQuizAnswerService;
import org.springframework.stereotype.Service;
import com.quiz.models.PassedQuizAnswer;

import java.util.List;

@Service
public class PassedQuizAnswerServiceImpl implements PassedQuizAnswerService {

    private final PassedQuizAnswerRepository passedQuizAnswerRepository;

    @Autowired
    public PassedQuizAnswerServiceImpl(PassedQuizAnswerRepository passedQuizAnswerRepository) {
        this.passedQuizAnswerRepository = passedQuizAnswerRepository;
    }

    @Override
    public List<PassedQuizAnswer> addPassedQuizAnswers(List<PassedQuizAnswer> passedQuizAnswerList) {
        return passedQuizAnswerRepository.saveAll(passedQuizAnswerList);
    }

}

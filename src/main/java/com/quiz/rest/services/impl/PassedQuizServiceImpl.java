package com.quiz.rest.services.impl;

import com.quiz.models.response.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.rest.repositories.PassedQuizRepository;
import com.quiz.rest.services.PassedQuizService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.quiz.models.PassedQuiz;

import java.util.List;

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

    @Override
    public PassedQuiz getPassedQuizById(Long passedQuizId) {
        return passedQuizRepository.findPassedQuizById(passedQuizId);
    }

    @Override
    public ResponseModel<List<PassedQuiz>> getPassedQuizzesByUserId(Long userId) {
        return new ResponseModel.ResponseModelBuilder<List<PassedQuiz>>().
                success(true).data(passedQuizRepository.
                findPassedQuizzesByUserId(userId)).message("").
                httpStatus(HttpStatus.OK).build();
    }

    @Override
    public ResponseModel<List<PassedQuiz>> getPassedQuizzesByQuizIdAndUserId(Long quizId, Long userId) {
        return new ResponseModel.ResponseModelBuilder<List<PassedQuiz>>().
                success(true).data(passedQuizRepository.findPassedQuizzesByQuizIdAndUserId(quizId, userId)).
                message("").httpStatus(HttpStatus.OK).build();
    }
}

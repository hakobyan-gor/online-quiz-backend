package com.quiz.rest.controllers;

import com.quiz.models.PassedQuiz;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.services.PassedQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/passed-quizzes")
public class PassedQuizController {

    private final PassedQuizService passedQuizService;

    @Autowired
    public PassedQuizController(PassedQuizService passedQuizService) {
        this.passedQuizService = passedQuizService;
    }

    @GetMapping("/{passedQuizId}")
    public void getPassedQuizById(@PathVariable Long passedQuizId){
        passedQuizService.getPassedQuizById(passedQuizId);
    }

    @GetMapping("/user/{userId}")
    public ResponseModel<List<PassedQuiz>> getPassedQuizzes(@PathVariable Long userId){
        /*
         * Make get quizzes
         * by user id
         * with limit 1
         * in repository layer.
         */
        return passedQuizService.getPassedQuizzesByUserId(userId);
    }

    @GetMapping("/quiz/{quizId}/user/{userId}")
    public void getPassedQuizzesById(@PathVariable Long quizId, @PathVariable Long userId){
        passedQuizService.getPassedQuizzesByQuizIdAndUserId(quizId, userId);
    }

}

package com.quiz.rest.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.quiz.rest.services.QuizCommentService;
import com.quiz.models.response.ResponseModel;
import com.quiz.models.QuizComment;

@RestController
@RequestMapping("/quiz-comment")
public class QuizCommentController {

    private final QuizCommentService quizCommentService;

    public QuizCommentController(QuizCommentService quizCommentService) {
        this.quizCommentService = quizCommentService;
    }

    @PostMapping("/add")
    public ResponseModel<QuizComment> addComment(@RequestBody QuizComment quizComment){
        return quizCommentService.addComment(quizComment);
    }

}
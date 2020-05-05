package com.quiz.rest.controllers;

import com.quiz.models.Answer;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/add-list")
    public ResponseModel<List<Answer>> createQuestions(@RequestBody List<Answer> answerList){
        return answerService.createAnswers(answerList);
    }

    @PostMapping("/add")
    public ResponseModel<Answer> createQuestion(@RequestBody Answer answer){
        return answerService.createAnswer(answer);
    }

    @GetMapping("/get/question/{id}")
    public ResponseModel<List<Answer>> getAnswersByQuestionId(@PathVariable Long id){
        return answerService.getAnswersByQuestionId(id);
    }

    @GetMapping("/get/{id}")
    public ResponseModel<Answer> getAnswer(@PathVariable Long id){
        return answerService.getAnswerById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseModel<Answer> updateAnswer(@PathVariable Long id, @RequestBody Answer answer){
        answer.setId(id);
        return answerService.updateAnswer(answer);
    }

}
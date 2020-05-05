package com.quiz.rest.controllers;

import com.quiz.models.Question;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/add-list")
    public ResponseModel<List<Question>> createQuestions(@RequestBody List<Question> questionList){
        return questionService.createQuestions(questionList);
    }

    @PostMapping("/add")
    public ResponseModel<Question> createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }

    @GetMapping("/get/{id}")
    public ResponseModel<Question> getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("/get/quiz/{id}")
    public ResponseModel<List<Question>> getQuestionsByQuizId(@PathVariable Long id){
        return questionService.getQuestionsByQuizId(id);
    }

    @PutMapping("/update/{id}")
    public ResponseModel<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question){
        question.setId(id);
        return questionService.updateQuestion(question);
    }

}
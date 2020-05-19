package com.quiz.rest.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.models.response.PassedQuizResponse;
import org.springframework.web.bind.annotation.*;
import com.quiz.models.request.PassQuizRequest;
import com.quiz.models.response.ResponseModel;
import io.swagger.annotations.ApiOperation;
import com.quiz.rest.services.QuizService;
import com.quiz.models.QuizCategory;
import com.quiz.models.Quiz;
import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    @ApiOperation(value = "Adding new Quiz")
    public ResponseModel<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/add-list")
    @ApiOperation(value = "Adding new Quizzes")
    public ResponseModel<List<Quiz>> createQuizzes(@RequestBody List<Quiz> quizzes) {
        return quizService.createQuizzes(quizzes);
    }

    @PostMapping("/pass-quiz")
    @ApiOperation(value = "Passing quiz")
    public ResponseModel<PassedQuizResponse> passQuiz(@RequestBody PassQuizRequest passQuizRequest){
        return quizService.passQuiz(passQuizRequest);
    }

    @GetMapping("/root-categories")
    @ApiOperation(value = "Getting Quizzes Root Categories")
    public ResponseModel<List<QuizCategory>> getRootCategories(){
        return quizService.getRootCategories();
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Getting Quiz by Id")
    public ResponseModel<Quiz> getQuiz(@PathVariable(name = "id") Long id) {
        return quizService.getQuizById(id);
    }

    @GetMapping("/get")
    @ApiOperation(value = "Getting All Quizzes")
    public ResponseModel<List<Quiz>> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/get/category/{id}")
    public ResponseModel<List<Quiz>> getQuizzesByCategory(@PathVariable Long id){
        System.out.println(id);
        return quizService.getQuizzesByCategoryId(id);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    @ApiOperation(value = "Updating Quiz information")
    public ResponseModel<Quiz> updateQuizById(@PathVariable Long id, @RequestBody Quiz quiz) {
        quiz.setId(id);
        return quizService.updateQuiz(quiz);
    }

}
package com.quiz.rest.services.impl;

import com.quiz.models.Question;
import com.quiz.models.Quiz;
import com.quiz.models.QuizCategory;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.repositories.QuizRepository;
import com.quiz.rest.services.QuizCategoryService;
import com.quiz.rest.services.QuestionService;
import com.quiz.rest.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionService quizQuestionService;
    private final QuizCategoryService quizCategoryService;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository,
                           QuestionService quizQuestionService,
                           QuizCategoryService quizCategoryService) {
        this.quizRepository = quizRepository;
        this.quizQuestionService = quizQuestionService;
        this.quizCategoryService = quizCategoryService;
    }

    @Override
    public ResponseModel<Quiz> createQuiz(Quiz quiz) {
        System.out.println(quiz);
        quiz = quizRepository.save(quiz);
        ResponseModel<Quiz> responseModel = new ResponseModel<>();
        if (quiz.getQuestionList() != null) {
            List<Question> questionList = quiz.getQuestionList();
            for (Question question : questionList) {
                question.setQuizId(quiz.getId());
            }
            quizQuestionService.createQuestions(quiz.getQuestionList());
        }
        responseModel.setSuccess(true);
        responseModel.setData(quiz);
        responseModel.setMessage("Quiz successfully created!");
        responseModel.setHttpStatus(HttpStatus.CREATED);
        return responseModel;
    }

    @Override
    public ResponseModel<List<Quiz>> createQuizzes(List<Quiz> quizzes) {
        quizzes = quizRepository.saveAll(quizzes);
        for (Quiz value : quizzes) {
            if (value.getQuestionList() != null) {
                List<Question> questionList = value.getQuestionList();
                for (Question question : questionList) {
                    question.setQuizId(value.getId());
                }
                quizQuestionService.createQuestions(value.getQuestionList());
            }
        }
        ResponseModel<List<Quiz>> responseModel = new ResponseModel<>();
        responseModel.setSuccess(true);
        responseModel.setData(quizzes);
        responseModel.setMessage("Quizzes successfully created!");
        responseModel.setHttpStatus(HttpStatus.CREATED);
        return responseModel;
    }

    @Override
    public ResponseModel<List<QuizCategory>> getRootCategories() {
        List<QuizCategory> quizCategoryList = quizCategoryService.getRootCategories();
        ResponseModel<List<QuizCategory>> responseModel = new ResponseModel<>();
        responseModel.setSuccess(true);
        responseModel.setData(quizCategoryList);
        responseModel.setMessage("");
        responseModel.setHttpStatus(HttpStatus.OK);
        return responseModel;
    }

    @Override
    public ResponseModel<Quiz> getQuizById(Long id) {
        Quiz quiz = quizRepository.findQuizById(id);
        ResponseModel<Quiz> responseModel = new ResponseModel<>();
        if (quiz == null) {
            responseModel.setSuccess(false);
            responseModel.setData(null);
            responseModel.setMessage(String.format("Quiz with id : %d not found!", id));
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            responseModel.setSuccess(true);
            responseModel.setData(quiz);
            responseModel.setMessage("");
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

    @Override
    public ResponseModel<List<Quiz>> getAllQuizzes() {
        ResponseModel<List<Quiz>> responseModel = new ResponseModel<>();
        List<Quiz> quizList = quizRepository.findAll();
        responseModel.setSuccess(true);
        responseModel.setData(quizList);
        responseModel.setMessage("");
        responseModel.setHttpStatus(HttpStatus.OK);
        return responseModel;
    }

    @Override
    public ResponseModel<List<Quiz>> getQuizzesByCategoryId(Long id) {
        ResponseModel<List<Quiz>> responseModel = new ResponseModel<>();
        List<Quiz> quizList = quizRepository.findQuizByCategoryId(id);
        if (quizList == null) {
            responseModel.setSuccess(false);
            responseModel.setMessage("Quizzes with this Category not found");
            responseModel.setData(null);
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            responseModel.setSuccess(true);
            responseModel.setMessage("");
            responseModel.setData(quizList);
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

    @Override
    public ResponseModel<Quiz> updateQuiz(Quiz quiz) {
        ResponseModel<Quiz> responseModel = new ResponseModel<>();
        Quiz oldQuiz = quizRepository.findQuizById(quiz.getId());
        if (oldQuiz == null) {
            responseModel.setSuccess(false);
            responseModel.setData(null);
            responseModel.setMessage("Quiz not found!");
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            oldQuiz.setName(quiz.getName());
            quiz = quizRepository.save(oldQuiz);
            responseModel.setSuccess(true);
            responseModel.setData(quiz);
            responseModel.setMessage("Quiz successfully updated!");
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

}

package com.quiz.rest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.models.response.PassedQuizResponse;
import com.quiz.rest.services.QuizCategoryService;
import com.quiz.rest.repositories.QuizRepository;
import com.quiz.models.request.PassQuizRequest;
import org.springframework.stereotype.Service;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.services.QuestionService;
import com.quiz.rest.services.AnswerService;
import org.springframework.http.HttpStatus;
import com.quiz.rest.services.QuizService;
import java.util.ArrayList;
import com.quiz.models.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionService quizQuestionService;
    private final QuizCategoryService quizCategoryService;
    private final AnswerService answerService;

    @Autowired
    public QuizServiceImpl(QuizRepository quizRepository,
                           QuestionService quizQuestionService,
                           QuizCategoryService quizCategoryService,
                           AnswerService answerService) {
        this.quizRepository = quizRepository;
        this.quizQuestionService = quizQuestionService;
        this.quizCategoryService = quizCategoryService;
        this.answerService = answerService;
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
    public ResponseModel<PassedQuizResponse> passQuiz(PassQuizRequest passQuizRequest) {

        // Map<Long, List<Long>> correctAnswer = questionsCorrectAnswers(passQuizRequest.getQuestionsIDs());

        HashMap<Long, List<Answer>> questionsIDsAndCorrectAnswers = (HashMap<Long, List<Answer>>) getCorrectAnswersByQuestionId(new ArrayList<>(passQuizRequest.getQuestionsIDsAndSelectedAnswers().keySet()));
        HashMap<Long, List<Long>> questionIDsAndSelectedAnswers = (HashMap<Long, List<Long>>) passQuizRequest.getQuestionsIDsAndSelectedAnswers();

        PassedQuizResponse passedQuizResponse = checkPassedQuiz(questionsIDsAndCorrectAnswers, questionIDsAndSelectedAnswers);


        return new ResponseModel<>(true, "Quiz Passed Successfully", passedQuizResponse, HttpStatus.OK);
    }

    private PassedQuizResponse checkPassedQuiz(Map<Long, List<Answer>> questionsIDsAndCorrectAnswers,
                                               Map<Long, List<Long>> questionIDsAndSelectedAnswers) {
        PassedQuizResponse response = new PassedQuizResponse();
        long score = 0L;
        for (Map.Entry<Long, List<Answer>> entry : questionsIDsAndCorrectAnswers.entrySet()) {
            List<Answer> correctAnswers = entry.getValue();
            List<Long> selectedAnswersIDs = questionIDsAndSelectedAnswers.get(entry.getKey());
            for (int i = 0; i < correctAnswers.size(); i++) {
                if (correctAnswers.get(i).getId().equals(selectedAnswersIDs.get(i))) {
                    score += correctAnswers.get(i).getScore();
                }
            }
        }
        response.setScore(score);
        return response;
    }

    private Map<Long, List<Long>> questionsCorrectAnswers(List<Long> questionsIDs) {
        Map<Long, List<Long>> answersIDs = new HashMap<>();
        for (Long questionsID : questionsIDs) {
            answersIDs.put(questionsID, answerService.getCorrectAnswersIDsByQuestionId(questionsID));
        }
        return answersIDs;
    }

    private Map<Long, List<Answer>> getCorrectAnswersByQuestionId(List<Long> questionIDs) {
        Map<Long, List<Answer>> correctAnswers = new HashMap<>();
        for (Long questionId : questionIDs) {
            correctAnswers.put(questionId, answerService.getCorrectAnswersByQuestionId(questionId));
        }
        return correctAnswers;
    }

    @Override
    public ResponseModel<List<QuizCategory>> getRootCategories() {
        List<QuizCategory> quizCategoryList = quizCategoryService.getRootCategories();
        return new ResponseModel<>(true, "", quizCategoryList, HttpStatus.OK);
    }

    @Override
    public ResponseModel<Quiz> getQuizById(Long id) {
        Quiz quiz = quizRepository.findQuizById(id);
        ResponseModel<Quiz> responseModel;
        if (quiz == null) {
            responseModel = new ResponseModel.ResponseModelBuilder<Quiz>().
                    success(false).data(null).message(String.format("Quiz with id : %d not found!", id)).
                    httpStatus(HttpStatus.NOT_FOUND).build();

        } else {
            responseModel = new ResponseModel.ResponseModelBuilder<Quiz>().
                    success(true).data(quiz).message("").httpStatus(HttpStatus.OK).build();
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
        ResponseModel<List<Quiz>> responseModel;
        List<Quiz> quizList = quizRepository.findQuizByCategoryId(id);
        if (quizList == null) {
            responseModel = new ResponseModel.ResponseModelBuilder<List<Quiz>>().
                    success(false).message("Quizzes with this Category not found").
                    data(null).httpStatus(HttpStatus.NOT_FOUND).build();
        } else {
            responseModel = new ResponseModel.ResponseModelBuilder<List<Quiz>>().
                    success(true).message("").data(quizList).httpStatus(HttpStatus.OK).build();
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

package com.quiz.rest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.rest.repositories.QuestionRepository;
import org.springframework.stereotype.Service;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.services.QuestionService;
import com.quiz.rest.services.AnswerService;
import org.springframework.http.HttpStatus;
import com.quiz.models.Question;
import com.quiz.models.Answer;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerService answerService;

    @Autowired
    public QuestionServiceImpl(QuestionRepository quizQuestionRepository,
                               AnswerService quizAnswerService) {
        this.questionRepository = quizQuestionRepository;
        this.answerService = quizAnswerService;
    }

    @Override
    public ResponseModel<List<Question>> createQuestions(List<Question> questions) {
        questions = questionRepository.saveAll(questions);
        ResponseModel<List<Question>> responseModel = new ResponseModel<>();
        for (Question question : questions) {
            if (question.getAnswerList() != null) {
                List<Answer> answerList = question.getAnswerList();
                for (Answer answer : answerList) {
                    answer.setQuestionId(question.getId());
                }
                answerService.createAnswers(answerList);
            }
        }
        responseModel.setSuccess(true);
        responseModel.setData(questions);
        responseModel.setMessage("Questions successfully created!");
        responseModel.setHttpStatus(HttpStatus.OK);
        return responseModel;
    }

    @Override
    public ResponseModel<Question> createQuestion(Question question) {
        question = questionRepository.save(question);
        ResponseModel<Question> responseModel = new ResponseModel<>();
        if (question.getAnswerList() != null) {
            List<Answer> answerList = question.getAnswerList();
            for (Answer answer : answerList) {
                answer.setQuestionId(question.getId());
            }
            answerService.createAnswers(answerList);
        }
        responseModel.setSuccess(true);
        responseModel.setData(question);
        responseModel.setMessage("Question successfully created!");
        responseModel.setHttpStatus(HttpStatus.OK);
        return responseModel;
    }

    @Override
    public ResponseModel<Question> getQuestionById(Long id) {
        Question question = questionRepository.findQuestionById(id);
        ResponseModel<Question> responseModel = new ResponseModel<>();
        if (question == null) {
            responseModel.setSuccess(false);
            responseModel.setData(null);
            responseModel.setMessage(String.format("Question with id : %d not found!", id));
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            responseModel.setSuccess(true);
            responseModel.setData(question);
            responseModel.setMessage("");
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

    @Override
    public ResponseModel<List<Question>> getQuestionsByQuizId(Long quizId) {
        List<Question> questionList = questionRepository.findQuestionByQuizId(quizId);
        ResponseModel<List<Question>> responseModel = new ResponseModel<>();
        if (questionList == null) {
            responseModel.setSuccess(false);
            responseModel.setData(null);
            responseModel.setMessage(String.format("Questions with quiz id : %d not found", quizId));
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            responseModel.setSuccess(true);
            responseModel.setData(questionList);
            responseModel.setMessage("");
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

    @Override
    public ResponseModel<Question> updateQuestion(Question question) {
        ResponseModel<Question> responseModel = new ResponseModel<>();
        Question oldQuestion = questionRepository.findQuestionById(question.getId());
        if (oldQuestion == null) {
            responseModel.setSuccess(false);
            responseModel.setData(null);
            responseModel.setMessage(String.format("Question with id %d not found!", question.getId()));
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            oldQuestion.setQuestion(question.getQuestion());
            question = questionRepository.save(oldQuestion);
            responseModel.setSuccess(true);
            responseModel.setData(question);
            responseModel.setMessage("Question successfully updated!");
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

}

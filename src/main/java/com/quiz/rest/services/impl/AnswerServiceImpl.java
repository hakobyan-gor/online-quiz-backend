package com.quiz.rest.services.impl;

import com.quiz.models.Answer;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.repositories.AnswerRepository;
import com.quiz.rest.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public ResponseModel<List<Answer>> createAnswers(List<Answer> answerList) {
        answerList = answerRepository.saveAll(answerList);
        ResponseModel<List<Answer>> responseModel = new ResponseModel<>();
        responseModel.setSuccess(true);
        responseModel.setData(answerList);
        responseModel.setMessage("Answer list successfully created!");
        responseModel.setHttpStatus(HttpStatus.CREATED);
        return responseModel;
    }

    @Override
    public ResponseModel<Answer> createAnswer(Answer answer) {
        answer = answerRepository.save(answer);
        ResponseModel<Answer> responseModel = new ResponseModel<>();
        responseModel.setSuccess(true);
        responseModel.setData(answer);
        responseModel.setMessage("Answer successfully created!");
        responseModel.setHttpStatus(HttpStatus.CREATED);
        return responseModel;
    }

    @Override
    public ResponseModel<Answer> getAnswerById(Long id) {
        ResponseModel<Answer> responseModel = new ResponseModel<>();
        Answer answer = answerRepository.findAnswerById(id);
        if (answer == null){
            responseModel.setSuccess(false);
            responseModel.setData(null);
            responseModel.setMessage(String.format("Answer with id : %d not found", id));
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            responseModel.setSuccess(true);
            responseModel.setData(answer);
            responseModel.setMessage("");
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

    @Override
    public List<Long> getCorrectAnswersIDsByQuestionId(Long questionId) {
        return answerRepository.findCorrectAnswerIDByQuestionId(questionId);
    }

    @Override
    public List<Answer> getCorrectAnswersByQuestionId(Long questionId) {
        return answerRepository.findCorrectAnswersByQuestionId(questionId);
    }

    @Override
    public ResponseModel<List<Answer>> getAnswersByQuestionId(Long questionId) {
        List<Answer> answerList = answerRepository.findAnswerByQuestionId(questionId);
        ResponseModel<List<Answer>> responseModel = new ResponseModel<>();
        if (answerList == null){
            responseModel.setSuccess(false);
            responseModel.setData(null);
            responseModel.setMessage(String.format("Answers not found with question id : %d", questionId));
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            responseModel.setSuccess(true);
            responseModel.setData(answerList);
            responseModel.setMessage("");
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

    @Override
    public ResponseModel<Answer> updateAnswer(Answer answer) {
        ResponseModel<Answer> responseModel = new ResponseModel<>();
        Answer oldAnswer = answerRepository.findAnswerById(answer.getId());
        if (oldAnswer == null){
            responseModel.setSuccess(false);
            responseModel.setMessage(String.format("Answer with id : %d not found", answer.getId()));
            responseModel.setData(null);
            responseModel.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            if (answer.getAnswer() != null)
                oldAnswer.setAnswer(answer.getAnswer());
            if (answer.getAnswerStatus() != null)
                oldAnswer.setAnswerStatus(answer.getAnswerStatus());
            oldAnswer.setScore(answer.getScore());
            answer = answerRepository.save(oldAnswer);
            responseModel.setSuccess(true);
            responseModel.setData(answer);
            responseModel.setMessage("Answer successfully updated!");
            responseModel.setHttpStatus(HttpStatus.OK);
        }
        return responseModel;
    }

}

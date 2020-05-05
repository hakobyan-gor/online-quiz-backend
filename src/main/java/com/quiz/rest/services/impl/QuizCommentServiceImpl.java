package com.quiz.rest.services.impl;

import com.quiz.models.QuizComment;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.repositories.QuizCommentRepository;
import com.quiz.rest.services.QuizCommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizCommentServiceImpl implements QuizCommentService {

    private QuizCommentRepository quizCommentRepository;

    public QuizCommentServiceImpl(QuizCommentRepository quizCommentRepository) {
        this.quizCommentRepository = quizCommentRepository;
    }

    @Override
    public ResponseModel<QuizComment> addComment(QuizComment quizComment) {
        return null;
    }

    @Override
    public ResponseModel<QuizComment> replyComment(QuizComment quizComment) {
        return null;
    }

    @Override
    public ResponseModel<List<QuizComment>> getCommentsByQuizId(Long id) {
        return null;
    }

    @Override
    public ResponseModel<List<QuizComment>> getCommentByUserId(Long id) {
        return null;
    }

    @Override
    public ResponseModel<QuizComment> updateComment(QuizComment quizComment) {
        return null;
    }

    @Override
    public ResponseModel<?> deleteComment(Long id) {
        return null;
    }

}

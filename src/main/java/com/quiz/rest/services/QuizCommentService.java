package com.quiz.rest.services;

import com.quiz.models.QuizComment;
import com.quiz.models.response.ResponseModel;

import java.util.List;

public interface QuizCommentService {

    ResponseModel<QuizComment> addComment(QuizComment quizComment);

    ResponseModel<QuizComment> replyComment(QuizComment quizComment);

    ResponseModel<List<QuizComment>> getCommentsByQuizId(Long id);

    ResponseModel<List<QuizComment>> getCommentByUserId(Long id);

    ResponseModel<QuizComment> updateComment(QuizComment quizComment);

    ResponseModel<?> deleteComment(Long id);

}

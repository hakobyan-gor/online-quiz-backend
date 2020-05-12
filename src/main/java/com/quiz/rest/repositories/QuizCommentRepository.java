package com.quiz.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quiz.models.QuizComment;

@Repository
public interface QuizCommentRepository extends JpaRepository<QuizComment, Long> {
}

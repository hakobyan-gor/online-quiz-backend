package com.quiz.rest.repositories;

import com.quiz.models.QuizComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCommentRepository extends JpaRepository<QuizComment, Long> {
}

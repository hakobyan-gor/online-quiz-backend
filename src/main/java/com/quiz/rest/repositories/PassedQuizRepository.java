package com.quiz.rest.repositories;

import com.quiz.models.PassedQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassedQuizRepository extends JpaRepository<PassedQuiz, Long> {
}
